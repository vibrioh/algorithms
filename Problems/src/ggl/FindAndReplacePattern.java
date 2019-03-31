package ggl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindAndReplacePattern {

    public List<String> findAndReplacePattern(String[] words, String pattern) {
        List<String> ans = new ArrayList();
        for (String word : words)
            if (matchOneMap(word, pattern))
                ans.add(word);
        return ans;
    }

    public boolean matchTwoMap(String word, String pattern) {
        Map<Character, Character> m1 = new HashMap();
        Map<Character, Character> m2 = new HashMap();

        for (int i = 0; i < word.length(); ++i) {
            char w = word.charAt(i);
            char p = pattern.charAt(i);
            if (!m1.containsKey(w)) m1.put(w, p);
            if (!m2.containsKey(p)) m2.put(p, w);
            if (m1.get(w) != p || m2.get(p) != w)
                return false;
        }
        return true;
    }

    public boolean matchOneMap(String word, String pattern) {
        Map<Character, Character> M = new HashMap();
        for (int i = 0; i < word.length(); ++i) {
            char w = word.charAt(i);
            char p = pattern.charAt(i);
            if (!M.containsKey(w)) M.put(w, p);
            if (M.get(w) != p) return false;
        }
        // If two key have the same value, then mismatch
        boolean[] seen = new boolean[26];
        for (char p : M.values()) {
            if (seen[p - 'a']) return false;
            seen[p - 'a'] = true;
        }
        return true;
    }
}



