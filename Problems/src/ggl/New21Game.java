package ggl;

public class New21Game {
    /*
    Intuition:
    The same problems as "Climbing Stairs".

    Explanation:
    In a word,
    dp[i]: probability of get points i
    dp[i] = sum(last W dp values) / W

    To get Wsum = sum(last W dp values), we can maintain a sliding window with size at most K.

    Time Complexity:
    O(N)
     */

    public double new21Game(int N, int K, int W) {
        if (K == 0 || N >= K + W) return 1;
        double dp[] = new double[N + 1], Wsum = 1, res = 0;
        dp[0] = 1;
        for (int i = 1; i <= N; ++i) {
            dp[i] = Wsum / W;
            if (i < K) Wsum += dp[i];
            else res += dp[i];
            if (i - W >= 0) Wsum -= dp[i - W];
        }
        return res;
    }
}
