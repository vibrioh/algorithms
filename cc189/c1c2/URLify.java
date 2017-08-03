package cc189.c1c2;

/**
 * Write a method to replace all spaces in a string with '%20'. You may assume that the string
 has sufficient space at the end to hold the additional characters, and that you are given the "true"
 length of the string. (Note: If implementing in Java, please use a character array so that you can
 perform this operation in place.)
 * EXAMPLE
 * Input: "Mr John Smith", 13
 * Output: "Mr%20John%20Smith"
 * <a href="https://stackoverflow.com/questions/32617609/the-value-of-length-when-replacing-space-with-20">source</a>
 * each space needs 2 shifts, in place means working within the char[] str
 */
public class URLify {
  public static int URLifyShift(char[] str, int length) {
    int spaceCount = 0;
    for (int i = 0; i < str.length; i++) {
      if (str[i] == ' ') {    // Use single quotes for literal char s, double quotes for literal String s, like so: char c = 'a'; String s = "hello"
        spaceCount++;
      }
    }
    int shift = 2 * spaceCount;
    int newLength = length + shift;
    for (int i = newLength - 1; shift > 0; i--) {
      char c = str[i - shift];
      if (c != ' ') {
        str[i] = c;
      } else {
        str[i] = '0';
        str[--i] = '2';    // a++ is known as postfix: add 1 to a, returns the old value.
        str[--i] = '%';    // ++a is known as prefix:  add 1 to a, returns the new value.
        shift -= 2;
      }
    }
    return newLength;
  }

  public static void main(String[] args) {
    char[] buffer = { 'M','r',' ','J','o','h','n',' ','S','m','i','t','h','*','*','*','*' };
    int inLen = 13;
    System.out.println("buffer: '" + new String(buffer) + "'");
    System.out.println("inLen : " + inLen);
    System.out.println("input : '" + new String(buffer, 0, inLen) + "'");
    int outLen = URLifyShift(buffer, inLen);
    System.out.println("outLen: " + outLen);
    System.out.println("result: '" + new String(buffer, 0, outLen) + "'");
  }
}
