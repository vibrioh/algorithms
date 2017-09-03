package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * index interchanges with element
 */

public class FindAllNumbersDisapperaedInAnArray {
  class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums) {
      List<Integer> res = new ArrayList<>();
      for (int i = 0; i < nums.length; i++) {
        int num = nums[i];
        while (num != 0) {
          int nextNum = nums[num - 1];
          nums[num - 1] = 0;
          num = nextNum;
        }
      }

      for (int i = 0; i < nums.length; i++) {
        if (nums[i] != 0) {
          res.add(i + 1);
        }
      }

      return res;
    }
  }

  public List<Integer> findDisappearedNumbers(int[] nums) {
    List<Integer> ret = new ArrayList<Integer>();

    for(int i = 0; i < nums.length; i++) {
      int val = Math.abs(nums[i]) - 1;
      if(nums[val] > 0) {
        nums[val] = -nums[val];
      }
    }

    for(int i = 0; i < nums.length; i++) {
      if(nums[i] > 0) {
        ret.add(i+1);
      }
    }
    return ret;
  }
}
