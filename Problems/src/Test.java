import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
        Solutions s = new Solutions();
        System.out.println(Arrays.toString(s.twoSum(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 6)));
        System.out.println(s.lengthOfLongestSubstring("burnfbdunvbfjeikdcjfidkc"));
        System.out.println(s.findMedianSortedArrays(new int[]{2, 3, 56, 555, 3235}, new int[]{4, 5, 7, 8, 11, 67}));
        System.out.println(s.longestPalindrome("bb"));
        System.out.println(s.convert("0123456789", 1));
        System.out.println(s.reverse(1534236469));
        System.out.println(s.myAtoi("9223372036854775808"));
        System.out.println(s.isPalindrome(123454321));
        System.out.println(s.maxArea(new int[]{15000, 14999, 14998, 14997, 14996, 14995, 14994, 14993, 14992, 14991}));
        System.out.println(s.intToRoman(3333));
        System.out.println(s.romanToInt("MVIXII"));
        System.out.println(s.longestCommonPrefix(new String[]{"flower", "flow", "flight"}));
        System.out.println(s.threeSum(new int[]{-2, -1, -1, 0, 1, 1, 2, 4}));
        System.out.println(s.threeSumClosest(new int[]{-2, -1, -1, 0, 1, 1, 2, 4}, 5));
        System.out.println(s.letterCombinations("9388"));
        System.out.println(s.divide(-2147483648, -1));
        System.out.println(Arrays.toString(s.searchRange(new int[]{2, 3, 4, 5, 6, 6, 6, 6, 7, 9}, 6)));
    }
}

