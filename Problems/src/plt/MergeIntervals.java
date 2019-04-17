package plt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeIntervals {
    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return intervals;
        }
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        List<int[]> res = new ArrayList<>();
        int start = intervals[0][0];
        int end = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            int[] curr = intervals[i];
            if (end >= curr[0]) {
                end = Math.max(curr[1], end);
                continue;
            } else {
                res.add(new int[]{start, end});
                start = curr[0];
                end = curr[1];
            }
        }
        res.add(new int[]{start, end});
        int[][] ans = res.toArray(new int[res.size()][]);
        return ans;
    }
}
