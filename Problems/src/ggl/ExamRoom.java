package ggl;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.TreeSet;

// PriorityQueue
/*
#### PriorityQueue
- Use priority queue to sort by customized class interval{int dist; int x, y;}.
- Sort by larger distance and then sort by start index
- seat(): pq.poll() to find interval of largest distance. Split and add new intervals back to queue.
- leave(x): one seat will be in 2 intervals: remove both from pq, and merge to a new interval.
- 主方程写出来其实很好写, 就是 split + add interval, 然后 find + delete interval 而已. 最难的是构建data structure
- seat(): O(logn), leave(): O(n)

##### Trick: 构建虚拟 boundary
- 如果是开头的seat, 或者是结尾的seat, 比较难handle: 一开始坐在seat=0的时候, 没有interval啊!
- Trick就是, 我们自己定义个虚拟的座位 `seat=-1`, `seat=N`
- 一开始有一个 interval[-1, N] 然后就建立了boundary.
- 从此以后, 每次split成小interval的时候:
- 遇到 `interval[-1, y]`, distance就是 `(y - 0)`
- 遇到 `interval[x, N]`, distance就是 `(N - 1 - x)`
- 当然正常的interval dist 就是 `(y - x) / 2`

##### distance 中间点
- Interval.dist 我们其实做的是 distance的中间点 `(y - x) / 2`
- 这里的dist是 `距离两边的距离` 而不是 x, y 之间的距离. 这里要特别注意.
 */
class ExamRoom {
    PriorityQueue<Interval> pq;
    int N;

    class Interval {
        // dist is the distance to both side
        int x, y, dist;

        public Interval(int x, int y) {
            this.x = x;
            this.y = y;
            if (x == -1) {
                this.dist = y;
            } else if (y == N) {
                this.dist = N - 1 - x;
            } else {
                this.dist = Math.abs(x - y) / 2;
            }
        }
    }

    public ExamRoom(int N) {
        this.pq = new PriorityQueue<>((a, b) -> a.dist != b.dist ? b.dist - a.dist : a.x - b.x);
        this.N = N;
        pq.add(new Interval(-1, N));
    }

    // O(logn): poll top candidate, split into two new intervals
    public int seat() {
        int seat = 0;
        Interval interval = pq.poll();
        if (interval.x == -1) seat = 0;
        else if (interval.y == N) seat = N - 1;
        else seat = (interval.x + interval.y) / 2;

        pq.offer(new Interval(interval.x, seat));
        pq.offer(new Interval(seat, interval.y));

        return seat;
    }

    // O(n)Find head and tail based on p. Delete and merge two ends
    public void leave(int p) {
        Interval head = null, tail = null;
        List<Interval> intervals = new ArrayList<>(pq);
        for (Interval interval : intervals) {
            if (interval.x == p) tail = interval;
            if (interval.y == p) head = interval;
            if (head != null && tail != null) break;
        }
        // Delete
        pq.remove(head);
        pq.remove(tail);
        // Merge
        pq.offer(new Interval(head.x, tail.y));
    }
}
