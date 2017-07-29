package cc189;

public class StringRotation {

  // is str1 is a substring of str2?
  public static boolean isSubstring(String str1, String str2) {
    if (str1.length() > str2.length()) {
      return false;
    }
    if (str1.length() == 0) {
      return true;
    }
    for (int i = 0; i < str2.length() - str1.length() + 1; i++) {    // you must check the end char!!!!!
      int equal = 0;
      while (str2.charAt(i+equal) == str1.charAt(equal)) {
        equal++;
        if (equal == str1.length()) {
          return true;
        }
      }
    }
    return false;
  }

  public static void main(String[] args) {
    String[] str1s = {"",
    " ",
    "love",
    "I",
    "I ",
    "e d",
    "!",
        "doggy!",
        "I love doggy!",
    "doggy! ",
    "I love doggy! "};
    String str2 = "I love doggy!";
    for (String str1 : str1s) {
      if (isSubstring(str1, str2)) {
        System.out.printf("{%s} is a subset of {%s}\n", str1, str2);
      } else {
        System.out.printf("{%s} is NOT a subset of {%s}\n", str1, str2);
      }
    }
  }
}
