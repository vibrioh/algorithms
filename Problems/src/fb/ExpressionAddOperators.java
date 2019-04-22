package fb;

import java.util.ArrayList;
import java.util.List;

public class ExpressionAddOperators {
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
}
