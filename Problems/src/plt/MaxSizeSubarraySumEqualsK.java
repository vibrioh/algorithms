package plt;

import java.util.HashMap;
import java.util.Map;

public class MaxSizeSubarraySumEqualsK {
    public int maxSubArrayLen(int[] nums, int k) {
        int preSum = 0;
        int len = 0;
        Map<Integer, Integer> mp = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            preSum += nums[i];
            if (preSum == k) {
                len = i + 1;
            }
            int preTarget = preSum - k;
            if (mp.containsKey(preTarget)) {
                len = Math.max(i - mp.get(preTarget), len);
            }
            mp.putIfAbsent(preSum, i);
        }
        return len;
    }
}
