package cc189;

/**
 * String Compression: Implement a method to perform basic string compression using the counts
 of repeated characters. For example, the string aabcccccaaa would become a2blc5a3. If the
 "compressed" string would not become smaller than the original string, your method should return
 the original string. You can assume the string has only uppercase and lowercase letters (a - z).
 *
 * Consider the beginning, forget the ending!!!!!
 */
public class StringCompression {

  public static String stringCompressionBuilder(String str) {
//    StringBuilder strBldr = new StringBuilder();
//    strBldr.append(str.charAt(0));
    String strComp = "" + str.charAt(0);
    int repeat = 1;
    for (int i = 1; i < str.length(); i++) {
      if (str.charAt(i) == str.charAt(i - 1)) {
        repeat += 1;
      } else {
//        strBldr.append(repeat);
//        strBldr.append(str.charAt(i));
        strComp += "" + repeat + str.charAt(i);
        repeat = 1;
      }
    }
//    strBldr.append(repeat);
    strComp += "" + repeat;
//    return (strBldr.length() < str.length()) ? strBldr.toString() : str;
    return (strComp.length() < str.length()) ? strComp : str;
  }

  public static String compressBad(String str) {
    String compressedString = "";
    int countConsecutive = 0;
    for (int i = 0; i < str.length(); i++) {
      countConsecutive++;

			/* If next character is different than current, append this char to result.*/
      if (i + 1 >= str.length() || str.charAt(i) != str.charAt(i + 1)) {
        compressedString += "" + str.charAt(i) + countConsecutive;
        countConsecutive = 0;
      }
    }
    return compressedString.length() < str.length() ? compressedString : str;
  }


  public static void main(String[] args) {
    String[] str = {"aaaaabbbbaaaabbddcddddddddd",
        "asdfjkll",
        "!@@@@@@@@$%^^^^^^^^^*&()))",
        "asdfjkl;asdlfadasdfsssssssssdddd",
        "94nm0900dfdddddiek"};
    for (String s : str) {
      System.out.println(s + ":   " + stringCompressionBuilder(s) + " | " + compressBad(s));
    }
  }
}
