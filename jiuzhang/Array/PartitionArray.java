package jiuzhang.Array;

public class PartitionArray {
  /**
   * index is always the nums[index] >= k, before are all nums[<index] < k
   * @param nums: The integer array you should partition
   * @param k: An integer
   * @return The index after partition
   */
  public int partitionArray(int[] nums, int k) {
    int index = 0;
    for (int i = 0; i < nums.length; i++) {    // i start at zero, because nums[0] needs to be if < k, index++
      if (nums[i] < k) {
        int temp = nums[i];
        nums[i] = nums[index];
        nums[index] = temp;
        index++;
      }
    }
    return index;
  }
}
