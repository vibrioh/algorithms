package fb;

import java.util.ArrayList;
import java.util.List;

public class FindAnagrams {

    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        if (s == null || s.length() < p.length()) {
            return res;
        }
        int plen = p.length();
        int[] counts = new int[256];
        for (char c : p.toCharArray()) {
            counts[c]++;
        }
        int valid = 0;
        char[] charS = s.toCharArray();
        int j = 0;
        for (int i = 0; i < charS.length; i++) {
            // for j to increase to get valid, j is not inclusive, the currect sub should be [i, j);
            while (j < charS.length && valid < plen) {
                char c = charS[j];
                if (counts[c] > 0) {
                    valid++;
                }
                counts[c]--;
                j++;
            }
            // this is the ckeckpoint
            if (valid == plen && j - i == plen) {
                // System.out.println("j i :" + j + " " + i);
                res.add(i);
            }

            // slove the char to be delete if it is the one needed, then valid should be decreased
            // not needed on vill be less than 0
            counts[charS[i]]++;
            if (counts[charS[i]] > 0) {
                valid--;
            }
        }
        return res;
    }
}
