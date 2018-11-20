import javax.lang.model.type.NullType;
import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.*;

public class Solutions {

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }


    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                return new int[]{map.get(nums[i]), i};
            }
            map.put(target - nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode head = dummyHead;
        int carry = 0;
        while (l1 != null || l2 != null || carry != 0) {
            int sum = 0;
            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }
            sum += carry;
            carry = sum / 10;
            head.next = new ListNode(sum % 10);
            head = head.next;
        }
        return dummyHead.next;
    }

    public int lengthOfLongestSubstring(String s) {
        int len_max = 0;
        int index_start_curr = 0;
        Map<Character, Integer> charMap = new HashMap<>();
        char[] charList = s.toCharArray();
        for (int i = 0; i < charList.length; i++) {
            char char_curr = charList[i];
            if (charMap.containsKey(char_curr) && charMap.get(char_curr) >= index_start_curr) {
                index_start_curr = charMap.get(char_curr) + 1;
            }
            charMap.put(char_curr, i);
            len_max = Math.max(len_max, i - index_start_curr + 1);
        }
        return len_max;
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }
        int len = nums1.length + nums2.length;
        int cut1 = 0;
        int cut2 = 0;
        int cutL = 0;
        int cutR = nums1.length;
        while (cut1 <= nums1.length) {
            cut1 = (cutR - cutL) / 2 + cutL;
            cut2 = len / 2 - cut1;
            double L1 = (cut1 == 0) ? Integer.MIN_VALUE : nums1[cut1 - 1];
            double L2 = (cut2 == 0) ? Integer.MIN_VALUE : nums2[cut2 - 1];
            double R1 = (cut1 == nums1.length) ? Integer.MAX_VALUE : nums1[cut1];
            double R2 = (cut2 == nums2.length) ? Integer.MAX_VALUE : nums2[cut2];
            if (L1 > R2) {
                cutR = cut1 - 1;
            } else if (L2 > R1) {
                cutL = cut1 + 1;
            } else {
                if (len % 2 == 0) {
                    L1 = (L1 > L2) ? L1 : L2;
                    R1 = (R1 < R2) ? R1 : R2;
                    return (L1 + R1) / 2;
                } else {
                    R1 = (R1 < R2) ? R1 : R2;
                    return R1;
                }
            }
        }
        return -1;
    }

    public String longestPalindrome(String s) {
        int len = s.length();
        if (len <= 1) {
            return s;
        }
        int idxStart = 0;
        int lenMax = 0;
        for (int i = 0; i < len; i++) {
            int len1 = checkPalindrome(s, i, i);
            int len2 = checkPalindrome(s, i, i + 1);
            int lenCurr = Math.max(len1, len2);
            idxStart = (lenCurr > lenMax) ? i : idxStart;
            lenMax = (lenCurr > lenMax) ? lenCurr : lenMax;
        }
        return s.substring(idxStart - (lenMax - 1) / 2, idxStart + lenMax / 2 + 1);
    }

    private int checkPalindrome(String s, int i, int j) {
        int lenMax = 0;
        while (i >= 0 && j < s.length()) {
            if (s.charAt(i) == s.charAt(j)) {
                lenMax = (j - i + 1 > lenMax) ? j - i + 1 : lenMax;
                i--;
                j++;
            } else {
                return lenMax;
            }
        }
        return lenMax;
    }

    public String convert(String s, int numRows) {
        if (numRows >= s.length() || numRows == 1) {
            return s;
        }
        StringBuilder res = new StringBuilder(s.length());
        for (int i = 0; i < numRows; i++) {
            res.append(s.charAt(i));
            int j = 1;
            if (i == 0 || i == numRows - 1) {
                while (i + 2 * (numRows - 1) * j < s.length()) {
                    res.append(s.charAt(i + 2 * (numRows - 1) * j));
                    j++;
                }
            } else {
                System.out.println(i + "-" + j);
                while (2 * (numRows - 1) * j - i < s.length()) {
                    res.append(s.charAt(2 * (numRows - 1) * j - i));
                    if (i + 2 * (numRows - 1) * j < s.length()) {
                        res.append(s.charAt(i + 2 * (numRows - 1) * j));
                    }
                    j++;
                }
            }
        }
        return res.toString();
    }

    public int reverse(int x) {
        long resl = 0;
        while (x != 0) {
            resl = resl * 10 + x % 10;
            x /= 10;
        }
        int res = (int) resl;
        return (res == resl) ? res : 0;
    }

    public int myAtoi(String str) {
        if (str == null) {
            return 0;
        }

        str = str.trim();

        if (str.length() == 0) {
            return 0;
        }

        int sign = 1;
        int idx = 0;
        long res = 0;

        if (str.charAt(idx) == '+') {
            idx++;
        } else if (str.charAt(idx) == '-') {
            sign = -1;
            idx++;
        }

        for (; idx < str.length(); idx++) {
            if (str.charAt(idx) < '0' || str.charAt(idx) > '9') {
                break;
            }
            res = res * 10 + (str.charAt(idx) - '0');
            if (res > Integer.MAX_VALUE) {
                break;
            }
        }

        res *= sign;

        if (res > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }

        if (res < Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        }

        return (int) res;
    }

    public boolean isPalindrome(int x) {
        if (x < 0 || x % 10 == 0 && x != 0) {
            return false;
        } else if (x < 10) {
            return true;
        }

        int rev = 0;
        while (x > rev) {
            rev = rev * 10 + x % 10;
            x /= 10;
        }
        return x == rev || x == rev / 10;
    }

    public int maxArea(int[] height) {
        // always move the short one
        int max_area = 0;
        int i = 0;
        int j = height.length - 1;
        while (i < j) {
            if (height[i] < height[j]) {
                max_area = Math.max(max_area, height[i] * (j - i));
                i++;
            } else {
                max_area = Math.max(max_area, height[j] * (j - i));
                j--;
            }
        }
        return max_area;
    }

    public String intToRoman(int num) {
        String M[] = {"", "M", "MM", "MMM"};
        String C[] = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String X[] = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String I[] = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
        return M[num / 1000] + C[(num / 100) % 10] + X[(num / 10) % 10] + I[num % 10];
    }

    public int romanToInt(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        Map<Character, Integer> map_char = new HashMap<>();
        map_char.put('I', 1);
        map_char.put('V', 5);
        map_char.put('X', 10);
        map_char.put('L', 50);
        map_char.put('C', 100);
        map_char.put('D', 500);
        map_char.put('M', 1000);

        int res = map_char.get(s.charAt(s.length() - 1));

        for (int i = s.length() - 2; i >= 0; i--) {
            if (map_char.get(s.charAt(i)) < map_char.get(s.charAt(i + 1))) {
                res -= map_char.get(s.charAt(i));
            } else {
                res += map_char.get(s.charAt(i));
            }
        }

        return res;
    }

    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        if (strs.length == 1) {
            return strs[0];
        }
        StringBuilder res = new StringBuilder();
        int idx_char = 0;
        while (true) {
            for (int i = 0; i < strs.length - 1; i++) {
                if (strs[i].length() <= idx_char || strs[i + 1].length() <= idx_char || strs[i].charAt(idx_char) != strs[i + 1].charAt(idx_char)) {
                    return res.toString();
                }
            }
            res.append(strs[0].charAt(idx_char));
            idx_char++;
        }
    }

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length < 3) {
            return res;
        }
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {              // skip same result
                continue;
            }
            int m = i + 1;
            int n = nums.length - 1;
            while (m < n) {
                int sum_two = nums[m] + nums[n];
                int target = 0 - nums[i];
                if (sum_two < target) {
                    m++;
                } else if (sum_two > target) {
                    n--;
                } else {
                    List<Integer> sum_three = new ArrayList<>();
                    sum_three.add(nums[i]);
                    sum_three.add(nums[m]);
                    sum_three.add(nums[n]);
                    res.add(sum_three);
                    m++;
                    n--;
                    while (m < n && nums[m] == nums[m - 1]) m++;  // skip same result
                    while (m < n && nums[n] == nums[n + 1]) n--;  // skip same result
                }
            }
        }
        return res;
    }

    public int threeSumClosest(int[] nums, int target) {
        if (nums == null || nums.length < 3) {
            throw new NullPointerException("nums array at least 3");
        }
        int sum_closest = nums[0] + nums[1] + nums[2];
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            int lo = i + 1;
            int hi = nums.length - 1;
            while (lo < hi) {
                int sum_curr = nums[i] + nums[lo] + nums[hi];
                if (sum_curr > target) {
                    sum_closest = (Math.abs(sum_closest - target) > Math.abs(sum_curr - target)) ? sum_curr : sum_closest;
                    hi--;
                } else if (sum_curr < target) {
                    sum_closest = (Math.abs(sum_closest - target) > Math.abs(sum_curr - target)) ? sum_curr : sum_closest;
                    lo++;
                } else {
                    return sum_curr;
                }
            }
        }
        return sum_closest;
    }

    public List<String> letterCombinations(String digits) {
        String[][] reference = {{"a", "b", "c"}, {"d", "e", "f"}, {"g", "h", "i"}, {"j", "k", "l"}, {"m", "n", "o"}, {"p", "q", "r", "s"}, {"t", "u", "v"}, {"w", "x", "y", "z"}};
        List<String> res = new ArrayList<>();
        if (digits == null) {
            return res;
        }
        List<String> strs = new ArrayList<>();
        strs.add("");
        for (int i = 0; i < digits.length(); i++) {
            int num = digits.charAt(i) - '0';
            int idx = num - 2;
            res.clear();
            for (String s : strs) {
                for (String t : reference[idx]) {
                    res.add(s + t);
                }
            }
            strs = new ArrayList<>(res);
        }
        return res;
    }

    public int divide(int dividend, int divisor) {
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }
        boolean isNegative = (dividend > 0 && divisor < 0) || (dividend < 0 && divisor > 0);
        long dividendL = Math.abs((long) dividend);
        long divisorL = Math.abs((long) divisor);
        int result = 0;
        while (dividendL >= divisorL) {
            int shift = 0;
            while (dividendL >= (divisorL << shift)) {
                shift++;
            }
            dividendL -= divisorL << (shift - 1);
            result += 1 << (shift - 1);
        }
        return isNegative ? -result : result;
    }

    public int[] searchRange(int[] nums, int target) {
        int firstIdx = -1;
        int lastIdx = -1;
        int[] nonRes = {firstIdx, lastIdx};
        if (nums == null || nums.length == 0 || target < nums[0] || target > nums[nums.length - 1]) {
            return nonRes;
        }
        int startIdx = 0;
        int endIdx = nums.length - 1;
        int midIdx;
        while (startIdx + 1 < endIdx) {
            midIdx = startIdx + (endIdx - startIdx) / 2;
            if (nums[midIdx] > target) {
                endIdx = midIdx;
            } else if (nums[midIdx] < target) {
                startIdx = midIdx;
            } else {
                endIdx = midIdx;
            }
        }
        if (target == nums[startIdx]) {
            firstIdx = startIdx;
        } else if (target == nums[endIdx]) {
            firstIdx = endIdx;
        } else {
            return nonRes;
        }
        endIdx = nums.length - 1;
        while (startIdx + 1 < endIdx) {
            midIdx = startIdx + (endIdx - startIdx) / 2;
            if (nums[midIdx] > target) {
                endIdx = midIdx;
            } else if (nums[midIdx] < target) {
                startIdx = midIdx;
            } else {
                startIdx = midIdx;
            }
        }
        if (target == nums[endIdx]) {
            lastIdx = endIdx;
        } else if (target == nums[startIdx]) {
            lastIdx = startIdx;
        } else {
            return nonRes;
        }
        return new int[]{firstIdx, lastIdx};
    }
}
