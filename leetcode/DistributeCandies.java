package leetcode;

import java.util.HashSet;
import java.util.Set;

public class DistributeCandies {
  class Solution {
    public int distributeCandies(int[] candies) {
      Set<Integer> set = new HashSet<>();
      for (int i : candies) {
        set.add(i);
      }
      /**
       * Math.min(set.size(), (candies.length / 2))
       */
      return 2 * set.size() > candies.length ? (candies.length / 2) : (set.size());
    }
  }
}
