package fb;

import java.util.*;

public class RemoveInvalidParentheses {
    public static void main(String[] args) {
        RemoveInvalidParentheses r = new RemoveInvalidParentheses();
        System.out.println("\n" + r.validOne(")()()f()((k(())hjg))dfs((0))))()(())"));
        System.out.println("\n" + r.validOne("))1))rr))3(((2((("));
    }

    public String validOne(String s) {
        if (s == null) {
            return s;
        }
        int len = s.length();
        Set<Integer> idxToDelete = new HashSet<>();
        // based one left '(' to get the ')' to be deleted
        int count = 0;
        for (int i = 0; i < len; i++) {
            if (s.charAt(i) == '(') {
                count++;
            }
            if (s.charAt(i) == ')') {
                if (count > 0) {
                    count--;
                } else {
                    idxToDelete.add(i);
                }
            }
        }
        // based one right ')' to get the '(' to be deleted
        count = 0;
        for (int i = len - 1; i >= 0; i--) {
            if (s.charAt(i) == ')') {
                count++;
            }
            if (s.charAt(i) == '(') {
                if (count > 0) {
                    count--;
                } else {
                    idxToDelete.add(i);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            if (idxToDelete.contains(i)) {
                System.out.print("-" + i + "-");
                continue;
            }
            sb.append(s.charAt(i));
        }
        return sb.toString();
    }


    public List<String> removeInvalidParentheses(String s) {

        List<String> results = new ArrayList<String>();
        int[] counts = getLeftRightCount(s);
        dfs(s, 0, counts[0], counts[1], results);

        return results;
    }

    private void dfs(String s, int startIndex, int leftCount, int rightCount, List<String> results) {
        if (leftCount == 0 && rightCount == 0 && isStringValid(s)) {
            results.add(s);
            return;
        }

        for (int i = startIndex; i < s.length(); i++) {
            if (i > startIndex && s.charAt(i) == s.charAt(i - 1)) {
                continue;
            }

            if (leftCount > 0 && s.charAt(i) == '(') {
                dfs(s.substring(0, i) + s.substring(i + 1), i, leftCount - 1, rightCount, results);
            }

            if (rightCount > 0 && s.charAt(i) == ')') {
                dfs(s.substring(0, i) + s.substring(i + 1), i, leftCount, rightCount - 1, results);
            }
        }
    }

    private boolean isStringValid(String s) {
        int[] result = getLeftRightCount(s);
        return result[0] == 0 && result[1] == 0;
    }

    private int[] getLeftRightCount(String s) {
        int[] counts = new int[]{0, 0};
        for (char c : s.toCharArray()) {
            if (c == '(') {
                counts[0]++;
            }
            if (c == ')') {
                if (counts[0] > 0) {
                    counts[0]--;
                } else {
                    counts[1]++;
                }
            }
        }
        return counts;
    }
}
