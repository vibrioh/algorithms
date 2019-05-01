package plt;

import java.util.*;

public class StatisticsOnStream {
    Queue<Integer> minQ;
    Queue<Integer> maxQ;
    Map<Integer, Integer> counts;
    List<Integer> modes;
    int maxCount;
    int sum;

    public StatisticsOnStream() {
        minQ = new PriorityQueue<>();
        maxQ = new PriorityQueue<>((a, b) -> (int) b - (int) a);
        counts = new HashMap<>();
        modes = new ArrayList<>();
        maxCount = 0;
        sum = 0;
    }

    public void addNum(int n) {
        maxQ.offer(n);
        minQ.offer(maxQ.poll());
        if (maxQ.size() < minQ.size()) {
            maxQ.offer(minQ.poll());
        }
        sum += n;
        counts.put(n, counts.getOrDefault(n, 0) + 1);
        if (maxCount < counts.get(n)) {
            maxCount = counts.get(n);
            modes = new ArrayList<>();
            modes.add(n);
        } else if (maxCount == counts.get(n)) {
            modes.add(n);
        }
    }

    public double getMedian() {
        if (maxQ.size() == minQ.size()) {
            return (maxQ.peek() + minQ.peek()) / 2.0;
        } else {
            return maxQ.peek();
        }
    }

    public double getMean() {
        return (double) sum / (maxQ.size() + minQ.size());
    }

    public List<Integer> getMode() {
        if (maxCount == 1 && (minQ.size() + maxQ.size() > 1)) {
            return new ArrayList<>();
        }
        return modes;
    }

    public static void main(String[] args) {
        StatisticsOnStream s = new StatisticsOnStream();
        Scanner scan = new Scanner(System.in);
        while (scan.hasNext()) {
            int n = scan.nextInt();
            s.addNum(n);
            System.out.println("Mean: " + s.getMean() + " Median: " + s.getMedian() + " Mode: " + s.getMode());
        }
    }
}


