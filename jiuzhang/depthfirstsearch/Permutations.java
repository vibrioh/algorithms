package jiuzhang.depthfirstsearch;

import java.util.ArrayList;
import java.util.List;

public class Permutations {
  /**
   * @param nums: A list of integers.
   * @return A list of permutations.
   */
  public List<List<Integer>> permute(int[] nums) {
    List<List<Integer>> result = new ArrayList<>();

    if (nums == null) {    // [[]] is an answer for []
      return result;
    }

    // pass a boolean[] to hold if the element is used already
    dfsHelper(nums, new boolean[nums.length], new ArrayList<Integer>(), result);

    return result;
  }

  private void dfsHelper(int[] nums, boolean[] added, List<Integer> sequence, List<List<Integer>> result) {
    if (sequence.size() == nums.length) {
      result.add(new ArrayList<Integer>(sequence));
      return;
    }

    for (int i = 0; i < nums.length; i++) {
      if (!added[i] && sequence.size() < nums.length) {
        sequence.add(nums[i]);
        added[i] = true;
        dfsHelper(nums, added, sequence, result);
        sequence.remove(sequence.size() - 1);    // remove the last one, don't make mistake
        added[i] = false;
      }
    }
  }
}
