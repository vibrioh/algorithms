package ggl;

public class SplitArrayLargestSum {
    /*
    Binary Search:
    这个题目乍一看似乎没啥思路...因为看起来这个比较像是枚举每种解法的暴力题解...

    但其实是二分法:

    这是个二分答案, 二分的区域左边是这个数组中最大的那个数, 因为我们可以吧数组每个数分成一个子数组

    二分区域的右边是这整个数组的和, 因为数组可以只分成一个大的数组

    二分的条件是, 假设最大的和是sum, 那么能把整个数组分成的个数n, 和给定的数组个数限制m相比

    如果n < m, 那么证明我们取的和太宽了, 导致分成的数组个数小于limit, 那么我们就得缩小和, 也就是right = mid

    如果n > m, 那么证明我们取得和太窄了, 导致分成的数组个数大于limit, 我们就需要扩大和和, 也就是left = mid

    如果n == m, 证明我们取得和刚合适, 左边右边应该都可以, 在这里我们选择左边, 注意如果选择一边的话, 那么对另一边来说mid就是不合理的, 所以要调整mid

    最后验证得到的答案即可
     */
    public int splitArray(int[] nums, int m) {
        // write your code here
        if (nums == null || nums.length == 0) {
            return 0;
        }

        long left = max(nums);
        long right = sum(nums);

        //二分和
        while (left < right) {
            long mid = left + (right - left) / 2;

            //如果选出来的数组的个数小于等于m, 说明
            //我们选的mid太大了, 我们要缩小数组能接受的和
            //所以我们移动right
            if (countLessOrEqual(nums, mid) <= m) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        //这个验证不是必须的, 不过为了严谨起见放在这里
        if (countLessOrEqual(nums, left) <= m) {
            return (int) left;
        } else if (countLessOrEqual(nums, right) <= m) {
            return (int) right;
        } else {
            return -1;
        }
    }

    private long max(int[] nums) {
        long max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            max = Math.max(max, nums[i]);
        }

        return max;
    }

    private long sum(int[] nums) {
        long sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }

        return sum;
    }

    private int countLessOrEqual(int[] nums, long target) {
        int i = 0;
        long local = 0;
        int res = 0;

        //统计和小于等于给定target的连续数组个数
        //算法就是不断地把数加到local里面, 一旦超过target, 就重置local
        while (i < nums.length) {
            local += nums[i];

            if (local > target) {
                res++;
                local = 0;
                continue;
            }

            i++;
        }

        //最后一步的和别忘记处理
        if (local <= target) {
            res++;
        }

        return res;
    }
}
