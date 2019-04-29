package fb;

/*
This problem is like a greedy problem. When you find nums[i-1] > nums[i] for some i, you will prefer to change nums[i-1]'s value, since a larger nums[i] will give you more risks that you get inversion errors after position i. But, if you also find nums[i-2] > nums[i], then you have to change nums[i]'s value instead, or else you need to change both of nums[i-2]'s and nums[i-1]'s values.
 */

public class NonDereasingArray {
    public boolean checkPossibility(int[] nums) {
        int n = 0;  //the number of changes
        for (int i = 0; i < nums.length - 1 && n <= 1; i++) {
            if (nums[i] > nums[i + 1]) {
                n++;
                if (i - 1 < 0 || nums[i + 1] >= nums[i - 1]) {
                    nums[i] = nums[i + 1];    //modify nums[i-1] of a priority
                } else {
                    nums[i + 1] = nums[i];    //have to modify nums[i]
                }
            }
        }
        return n <= 1;
    }
}
