package jiuzhang.binarysearch;

/**
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

 (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

 You are given a target value to search. If found in the array return its index, otherwise return -1.

 You may assume no duplicate exists in the array.
 */

public class SearchInRotatedSortedArray {
  public int search(int[] nums, int target) {
    if (nums == null || nums.length == 0) {    // must needed
      return -1;
    }

    int start = 0;
    int end = nums.length - 1;
    int last = nums[nums.length - 1];    // this is actual the cutting edge of an array

    if (target == last) {    // exclude last, then don't have to consider equal condition
      return end;
    }

    while (start + 1 < end) {
      int mid = start + (end - start) / 2;
      if (nums[mid] == target) {
        return mid;
      }
      if (nums[mid] > last) {
        if (last < target && target < nums[mid]) {
          end = mid;    // think twice, an easy part, but you did wrongly
        } else {
          start = mid;
        }
      } else {
        if (nums[mid] < target && target < last) {
          start = mid;
        } else {
          end = mid;
        }
      }
    }

    if (nums[start] == target) {
      return start;
    }
    if (nums[end] == target) {
      return end;
    }
    return -1;
  }
}
