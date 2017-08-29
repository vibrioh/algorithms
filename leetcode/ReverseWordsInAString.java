package leetcode;

public class ReverseWordsInAString {
  class Solution {
    public String reverseWords(String s) {
      String result = "";
      String[] words = s.split(" ");
      for (int i = 0; i < words.length; i++) {
        if (i != words.length - 1) {
          result += (reverseWord(words[i]) + " ");
        } else {
          result += reverseWord(words[i]);
        }
      }
      return result;
    }

    private String reverseWord(String word) {
      String reverse = "";
      for (int i = word.length() - 1; i >= 0; i--) {
        reverse += word.charAt(i);
      }
      return reverse;
    }
  }

  public class Solution2 {
    public String reverseWords(String s) {
      String words[] = s.split(" ");
      StringBuilder res=new StringBuilder();
      for (String word: words)
        res.append(new StringBuilder(word).reverse().toString() + " ");
      return res.toString().trim();
    }
  }


}
