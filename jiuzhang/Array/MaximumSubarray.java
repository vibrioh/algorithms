package jiuzhang.Array;

public class MaximumSubarray {

  /**
   * @param nums: A list of integers
   * @return A integer indicate the sum of max subarray
   */
//  public int maxSubArray(int[] nums) {
//    int n = nums.length + 1;
//    int[] nSum = new int[n];
//
//    for (int i = 0; i < nums.length; i++) {
//      nSum[i + 1] = nSum[i] + nums[i];
//    }
//
//    int maxSum = Integer.MIN_VALUE;
//
//    for (int i = 0; i < nums.length; i++) {
//      for (int j = i + 1; j <= nums.length; j++) {
//        int sum = nSum[j] - nSum[i];
//        maxSum = Math.max(sum, maxSum);
//      }
//    }
//
//    return maxSum;
//  }

  /**
   * @param nums: A list of integers
   * @return: A integer indicate the sum of max subarray
   */
  public int maxSubArray(int[] nums) {
    int maxSum = Integer.MIN_VALUE;
    int minSum = 0;
    int sum = 0;
    for (int i = 0; i < nums.length; i++) {
      sum += nums[i];
      maxSum = Math.max(sum - minSum, maxSum);
      minSum = Math.min(sum, minSum);
    }
    return maxSum;
  }
}
