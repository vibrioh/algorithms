package plt;

import java.util.Arrays;
import java.util.List;

public class MinimumTimeDifference {
    public int findMinDifference(List<String> timePoints) {
        int[] mins = new int[timePoints.size()];
        int idx = 0;
        for (String s : timePoints) {
            int min = 0;
            String[] ss = s.split(":");
            min = Integer.valueOf(ss[0]) * 60 + Integer.valueOf(ss[1]);
            mins[idx++] = min;
        }
        Arrays.sort(mins);
        int res = Math.min(1440, mins[0] + 1440 - mins[mins.length - 1]);
        for (int i = 1; i < mins.length; i++) {
            res = Math.min(res, mins[i] - mins[i - 1]);
        }
        return res;
    }
}
