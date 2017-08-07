package leetcode;

/**
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

 (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

 Find the minimum element.

 You may assume no duplicate exists in the array.
 */
public class FindMinimumInRotatedSortedArray {

  /**
   * 第一境界OOXX, 具象化问题，找到符合二分的条件
   * @param nums a rotated sorted array
   * @return integer of the minimum element
   */
  public int findMin(int[] nums) {
    int start = 0;
    int end = nums.length - 1;

    while (start + 1 < end) {
      int mid = start + (end - start) / 2;
      if (nums[mid] > nums[nums.length - 1]) {
        start = mid;
      } else {
        end = mid;
      }
    }

    if (nums[start] > nums[end]) {
      return nums[end];
    }

    return nums[start];
  }
}
