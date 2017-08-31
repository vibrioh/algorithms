package leetcode;

public class NimGame {
  class Solution {
    public boolean canWinNim(int n) {
      return !(n % 4 == 0);
    }
  }
}
