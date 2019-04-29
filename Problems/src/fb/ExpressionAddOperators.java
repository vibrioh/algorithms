package fb;

import java.util.ArrayList;
import java.util.List;

public class ExpressionAddOperators {


    // "+" only to do expression
    public List<String> addOnly(String strNum, int target) {
        List<String> res = new ArrayList<>();
        dfsAdd(strNum, target, 0, "", 0, res);
        return res;
    }

    private void dfsAdd(String strNum, int target, int start, String exp, long sum, List<String> res) {
        if (start == strNum.length()) {
            if (sum == target) {
                res.add(exp);
            }
            return;
        }
        for (int i = start; i < strNum.length(); i++) {
            long num = Long.parseLong(strNum.substring(start, i + 1));
            //check strt == 0 is very important, it make sure start clean, get rid of i = 0 .. 1 .. 2 .. caused nums
            if (start == 0) {
                dfsAdd(strNum, target, i + 1, "" + num, num, res);
            } else {
                dfsAdd(strNum, target, i + 1, exp + "+" + num, sum + num, res);
            }
            if (num == 0) {
                break;
            }
        }
    }


    // The whole: "+" "-" "*"
    private void dfs(String num, int target, int start, String str, long sum, long lastF, List<String> ans) {
        // used all string chars
        if (start == num.length()) {
            // generated the correct expression
            if (sum == target) {
                ans.add(str);
            }
            return;
        }
        for (int i = start; i < num.length(); i++) {
            // get the num of curr
            long x = Long.parseLong(num.substring(start, i + 1));
            // started from beginning, no operator but string itself
            // next one should be i + 1
            if (start == 0) {
                dfs(num, target, i + 1, "" + x, x, x, ans);
            } else {
                dfs(num, target, i + 1, str + "*" + x, sum - lastF + lastF * x, lastF * x, ans);
                dfs(num, target, i + 1, str + "+" + x, sum + x, x, ans);
                dfs(num, target, i + 1, str + "-" + x, sum - x, -x, ans);
            }
            // no legal num will start with 0 (023) rather than 0 itself, so stop increasing i for 0XXXX
            if (x == 0) {
                break;
            }
        }
    }

    public List<String> addOperators(String num, int target) {
        List<String> ans = new ArrayList<>();
        dfs(num, target, 0, "", 0, 0, ans);
        return ans;
    }

    public static void main(String[] args) {
        ExpressionAddOperators e = new ExpressionAddOperators();
        String[] strs = new String[]{"123", "232", "00", "01237"};
        int[] targets = new int[]{6, 8, 0, 31};
        for (int i = 0; i < 4; i++) {
            System.out.println(e.addOnly(strs[i], targets[i]));
            System.out.println(e.addOperators(strs[i], targets[i]));
            System.out.println();
        }
    }
}
