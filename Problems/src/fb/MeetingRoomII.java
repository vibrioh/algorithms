package fb;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class MeetingRoomII {
    public int minMeetingRooms(int[][] intervals) {
        Queue<Integer> ends = new PriorityQueue<>();
        Arrays.sort(intervals, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        for (int[] interval : intervals) {
            int start = interval[0];
            int end = interval[1];
            if (ends.isEmpty()) {
                ends.offer(end);
                continue;
            }
            if (start >= ends.peek()) {
                ends.poll();
            }
            ends.offer(end);
        }
        return ends.size();
    }
}
