package cc189;

import java.util.Map;
import java.util.HashMap;
/**
 * Palindrome Permutation: Given a string, write a function to check if it is a permutation of a palin­
 drome. A palindrome is a word or phrase that is the same forwards and backwards. A permutation
 is a rearrangement of letters. The palindrome does not need to be limited to just dictionary words.
 EXAMPLE
 Input: Tact Coa
 Output: True (permutations: "taco cat", "atco eta", etc.)
 */
public class PalindromePermutation {
  public static boolean isPalindromePermutation(String str) {
    char[] charray = str.toLowerCase().toCharArray();
    Map<Character, Integer> map = new HashMap<>();
    for (char c : charray) {
      if (c == ' ') {
        continue;
      }
      if (map.containsKey(c)) {
        map.put(c, map.get(c) + 1);
      } else {
        map.put(c, 1);
      }
    }
    int limit = 1;
    for (Integer n : map.values()) {
      if (n%2 != 0) {
        limit--;
        if (limit < 0) {
          return false;
        }
      }
    }
    return true;
  }

  public static void main(String[] args) {
    boolean b = isPalindromePermutation("Tact Coa");
    System.out.println(b);
  }
}
