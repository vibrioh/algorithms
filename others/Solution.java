package others;

import java.util.ArrayList;
import jiuzhang.binarytree.TreeNode;

import java.util.List;


public class Solution {
  /**
   * param n: As description.
   * return: A list of strings.
   */
  public ArrayList<String> fizzBuzz(int n) {
    ArrayList<String> results = new ArrayList<String>();
    for (int i = 1; i <= n; ++i) {
      if (i % 3 == 0 && i % 5 == 0) {
        results.add("fizz buzz");
      } else if (i % 3 == 0) {
        results.add("fizz");
      } else if (i % 5 == 0) {
        results.add("buzz");
      } else  {
        results.add(i + "");
      }
    }
    return results;
  }
}
