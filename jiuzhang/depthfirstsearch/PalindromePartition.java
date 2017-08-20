package jiuzhang.depthfirstsearch;

import java.util.ArrayList;
import java.util.List;

public class PalindromePartition {
  /**
   * @param s: A string
   * @return A list of lists of string
   */
  public List<List<String>> partition(String s) {
    List<List<String>> result = new ArrayList<>();

    if (s == null || s.length() == 0) {
      return result;
    }

    dfsHelper(s, 0, new ArrayList<String>(), result);

    return result;
  }

  private void dfsHelper(String s, int startIndex, List<String> palindromes, List<List<String>> result) {
    if (startIndex == s.length()) {
      result.add(new ArrayList<String>(palindromes));
      return;
    }

    for (int i = startIndex; i < s.length(); i++) {
      String sub = s.substring(startIndex, i + 1);
      if (isPalindrome(sub)) {
        palindromes.add(sub);
        dfsHelper(s, i + 1, palindromes, result);
        palindromes.remove(palindromes.size() - 1);
      }
    }
  }

  private boolean isPalindrome(String s) {
    int len = s.length();
    for (int i = 0; 2 * i < len; i++) {
      if (s.charAt(i) != s.charAt(len - 1 - i)) {
        return false;
      }
    }

    return true;
  }
}
