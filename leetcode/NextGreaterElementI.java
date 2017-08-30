package leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class NextGreaterElementI {
  class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
      if (nums1 == null || nums2 == null) {
        return null;
      }

      int[] res = new int[nums1.length];
      Map<Integer, Integer> resMap = new HashMap<>();
      Stack<Integer> greaters = new Stack<>();

      for (int i = nums2.length - 1; i >= 0; i--) {

        while (!greaters.isEmpty() && greaters.peek() < nums2[i]) {
          greaters.pop();
        }

        int nextGreater = -1;
        if (!greaters.isEmpty()) {
          nextGreater = greaters.peek();
        }

        resMap.put(nums2[i], nextGreater);
        greaters.push(nums2[i]);
      }

      for (int i = 0; i < nums1.length; i++) {
        res[i] = resMap.get(nums1[i]);
      }

      return res;
    }
  }

  public class Solution2 {
    public int[] nextGreaterElement(int[] findNums, int[] nums) {
      Stack < Integer > stack = new Stack< >();
      HashMap< Integer, Integer > map = new HashMap < > ();
      int[] res = new int[findNums.length];
      for (int i = 0; i < nums.length; i++) {
        while (!stack.empty() && nums[i] > stack.peek())
          map.put(stack.pop(), nums[i]);
        stack.push(nums[i]);
      }
      while (!stack.empty())
        map.put(stack.pop(), -1);
      for (int i = 0; i < findNums.length; i++) {
        res[i] = map.get(findNums[i]);
      }
      return res;
    }
  }
}
