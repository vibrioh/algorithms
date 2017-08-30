package leetcode;

public class ReshapeTheMatrix {
  class Solution {
    public int[][] matrixReshape(int[][] nums, int r, int c) {
      if (nums.length * nums[0].length != r * c) {
        return nums;
      }
      int[][] res = new int[r][c];

      int i = 0;
      int j = 0;
      for (int[] row : nums) {
        for (int num : row) {
          res[i][j] = num;
          j++;
          if (j == c) {
            j = 0;
            i++;
          }
        }
      }
      return res;
    }
  }
}
