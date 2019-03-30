package ggl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class UniquePaths {

    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        dp[0][0] = 1;
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (r > 0) {
                    dp[r][c] += dp[r - 1][c];
                }
                if (c > 0) {
                    dp[r][c] += dp[r][c - 1];
                }
            }
        }
        return dp[m - 1][n - 1];
    }

    public int uniquePathsToNspace(int m, int n) {
        if (m > n) {
            int tmp = m;
            m = n;
            n = tmp;
        }
        int[] dp = new int[m];
        Arrays.fill(dp, 1);
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                dp[j] = dp[j] + dp[j - 1];
            }
        }
        return dp[m - 1];
    }

    public boolean canReach(int[][] points) {
        List<int[]> list = new ArrayList<>();
        list.add(new int[]{0, 0});
        for (int[] point : points) list.add(point);
        Collections.sort(list, (a, b) -> a[1] - b[1]);
        for (int i = 1; i < list.size(); i++) {
            int[] curr = list.get(i);
            int[] prev = list.get(i - 1);
//            if (curr[1] == prev[1]) return false;// 此处的判断不够严谨；还有一种情况是，两个点在同一列上，但这两个点其实是同一个点，则我们不应该直接zhi'jiefalse
//            int len = curr[1] - prev[1];
//            int upper = prev[0] - len;
//            int lower = prev[0] + len;
//            if (curr[0] <= lower && curr[0] >= upper) continue;
//            else return false;
            if (curr[0] > prev[0]) {
                return false;
            }
        }
        return true;
    }


    public int uniquePathsLeftUp2RightUp(int rows, int cols) {
        int[] dp = new int[rows];
        int[] tmp = new int[rows];
        dp[0] = 1;
        for (int j = 1; j < cols; j++) {
            for (int i = 0; i < rows; i++) {
                int val1 = i - 1 >= 0 ? dp[i - 1] : 0;
                int val2 = dp[i];
                int val3 = i + 1 < rows ? dp[i + 1] : 0;
                tmp[i] = val1 + val2 + val3;
            }
            System.arraycopy(tmp, 0, dp, 0, tmp.length);
        }
        return dp[0];
    }

    public boolean canReach2(int[][] points) {
        List<int[]> list = new ArrayList<>();
        list.add(new int[]{0, 0});
        for (int[] point : points) list.add(point);
        Collections.sort(list, (a, b) -> a[1] - b[1]);
        for (int i = 1; i < list.size(); i++) {
            int[] curr = list.get(i);
            int[] prev = list.get(i - 1);
            if (curr[1] == prev[1]) return false;// 此处的判断不够严谨；还有一种情况是，两个点在同一列上，但这两个点其实是同一个点，则我们不应该直接zhi'jiefalse
            int len = curr[1] - prev[1];
            int upper = prev[0] - len;
            int lower = prev[0] + len;
            if (curr[0] <= lower && curr[0] >= upper) continue;
            else return false;
        }
        return true;
    }

    public int uniquePathsH(int rows, int cols, int H) {
        return uniquePathsLeftUp2RightUp(rows, cols) - uniquePathsLeftUp2RightUp(H, cols);
    }

    public int uniquePathsLeftUp2LeftDown(int rows, int cols) {
        int[] dp = new int[cols];
        int[] tmp = new int[cols];
        dp[0] = 1;
        for (int i = 1; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int val1 = j - 1 >= 0 ? dp[j - 1] : 0;
                int val2 = dp[j];
                int val3 = j + 1 < cols ? dp[j + 1] : 0;
                tmp[i] = val1 + val2 + val3;
            }
            System.arraycopy(tmp, 0, dp, 0, tmp.length);
        }
        return dp[0];
    }


}
