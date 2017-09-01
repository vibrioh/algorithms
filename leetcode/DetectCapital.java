package leetcode;

public class DetectCapital {
  class Solution {
    public boolean detectCapitalUse(String word) {
      if (word.length() == 1) {
        return true;
      }
      for (int i = 1; i < word.length() - 1; i++) {
        if (Character.isUpperCase(word.charAt(i)) != Character.isUpperCase(word.charAt(i + 1))) {
          return false;
        }
      }

      if (!Character.isUpperCase(word.charAt(0)) && Character.isUpperCase(word.charAt(1))) {
        return false;
      }

      return true;
    }
  }

  class Solution2 {
    public boolean detectCapitalUse(String word) {
      return word.matches("[A-Z]+|[a-z]+|[A-Z][a-z]+");
    }
  }
}
