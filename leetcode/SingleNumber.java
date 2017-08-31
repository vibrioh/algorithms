package leetcode;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * XOR trick, n^n = 0, 0^m = m, so XOR all, find single number
 */
public class SingleNumber {
  class Solution {
    public int singleNumber(int[] nums) {
      Set<Integer> set = new HashSet<>();

      for (int i : nums) {
        if (!set.remove(i)) {
          set.add(i);
        }
      }

      Iterator<Integer> iter = set.iterator();

      return iter.next();
    }
  }

  class SolutionXOR {
    public int singleNumber(int[] nums) {
      int res = 0;
      for (int i = 0; i < nums.length; i++) {
        res ^= nums[i];
      }
      return res;
    }
  }
}
