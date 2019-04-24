package fb;

import java.util.HashMap;
import java.util.Map;

public class ContinuousSubarraySum {
    public boolean checkSubarraySum(int[] nums, int k) {
        if (nums == null) {
            return false;
        }
        int len = nums.length;
        int sum = 0;
        // to record fist idx
        Map<Integer, Integer> mp = new HashMap<>();
        mp.put(0, -1);
        for (int i = 0; i < len; i++) {
            sum += nums[i];
            // math
            /*
                Haven't seen anyone post the math or theory behind the solutions yet, so I'm sharing mine. Let me know if there is any better one.
                In short, start with mod =0, then we always do mod = (mod+nums[i])%k, if mod repeats, that means between these two mod = x occurences the sum is multiple of k.
                Math: c = a % k, c = b % k, so we have a % k = b % k.
                Where a is the mod at i and b is the mod at j and a <= b, i < j, because all nums are non-negative. And c is the mod that repeats.
                Suppose b-a=d, then we have b % k = ((a+d) % k)%k = (a%k + d%k)%k
                In order to make the equation valid: a % k = (a%k + d%k)%k
                d%k has to be 0, so d, the different between b and a, is a multiple of k
                Example:
                [23, 2, 1, 6, 7] k=9
                mod = 5, 7, 8, 5 <-- at here we found it
             */
            if (k != 0) {
                sum %= k;
            }
            if (mp.containsKey(sum) && i - mp.get(sum) >= 2) {
                return true;
            }
            // to get longer distance
            mp.putIfAbsent(sum, i);
        }
        return false;
    }
}
