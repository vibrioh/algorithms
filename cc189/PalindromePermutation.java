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
 * The key is limit one odd char number in a palindrome permutation.
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
    boolean isOne = false;
    for (Integer n : map.values()) {
      if (n%2 == 1) {
        if (isOne) {
          return false;
        }
        isOne = true;
      }
    }
    return true;
  }

  public static void main(String[] args) {
    String[] strings = {"Tact Coa",
        "Rats live on no evil star",
        "A man, a plan, a canal, panama",
        "Lleve",
        "Tacotac",
        "asda"};
    for (String s : strings) {
      boolean a = isPalindromePermutation(s);
      System.out.println(s + " :   " + a);
    }
  }
}
