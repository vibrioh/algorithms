package ggl;

import java.util.LinkedHashMap;
import java.util.Map;

/*
Time complexity : \mathcal{O}(N)O(N) since all operations with ordered dictionary : insert/get/delete/popitem (put/containsKey/remove) are done in a constant time.

Space complexity : \mathcal{O}(k)O(k) since additional space is used only for an ordered dictionary with at most k + 1 elements.
 */

public class LongestSubstringKDistinctCharacters {

    // use the template of string sliding window, need to think about how to make correct update condition
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
            while (j < charS.length && numUni <= k) {
                char cAdd = charS[j];
                if (counts[cAdd] == 0) {
                    numUni++;
                }
                if (numUni <= k) {
                    max = Math.max(max, j - i + 1);
                }
                counts[cAdd]++;
                j++;
            }
            char cDel = charS[i];
            counts[cDel]--;
            if (counts[cDel] == 0) {
                numUni--;
            }
        }
        return max;
    }

    public int lengthOfLongestSubstringKDistinctMap(String s, int k) {
        int n = s.length();
        if (n * k == 0) return 0;

        // sliding window left and right pointers
        int left = 0;
        int right = 0;
        // hashmap character -> its rightmost position
        // in the sliding window
        LinkedHashMap<Character, Integer> hashmap = new LinkedHashMap<Character, Integer>(k + 1);

        int max_len = 1;

        while (right < n) {
            Character character = s.charAt(right);
            // if character is already in the hashmap -
            // delete it, so that after insert it becomes
            // the rightmost element in the hashmap
            if (hashmap.containsKey(character))
                hashmap.remove(character);
            hashmap.put(character, right++);

            // slidewindow contains k + 1 characters
            if (hashmap.size() == k + 1) {
                // delete the leftmost character
                Map.Entry<Character, Integer> leftmost = hashmap.entrySet().iterator().next();
                hashmap.remove(leftmost.getKey());
                // move left pointer of the slidewindow
                left = leftmost.getValue() + 1;
            }

            max_len = Math.max(max_len, right - left);
        }
        return max_len;
    }

    public int lengthOfLongestSubstringKDistinct2(String s, int k) {
        int[] count = new int[256];
        int num = 0, i = 0, res = 0;
        for (int j = 0; j < s.length(); j++) {
            if (count[s.charAt(j)]++ == 0) num++;
            if (num > k) {
                while (--count[s.charAt(i++)] > 0) ;
                num--;
            }
            res = Math.max(res, j - i + 1);
        }
        return res;
    }

}
