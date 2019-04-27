package fb;

public class LongestSubstringWithAtMostKDistinctCharacters {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (s == null) {
            return 0;
        }
        char[] charS = s.toCharArray();
        int[] counts = new int[256];
        int j = 0;
        int max = 0;
        int numUni = 0;
        for (int i = 0; i < charS.length; i++) {
            // j need to go to all == k
            while (j < charS.length && numUni <= k) {
                char cAdd = charS[j];
                if (counts[cAdd] == 0) {
                    numUni++;
                }
                // the key is the update should be <=, incase there is no ==
                if (numUni <= k) {
                    max = Math.max(max, j - i + 1);
                }
                counts[cAdd]++;
                j++;
            }
            // when remove the i char, just check if numUni needs to be updated
            char cDel = charS[i];
            counts[cDel]--;
            if (counts[cDel] == 0) {
                numUni--;
            }
        }
        return max;
    }
}
