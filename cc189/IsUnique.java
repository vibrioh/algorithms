package cc189;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Is Unique: Implement an algorithm to determine if a string has all unique characters.
 * What if you cannot use additional data structures?
 * Also see <a href="http://www.geeksforgeeks.org/determine-string-unique-characters/">same</a>
 * Note: Please note that the program is case sensitive.
 */
public class IsUnique {


  // Approach 1 –- Brute Force technique: Run 2 loops with variable i and j. Compare str[i] and str[j].
  // If they become equal at any point, return false.
  // Time Complexity: O(N^2)
  public boolean isUniqueBruteForce(String str) {
    for (int i = 0; i < str.length(); i++) {    // String length() vs Array length
      for (int j = i + 1; j < str.length(); j++) {
        if (str.charAt(i) == str.charAt(j)) {    // String charAt(i) vs Array [i]
          return false;
        }
      }
    }
    return true;
  }


  // Approach 2 – Sorting: Using sorting based on ASCII values of characters
  // Time Complexity: O(n log n)
  // Convert the string to character array for sorting
  // Practical result is bad.
  public boolean isUniqueCharArray(String str)
  {
    char [] chArray = str.toCharArray();

    // Using sorting
    Arrays.sort(chArray);

    for(int i=0; i<chArray.length-1; i++)
    {
      if (chArray[i] == chArray[i+1])
        return false;
    }
    return true;
  }


  // Approach x -- HashSet: using this structure seems better.
  // Time Complexity: O(n)
  public boolean isUniqueHashSet(String str) {
    Set<Character> set = new HashSet<>();
    for (int i = 0; i < str.length(); i++) {
      if (set.contains(str.charAt(i))) {
        return false;
      }
      else {
        set.add(str.charAt(i));
      }
    }
    return true;
  }


  // Approach 3 –- Use of Extra Data Structure: This approach assumes ASCII char set(8 bits). The idea is to maintain a boolean array for the characters. The 256 indices represent 256 characters. All the array elements are initially set to false. As we iterate over the string, set true at the index equal to the int value of the character. If at any time, we encounter that the array value is already true, it means the character with that int value is repeated.
  // Time Complexity: O(n)
  final static int MAX_CHAR = 256;

  boolean isUnique256(String str)
  {
    // If length is greater than 265,
    // some characters must have been repeated
    if (str.length() > MAX_CHAR)
      return false;

    boolean[] chars = new boolean[MAX_CHAR];
    Arrays.fill(chars, false);

    for (int i=0; i<str.length(); i++)
    {
      int index = (int) str.charAt(i);

            /* If the value is false, set it true */
      if (!chars[index])
        chars[index] = true;

            /* If the value is already true, string
               has duplicate characters, return false */
      else
        return false;
    }

        /* No duplicates encountered, return true */
    return true;
  }


  // Approach 4 – Without Extra Data Structure: The approach is valid for strings having alphabet as a-z. This approach is little tricky. Instead of maintaining a boolean array, we maintain an integer value called checker(32 bits). As we iterate over the string, we find the int value of the character with respect to ‘a’ with the statement int bitAtIndex = str.charAt(i)-‘a’;
  // Then the bit at that int value is set to 1 with the statement 1<<bitAtIndex .
  // Now, if this bit is already set in the checker, the bit AND operation would make checker > 0. Return false in this case.
  // Else Update checker to make the bit 1 at that index with the statement checker = checker | (1<<bitAtIndex);
  // Time Complexity: O(n)
  boolean isUniqueBitwise(String str) {
    // Assuming string can have characters a-z
    // this has 32 bits set to 0
    int checker = 0;

    for (int i = 0; i < str.length(); i++) {
      int bitAtIndex = str.charAt(i) - 'a';

      // if that bit is already set in checker,
      // return false
      if ((checker & (1 << bitAtIndex)) > 0)
        return false;

      // otherwise update and continue by
      // setting that bit in the checker
      checker = checker | (1 << bitAtIndex);
    }

    // no duplicates encountered, return true
    return true;
  }













  // Driver Code
  public static void main(String args[])
  {
    IsUnique obj = new IsUnique();
    String[] input = {
        "GeeksforGeeks",
        "forever",
        "lost",
        "first",
        "doggy",
        "piggy",
        "  ",
        " ",
        "",
        "哈哈",
        "1234567890-=`qwertyuiop[]asdfghjkl;'zxcvbnm,./!@#$%^&*()_+~QWERTYUIOP{}|:LKJHGFDSAZXCVBNM<>?"
    };

    System.out.println("Brute Force");
    long startTime = System.nanoTime();
    for (String s : input) {
      if (obj.isUniqueBruteForce(s))
        System.out.println("The String \"" + s
            + "\" has all unique characters.");
      else
        System.out.println("The String \"" + s
            + "\" has duplicate characters.");
    }
    long duration = System.nanoTime() - startTime;
    System.out.println("Duration: " + duration + " (ns)\n");

    System.out.println("Char Sorting Array");
    long startTime1 = System.nanoTime();
    for (String s : input) {
      if (obj.isUniqueCharArray(s))
        System.out.println("The String \"" + s
            + "\" has all unique characters.");
      else
        System.out.println("The String \"" + s
            + "\" has duplicate characters.");
    }
    long duration1 = System.nanoTime() - startTime1;
    System.out.println("Duration: " + duration1 + " (ns)\n");

    System.out.println("HashSet");
    long startTime2 = System.nanoTime();
    for (String s : input) {
      if (obj.isUniqueHashSet(s))
        System.out.println("The String \"" + s
            + "\" has all unique characters.");
      else
        System.out.println("The String \"" + s
            + "\" has duplicate characters.");
    }
    long duration2 = System.nanoTime() - startTime2;
    System.out.println("Duration: " + duration2 + " (ns)\n");

    System.out.println("256");
    long startTime3 = System.nanoTime();
    for (String s : input) {
      if (obj.isUniqueHashSet(s))
        System.out.println("The String \"" + s
            + "\" has all unique characters.");
      else
        System.out.println("The String \"" + s
            + "\" has duplicate characters.");
    }
    long duration3 = System.nanoTime() - startTime3;
    System.out.println("Duration: " + duration3 + " (ns)\n");

    System.out.println("Bitwise");
    long startTime4 = System.nanoTime();
    for (String s : input) {
      if (obj.isUniqueHashSet(s))
        System.out.println("The String \"" + s
            + "\" has all unique characters.");
      else
        System.out.println("The String \"" + s
            + "\" has duplicate characters.");
    }
    long duration4 = System.nanoTime() - startTime4;
    System.out.println("Duration: " + duration4 + " (ns)\n");
  }
}
