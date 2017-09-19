package leetcode;

public class RangeAdditionII {

  /**
   * need to consider ops = [], so a = m, b = n.
   * just as
   m = Math.min(m, op[0]);
   n = Math.min(n, op[0]);
   */
  class Solution {
    public int maxCount(int m, int n, int[][] ops) {
      int a = m;
      int b = n;
      for (int[] op : ops) {
        a = Math.min(a, op[0]);
        b = Math.min(b, op[1]);
      }
      return a*b;
    }
  }
}
