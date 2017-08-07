package jiuzhang;

public class BinarySearch {

  /**
   * Find any/first/last position of a target number in a sorted array. Return -1 if target does not exist.
   *
   * @param nums an integer array sorted in ascending order
   * @param target an integer
   * @return an integer
   */
  public int findPosition(int[] nums, int target) {
    if (nums == null || nums.length == 0) {
      return -1;
    }

    int start = 0;
    int end = nums.length - 1;

    /**
     * 【start + 1 < end】 相邻就退出
     * start = 0, end = 1, 就退出
     * [2, 2], target = 2
     * mid = 0 + (1 - 0) / 2 = 0, start = mid 死循环
     */
    while (start + 1 < end) {
      // int mid = (end + start) / 2, if start, end ~ 2^31, then out of range
      int mid = start + (end - start) / 2;
      if (nums[mid] == target) {
        /**
         * switch any/first/last
         */
        return mid;    // for any
//        end = mid;    // for first position
//        start = mid;    // if start < end; dead loop

      } else if (nums[mid] < target) {
        // end = mid -1; works as well
        end = mid;
      } else {
        // start = mid + 1; works as well
        start = mid;
      }
    }

    // double check 最后相邻的情况，while中往往无法直接输出结果。
    // 输出需要的结果， 输出start（first）在前还是end（first）在前。
    if (nums[start] == target) {
      return start;
    }
    if (nums[end] == target) {
      return end;
    }

    return -1;
  }
}
