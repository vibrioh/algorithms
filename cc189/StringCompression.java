package cc189;

/**
 * String Compression: Implement a method to perform basic string compression using the counts
 of repeated characters. For example, the string aabcccccaaa would become a2blc5a3. If the
 "compressed" string would not become smaller than the original string, your method should return
 the original string. You can assume the string has only uppercase and lowercase letters (a - z).
 * Consider the beginning, forget the ending!!!!!
 */
public class StringCompression {

  public static String stringCompressionBuilder(String str) {
    StringBuilder strBldr = new StringBuilder();
    strBldr.append(str.charAt(0));
    int repeat = 1;
    for (int i = 1; i < str.length(); i++) {
      if (str.charAt(i) == str.charAt(i - 1)) {
        repeat += 1;
      } else {
        strBldr.append(repeat);
        strBldr.append(str.charAt(i));
        repeat = 1;
      }
    }
    strBldr.append(repeat);
    return (strBldr.length() < str.length()) ? strBldr.toString() : str;
  }

  public static void main(String[] args) {
    String[] str = {"aaaaabbbbaaaabbddcddddddddd",
        "asdfjkll",
        "!@@@@@@@@$%^^^^^^^^^*&()))",
        "asdfjkl;asdlfadasdfsssssssssdddd",
        "94nm0900dfdddddiek"};
    for (String s : str) {
      System.out.println(s + ":   " + stringCompressionBuilder(s));
    }
  }
}
