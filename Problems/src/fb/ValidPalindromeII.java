package fb;

public class ValidPalindromeII {
    public boolean validPalindrome(String s) {
        if (s == null) {
            return true;
        }
        int l = s.length();
        int left = 0;
        int right = l - 1;
        boolean canDeleteLeft = true;
        boolean canDeleteRight = true;
        while (left < right) {
            if (s.charAt(left) == s.charAt(right)) {
                left++;
                right--;
            } else {
                if (canDeleteLeft) {
                    right--;
                    canDeleteLeft = false;
                } else if (canDeleteRight) {
                    right++;
                    left++;
                    canDeleteRight = false;
                } else {
                    return false;
                }
            }
        }
        return true;
    }
}
