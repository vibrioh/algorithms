package ggl;

import java.util.Arrays;
import java.util.PriorityQueue;

/*

根据描述任何一个合法的pay group里面任意两个worker之间 quality1/quality2 = wage1/wage2，转化一下 wage1/quality1 = wage2 / quality2，即所有的工人他们的paid wage和他们的quality的比值都应该是一样的，根据这个性质，我们每一个合法的pay group只能取最大的wage/quality当作所有工人的pay ratio。

假如我们不取最大的，选择的ratio为w1/q1，而且存在一个工人的wagei/qualityi > w1/q1，转换一下很容易得到 wagei > quaility * w1/q1，即表示该工人的最低工资大于了他实际得到的工资，和题意不符合。

所以有了以上性质后，直接根据ratio排序，然后又需要每个group的pay最小，每个group的总工资计算方法（q1+q2+q3+...+qk）* ratio，所以就是需要quality的sum最小，那每次我们加入了一个新的ratio就把quality最大worker踢出group，这样每次group的pay可以保证是新ratio下的最小pay。遍历数组，记录所有ratio中出现的pay最小值即可。


"1. Every worker in the paid group should be paid in the ratio of their quality compared to other workers in the paid group."
So for any two workers in the paid group,
we have wage[i] : wage[j] = quality[i] : quality[j]
So we have wage[i] : quality[i] = wage[j] : quality[j]
We pay wage to every worker in the group with the same ratio compared to his own quality.

"2. Every worker in the paid group must be paid at least their minimum wage expectation."
For every worker, he has an expected ratio of wage compared to his quality.

So to minimize the total wage, we want a small ratio.
So we sort all workers with their expected ratio, and pick up K first worker.
Now we have a minimum possible ratio for K worker and we their total quality.

As we pick up next worker with bigger ratio, we increase the ratio for whole group.
Meanwhile we remove a worker with highest quality so that we keep K workers in the group.
We calculate the current ratio * total quality = total wage for this group.

We redo the process and we can find the minimum total wage.
Because workers are sorted by ratio of wage/quality.
For every ratio, we find the minimum possible total quality of K workers.

Time Complexity
O(NlogN) for sort.
O(NlogK) for priority queue.
 */
public class HireWorker {
    public double mincostToHireWorkers(int[] q, int[] w, int K) {
        double[][] workers = new double[q.length][2];
        for (int i = 0; i < q.length; ++i)
            workers[i] = new double[]{(double) (w[i]) / q[i], (double) q[i]};
        Arrays.sort(workers, (a, b) -> Double.compare(a[0], b[0]));
        double res = Double.MAX_VALUE, qsum = 0;
        PriorityQueue<Double> pq = new PriorityQueue<>();
        for (double[] worker : workers) {
            qsum += worker[1];
            pq.add(-worker[1]);
            // because negative, so this is actually get rid of
            if (pq.size() > K) qsum += pq.poll();
            // use curr max ratio
            if (pq.size() == K) res = Math.min(res, qsum * worker[0]);
        }
        return res;
    }
}
