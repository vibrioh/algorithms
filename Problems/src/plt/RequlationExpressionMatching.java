package plt;

public class RequlationExpressionMatching {
    public boolean isMatch(String s, String p) {
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[0][0] = true;
        for (int i = 1; i <= p.length(); i++) {
            //need to do i-2 >= 0 check because in Lintcode, it has testcase which provides invalid p string, like "*a.*", no character before *
            if (p.charAt(i - 1) == '*' && i - 2 >= 0 && dp[0][i - 2]) {
                dp[0][i] = true;
            }
        }
        for (int i = 1; i <= s.length(); i++) {
            char sc = s.charAt(i - 1);
            for (int j = 1; j <= p.length(); j++) {
                char pc = p.charAt(j - 1);
                if (pc == '*' && j - 2 >= 0) {
                    if (p.charAt(j - 2) != '.' && p.charAt(j - 2) != s.charAt(i - 1)) {
                        //take x* as 0 match
                        dp[i][j] = dp[i][j - 2];
                    } else {
                        //3 different case, 0 match, 1 match, multiple match
                        dp[i][j] = dp[i - 1][j] || dp[i][j - 2] || dp[i - 1][j - 2]; //dp[i][j-1] is the same
                    }
                } else if (sc == pc || pc == '.') {
                    dp[i][j] = dp[i - 1][j - 1];
                }
            }

        }
        return dp[s.length()][p.length()];
    }
}
