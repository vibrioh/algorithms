package jiuzhang.depthfirstsearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PermutationsII {
  /**
   * How to remove duplicates?
   * 1. sorted array
   * 2. if the first time appear, and used, then skip
   * 第一次用过了，就不要再用了。
   *
   * @param nums: A list of integers.
   * @return A list of unique permutations.
   */
  public List<List<Integer>> permuteUnique(int[] nums) {
    List<List<Integer>> result = new ArrayList<>();

    if (nums == null) {
      return result;
    }

    Arrays.sort(nums);

    dfsHelper(nums, new boolean[nums.length], new ArrayList<Integer>(), result);

    return result;
  }

  private void dfsHelper(int[] nums, boolean[] added, List<Integer> sequence, List<List<Integer>> result) {
    if (sequence.size() == nums.length) {
      result.add(new ArrayList<Integer>(sequence));
      return;
    }

    for (int i = 0; i < nums.length; i++) {
      // index can not less than 0
      // if nums[i - 1] has been added, won't add the same nums[i] for the next recursion
      if (i != 0 && nums[i] == nums[i -1] && added[i - 1]) {
        continue;
      }
      if (!added[i] && sequence.size() < nums.length) {
        sequence.add(nums[i]);
        added[i] = true;
        dfsHelper(nums, added, sequence, result);
        sequence.remove(sequence.size() - 1);
        added[i] = false;
      }
    }
  }
}
