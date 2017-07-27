package cc189;

import java.lang.Math;
/**
 * One Away: There are three types of edits that can be performed on strings: insert a character,
 remove a character, or replace a character. Given two strings, write a function to check if they are
 one edit (or zero edits) away.
 EXAMPLE
 pale, ple -> true
 pales, pale -> true
 pale, bale -> true
 pale, bake -> false
 */
public class OneAway {

  public static boolean isOneAway(String str1, String str2) {
//    char[] charr1 = str1.toCharArray();
//    char[] charr2 = str2.toCharArray();
    int sLen = Math.min(str1.length(), str2.length());
    int bLen = Math.max(str1.length(), str2.length());
    if (bLen - sLen > 1) {
      return false;
    }
    // boolean isOne = (bLen - sLen == 0) ? false : true;     // ternary can be shortened
    boolean isOne = bLen - sLen != 0;    // if now length unequal, true.
    for (int i = 0; i < sLen; i++) {
      if (str1.charAt(i) != str2.charAt(i)) {
        if (isOne) {
          return false;
        } else {
          isOne = true;
        }
      }
    }
    return true;
  }


  public static void test(String a, String b, boolean expected) {
    boolean resultA = isOneAway(a, b);


    if (resultA == expected) {
      System.out.println(a + ", " + b + ": success   " + resultA);
    } else {
      System.out.println(a + ", " + b + ": error   " + resultA);
    }
  }

  public static void main(String[] args) {
    String[][] tests = {{"a", "b", "true"},
        {"", "d", "true"},
        {"d", "de", "true"},
        {"pale", "pse", "false"},
        {"acdsfdsfadsf", "acdsgdsfadsf", "true"},
        {"acdsfdsfadsf", "acdsfdfadsf", "true"},
        {"acdsfdsfadsf", "acdsfdsfads", "true"},
        {"acdsfdsfadsf", "cdsfdsfadsf", "true"},
        {"adfdsfadsf", "acdfdsfdsf", "false"},
        {"adfdsfadsf", "bdfdsfadsg", "false"},
        {"adfdsfadsf", "affdsfads", "false"},
        {"pale", "pkle", "true"},
        {"pkle", "pable", "false"}};
    for (int i = 0; i < tests.length; i++) {
      String[] test = tests[i];
      String a = test[0];
      String b = test[1];
      boolean expected = test[2].equals("true");

      test(a, b, expected);
      test(b, a, expected);
    }

  }
}
