package ggl;

/*
Intuition

Let's ask possible(D): with K (or less) gas stations, can we make every adjacent distance between gas stations at most D? This function is monotone, so we can apply a binary search to find D^{\text{*}}D
*
 .

Algorithm

More specifically, there exists some D* (the answer) for which possible(d) = False when d < D* and possible(d) = True when d > D*. Binary searching a monotone function is a typical technique, so let's focus on the function possible(D).

When we have some interval like X = stations[i+1] - stations[i], we'll need to use \lfloor \frac{X}{D} \rfloor⌊
D
X
​
 ⌋ gas stations to ensure every subinterval has size less than D. This is independent of other intervals, so in total we'll need to use \sum_i \lfloor \frac{X_i}{D} \rfloor∑
i
​
 ⌊
D
X
i
​

​
 ⌋ gas stations. If this is at most K, then it is possible to make every adjacent distance between gas stations at most D.

 Complexity Analysis

Time Complexity: O(N \log W)O(NlogW), where NN is the length of stations, and W = 10^{14}W=10
14
  is the range of possible answers (10^810
8
 ), divided by the acceptable level of precision (10^{-6}10
−6
 ).

Space Complexity: O(1)O(1) in additional space complexity.
 */
public class GasStation {
    public double minmaxGasDist(int[] stations, int K) {
        double lo = 0, hi = 1e8;
        while (hi - lo > 1e-6) {
            double mi = (lo + hi) / 2.0;
            if (possible(mi, stations, K))
                hi = mi;
            else
                lo = mi;
        }
        return lo;
    }

    public boolean possible(double D, int[] stations, int K) {
        int used = 0;
        for (int i = 0; i < stations.length - 1; ++i)
            used += (int) ((stations[i + 1] - stations[i]) / D);
        return used <= K;
    }

}
