package leetcode;

/**
 * find word1 word2 interchangeably
 */
public class ShortestWordDistance {
  class Solution {
    public int shortestDistance(String[] words, String word1, String word2) {
      int res = words.length;
      String currWord = null;
      int currIndex = 0;

      for (int i = 0; i < words.length; i++) {
        if (!(words[i].equals(word1) || words[i].equals(word2))) {
          continue;
        }
        if (currWord == null) {
          currWord = words[i];
        }
        if (!words[i].equals(currWord)) {
          res = Math.min(res, i - currIndex);
          currWord = words[i];
        }
        currIndex = i;
      }

      return res;
    }

    public int shortestDistance2(String[] words, String word1, String word2) {
      int p1 = -1, p2 = -1, min = Integer.MAX_VALUE;

      for (int i = 0; i < words.length; i++) {
        if (words[i].equals(word1))
          p1 = i;

        if (words[i].equals(word2))
          p2 = i;

        if (p1 != -1 && p2 != -1)
          min = Math.min(min, Math.abs(p1 - p2));
      }

      return min;
    }
  }
}
