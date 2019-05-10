package amz;

import java.util.Stack;
/*
"\\."
 */

public class CompareVersionNumber {
    public int compareVersion(String version1, String version2) {
        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();
        String[] s1 = version1.split("\\.");
        String[] s2 = version2.split("\\.");
        // doesn't have to use stack! just compare from first to last.
        // for (int i = s1.length - 1; i >= 0; i--) {
        //     stack1.push(Integer.valueOf(s1[i]));
        // }
        // for (int i = s2.length - 1; i >= 0; i--) {
        //     stack2.push(Integer.valueOf(s2[i]));
        // }
        // while (!stack1.isEmpty() || !stack2.isEmpty()) {
        //     int v1 = stack1.isEmpty() ? 0 : stack1.pop();
        //     int v2 = stack2.isEmpty() ? 0 : stack2.pop();
        //     // System.out.println(v1 + " " + v2);
        //     if (v1 > v2) {
        //         return 1;
        //     }
        //     if (v2 > v1) {
        //         return -1;
        //     }
        // }
        int len = Math.max(s1.length, s2.length);
        for (int i = 0; i < len; i++) {
            int v1 = i < s1.length ? Integer.valueOf(s1[i]) : 0;
            int v2 = i < s2.length ? Integer.valueOf(s2[i]) : 0;
            if (v1 > v2) {
                return 1;
            }
            if (v1 < v2) {
                return -1;
            }
        }
        return 0;
    }
}
