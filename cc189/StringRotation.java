package cc189;

public class StringRotation {

  // is str1 is a substring of str2?
  public static boolean isSubString(String str1, String str2) {
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

  // just concatenate str1
  public static boolean isStringRotation(String str1, String str2) {
//    String str1str1 = str1 + str1;
//    System.out.println(str3);
    return isSubString(str2, str1 + str1);
  }

//  public static String stringSort(String str) {    // Arrays.sort() void!!
//    char[] charr = str.toCharArray();
//    Arrays.sort(charr);
//    return new String(charr);
//  }

  // rotation changed the order, so sort would work
  /**
   * only sort won't work, lost original order info
   */
//  public static boolean isStringRotation(String str1, String str2) {
//    if (str1.length() != str2.length()) {
//      return false;
//    }
//    if (isSubString(stringSort(str1), stringSort(str2))) {
//      return true;
//    }
//    return false;
//  }

  public static boolean isStringRevers(String str1, String str2) {    // this is not the problem asked
    if (str1.length() != str2.length()) {
      return false;
    }
    for (int i = 0; i < str1.length(); i++) {
//      System.out.printf("[%s][%s] \n", str1.charAt(i), str2.charAt(str1.length() - 1 - i));
      if (str1.charAt(i) != str2.charAt(str1.length() - 1 - i)) {
        return false;
      }
    }
    return true;
  }

  public static void main(String[] args) {
    String[] str1s = {"", " ", "love", "I", "I ", "e d", "!", "doggy!", "I love doggy!", "doggy! ", "I love doggy! "};
    String str2 = "I love doggy!";
    for (String str1 : str1s) {
      if (isSubString(str1, str2)) {
        System.out.printf("{%s} is a subset of {%s}\n", str1, str2);
      } else {
        System.out.printf("{%s} is NOT a subset of {%s}\n", str1, str2);
      }
    }

    System.out.println("------------------------------------------------------");

    String[] str3s = {"elttobretaw", "elttobretaw ", "elttobreta", "waterbottle", "", " ", "elttobretaw"};
    String str4 = "waterbottle";
    for (String str3 : str3s) {
      if (isStringRevers(str3, str4)) {
        System.out.printf("{%s} is a revers of {%s}\n", str3, str4);
      } else {
        System.out.printf("{%s} is NOT a revers of {%s}\n", str3, str4);
      }
    }

    System.out.println("------------------------------------------------------");

    String[] str5s = {"elttobretaw", "elttobretaw ", "elttobreta", "waterbottle", "", " ", "elttobretaw", "rbottlewate"};
    String str6 = "waterbottle";
    for (String str5 : str5s) {
      if (isStringRotation(str5, str6)) {
        System.out.printf("{%s} is a rotation of {%s}\n", str5, str6);
      } else {
        System.out.printf("{%s} is NOT a rotation of {%s}\n", str5, str6);
      }
    }
  }
}
