package fb;

public class MaximumSumOf3NonOverlappingSubarrays {
    /*
    将[0, length-1]的区间划分为left, i and right

    首先计算长度为K的子串的sum，index为子串的最后一位
    然后计算left的max sum，index为子串的最后一位
    然后计算right的max sum，index为子串的最后一位

    以sum[i]遍历一遍，打擂台
    sum = sum[left[i - k]] + sum[i] + sum[right[i + k]]
     */
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int[] result = new int[3];
        if (nums == null || nums.length == 0) {
            return result;
        }

        int length = nums.length;
        int[] sum = new int[length];

        // k prefix
        sum[0] = nums[0];
        for (int i = 1; i < length; i++) {
            sum[i] = sum[i - 1] + nums[i];
            if (i >= k) {
                sum[i] -= nums[i - k];
            }
        }

        // left max end index
        int[] left = new int[length];
        int leftMaxIndex = k - 1;
        for (int i = k - 1; i < length; i++) {
            left[i] = sum[i] > sum[leftMaxIndex] ? i : leftMaxIndex;
            leftMaxIndex = left[i];
        }

        // right max end index
        int[] right = new int[length];
        int rightMaxIndex = length - 1;
        for (int i = length - 1; i >= k - 1; i--) {
            right[i] = sum[i] >= sum[rightMaxIndex] ? i : rightMaxIndex;
            rightMaxIndex = right[i];
        }

        int max = Integer.MIN_VALUE;
        // scan
        for (int i = 2 * k - 1; i < length - k; i++) {
            int sum3 = sum[left[i - k]] + sum[i] + sum[right[i + k]];
            if (sum3 > max) {
                max = sum3;
                result[0] = left[i - k] - k + 1;
                result[1] = i - k + 1;
                result[2] = right[i + k] - k + 1;
            }
        }

        return result;
    }
}
