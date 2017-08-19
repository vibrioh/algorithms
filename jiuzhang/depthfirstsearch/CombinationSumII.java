package jiuzhang.depthfirstsearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSumII {
  /**
   * @param candidates: Given the candidate numbers
   * @param target: Given the target number
   * @return: All the combinations that sum to target
   */
  public List<List<Integer>> combinationSum2(int[] candidates, int target) {
    List<List<Integer>> result = new ArrayList<>();    // List is an abstract object, pass/initialize instance with data structures

    if (candidates == null || candidates.length == 0) {
      return result;
    }

    Arrays.sort(candidates);

    int[] nums = candidates;

    dfsHelper(nums, 0, target, new ArrayList<Integer>(), result);    // List is an abstract object, pass/initialize instance with data structures

    return result;
  }


  private void dfsHelper( int[] nums,
      int startIndex,
      int remainedTarget,
      List<Integer> combination,
      List<List<Integer>> result) {

    if (remainedTarget == 0) {
      result.add(new ArrayList<Integer>(combination));
      return;
    }


    for (int i = startIndex; i < nums.length; i++) {
      if (i != startIndex && nums[i] == nums[i - 1]) {    // 选代表，如果重复，永远只加作为起始的index的那个
        continue;
      }
      if (remainedTarget > 0) {
        combination.add(nums[i]);
        dfsHelper(nums, i + 1, remainedTarget - nums[i], combination, result);
        combination.remove(combination.size() - 1);    // remove last indexed object
      }
    }
  }
}
