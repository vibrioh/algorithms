package leetcode;

public class LongestUncommonSubsequenceI {
  class Solution {
    public int findLUSlength(String a, String b) {
      if (a.equals(b)) {    // use a == b will be wrong answer, because a, b in different memory addresses
        return -1;
      }
      return Math.max(a.length(), b.length());
    }
  }
}
