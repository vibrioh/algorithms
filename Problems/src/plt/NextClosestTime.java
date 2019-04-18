package plt;

import java.util.TreeSet;

public class NextClosestTime {
    public String nextClosestTime(String time) {
        int n = time.length();
        char[] res = time.toCharArray();
        TreeSet<Integer> set = new TreeSet<>();
        for (char c : res) {
            if (c != ':') {
                set.add(c - '0');
            }
        }
        for (int i = n - 1; i >= 0; i--) {
            char c = res[i];
            int v = c - '0';
            if (c == ':') {
                continue;
            }
            // Returns the least element in this set greater than or equal to the given element, or null if there is no such element.
            Integer ceil = set.ceiling(v + 1);
            if (ceil == null || (i == 3 && ceil > 5) || (i == 0 && ceil > 2) || (res[0] == '2' && i == 1 && ceil > 4)) {
                // Returns the first (lowest) element currently in this set.
                res[i] = (char) (set.first() + '0');
            } else {
                res[i] = (char) (ceil + '0');
                return String.valueOf(res);
            }
        }
        return String.valueOf(res);
    }
}
