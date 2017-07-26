package cc189;

import java.util.Arrays;

public class CheckPermutation {
  /**
   * Given two strings, write a method to decide if one is a permutation of the other.
   * Sort both the strings and compare it.
   * Time complexity: O(n log n)
   */
  public static boolean checkPermutaion(String str1, String str2) {
//    char[] charry1 = str1.toCharArray();
//    char[] charry2 = str2.toCharArray();
//    Arrays.sort(charry1);
//    Arrays.sort(charry2);
    return Arrays.equals(stringToSortedArray(str1), stringToSortedArray(str2));
  }

  public static char[] stringToSortedArray(String s) {
    char[] charry = s.toCharArray();
    Arrays.sort(charry);
    return charry;
  }

  public static void main(String[] args) {
    String[][] pairs = {{"apple", "papel"}, {"carrot", "tarroc"}, {"hello", "llloh"}};
    for (String[] pair : pairs) {
      String word1 = pair[0];
      String word2 = pair[1];
      boolean anagram = checkPermutaion(word1, word2);
      System.out.println(word1 + ", " + word2 + ": " + anagram);
    }
  }
}
