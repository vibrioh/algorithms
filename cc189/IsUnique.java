package cc189;

/**
 * Is Unique: Implement an algorithm to determine if a string has all unique characters.
 * What if you cannot use additional data structures?
 * Also see <a href="http://www.geeksforgeeks.org/determine-string-unique-characters/">same</a>
 */
public class IsUnique {

  // Brute Force technique: Run 2 loops with variable i and j. Compare str[i] and str[j].
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

  //

  // Driver Code
  public static void main(String args[])
  {
    IsUnique obj = new IsUnique();
    String[] input = {
        "GeekforGeeks",
        "forever",
        "lost",
        "first",
        "doggy",
        "piggy",
        "  ",
        " ",
        ""
    };

    System.out.println("Brute Force");
    for (String s : input) {
      if (obj.isUniqueBruteForce(s))
        System.out.println("The String \"" + s
            + "\" has all unique characters.");
      else
        System.out.println("The String \"" + s
            + "\" has duplicate characters.");
    }
  }
}
