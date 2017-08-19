package jiuzhang.depthfirstsearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSum {
  /**
   * @param candidates: A list of integers
   * @param target:An integer
   * @return A list of lists of integers
   */
  public List<List<Integer>> combinationSum(int[] candidates, int target) {
    List<List<Integer>> result = new ArrayList<>();    // List is an abstract object, pass/initialize instance with data structures

    if (candidates == null || candidates.length == 0) {
      return result;
    }

    int[] nums = removeDuplicates(candidates);

    dfsHelper(nums, 0, target, new ArrayList<Integer>(), result);    // List is an abstract object, pass/initialize instance with data structures

    return result;
  }

  // 1. 递归的定义
  // 从nums中的startIndex开始挑选一些数，放到combination中，且他们的和为target。
  private void dfsHelper( int[] nums,
                          int startIndex,
                          int remainedTarget,
                          List<Integer> combination,
                          List<List<Integer>> result) {
    // 3. 递归的出口
    if (remainedTarget == 0) {
      result.add(new ArrayList<Integer>(combination));
      // 可以不写return，循环中的if可以检验
      return;
    }

    // 2. 递归的拆解
    // [1, 2], [1, 3], [1, 4]... [1, 5]
    for (int i = startIndex; i < nums.length; i++) {
      if (remainedTarget > 0) {
        // [1] -> [1, 2]
        combination.add(nums[i]);
        // 把所有以[1, 2]开头的剩余和为remainedTarget的集合，找到并放入result中
        dfsHelper(nums, i, remainedTarget - nums[i], combination, result);
        // backtracking [1, 2] -> [1]
        combination.remove(combination.size() - 1);    // remove last indexed object
      }
    }
  }

  private int[] removeDuplicates(int[] candidates) {
    Arrays.sort(candidates);

    int index = 0;
    for (int i = 0; i < candidates.length; i++) {
      if (candidates[i] != candidates[index]) {
        candidates[++index] = candidates[i];
      }
    }

    int[] nums = new int[index + 1];
    for (int i = 0; i < nums.length; i++) {
      nums[i] = candidates[i];
    }

    return nums;
  }
}
