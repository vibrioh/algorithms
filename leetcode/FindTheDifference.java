package leetcode;

import java.util.Arrays;

public class FindTheDifference {
  class Solution {
    public char findTheDifference(String s, String t) {
      char[] sArr = s.toCharArray();
      char[] tArr = t.toCharArray();
      Arrays.sort(sArr);
      Arrays.sort(tArr);
      for (int i = 0; i < sArr.length; i++) {
        if (sArr[i] != tArr[i]) {
          return tArr[i];
        }
      }
      return tArr[tArr.length - 1];
    }
  }

  public char findTheDifference2(String s, String t) {
    int charCode = t.charAt(s.length());
    // Iterate through both strings and char codes
    for (int i = 0; i < s.length(); ++i) {
      charCode -= (int)s.charAt(i);
      charCode += (int)t.charAt(i);
    }
    return (char)charCode;
  }

  public char findTheDifference3(String s, String t) {
    int n = t.length();
    char c = t.charAt(n - 1);
    for (int i = 0; i < n - 1; ++i) {
      c ^= s.charAt(i);
      c ^= t.charAt(i);
    }
    return c;
  }
}
