package fb;

import java.util.HashMap;
import java.util.Map;

public class SubarraySumEqualsK {
    public int subarraySum(int[] nums, int k) {
        if (nums == null) {
            return 0;
        }
        Map<Integer, Integer> sumNum = new HashMap<>();
        int res = 0;
        int sum = 0;
        sumNum.put(sum, 1);
        for (int n : nums) {
            sum += n;
            int target = sum - k;
            if (sumNum.containsKey(target)) {
                res += sumNum.get(target);
            }
            sumNum.put(sum, sumNum.getOrDefault(sum, 0) + 1);
        }
        return res;
    }
}
