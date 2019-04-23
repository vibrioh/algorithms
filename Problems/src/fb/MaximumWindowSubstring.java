package fb;

public class MaximumWindowSubstring {
    //用Count数组计数，j在i前面的Sliding Window思想.
    public String minWindow(String s, String t) {
        if (s == null || t == null) {
            return null;
        }

        char[] charS = s.toCharArray();
        int[] count = new int[256];

        for (char c : t.toCharArray()) {
            count[c]++;
        }

        int j = 0;
        int countLen = t.length();
        String res = "";
        int curLen = 0;
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < charS.length; i++) {
            while (j < charS.length && curLen < countLen) {
                char c = charS[j];
                if (count[c] > 0) {
                    curLen++;
                }
                // those char not in t will be negative
                count[c]--;
                j++;
            }

            if (curLen == countLen) {
                if (j - i < min) {
                    res = s.substring(i, j);
                    min = j - i;
                }
            }

            // this char is going to be removed, become next needed one, so add 1
            count[charS[i]]++;

            // only the char in t can keep positive
            if (count[charS[i]] > 0) {
                curLen--;
            }
        }

        return res;
    }
}
