package fb;

public class PermutationInString {
    public boolean checkInclusion(String s1, String s2) {
        int l1 = s1.length();
        int l2 = s2.length();
        if (l1 > l2) {
            return false;
        }
        int[] counts = new int[26];
        for (char c : s1.toCharArray()) {
            counts[c - 'a']++;
        }
        int j = 0;
        int currlen = 0;
        char[] cs2 = s2.toCharArray();
        for (int i = 0; i < l2; i++) {
            while (j < l2 && currlen < l1) {
                char c = cs2[j];
                if (counts[c - 'a'] > 0) {
                    currlen++;
                }
                counts[c - 'a']--;
                j++;
            }
            if (currlen == l1) {
                if (j - i == l1) {
                    return true;
                }
            }
            counts[cs2[i] - 'a']++;
            if (counts[cs2[i] - 'a'] > 0) {
                currlen--;
            }
        }
        return false;
    }
}
