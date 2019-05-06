package ggl;

public class ExpressiveWords {
    public int expressiveWords(String S, String[] words) {
        int res = 0;
        for (String W : words) if (check(S, W)) res++;
        return res;
    }

    public boolean check(String S, String W) {
        int n = S.length(), m = W.length(), i = 0, j = 0;
        for (int i2 = 0, j2 = 0; i < n && j < m; i = i2, j = j2) {
            if (S.charAt(i) != W.charAt(j)) return false;
            while (i2 < n && S.charAt(i2) == S.charAt(i)) i2++;
            while (j2 < m && W.charAt(j2) == W.charAt(j)) j2++;
            if (i2 - i != j2 - j && i2 - i < Math.max(3, j2 - j)) return false;
        }
        return i == n && j == m;
    }
}
