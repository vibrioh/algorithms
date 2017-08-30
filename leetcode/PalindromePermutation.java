package leetcode;

import java.util.HashSet;
import java.util.Set;

public class PalindromePermutation {
  class Solution {
    public boolean canPermutePalindrome(String s) {
      if (s == null || s.length() == 0) {
        return false;
      }

      Set<Character> set = new HashSet<>();

      for (char c : s.toCharArray()) {
        if (!set.remove(c)) {
          set.add(c);
        }
      }

      return set.size() < 2;
    }
  }

  public class Solution2 {
    public boolean canPermutePalindrome(String s) {
      Set < Character > set = new HashSet < > ();
      for (int i = 0; i < s.length(); i++) {
        if (!set.add(s.charAt(i)))
          set.remove(s.charAt(i));
      }
      return set.size() <= 1;
    }
  }
}
