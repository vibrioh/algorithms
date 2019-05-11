package amz;

import java.util.*;

public class WordBreak {
    public boolean wordBreak(String s, List<String> wordDict) {
//        return wordBreakDFS(s, new HashSet<>(wordDict), 0);
        return wordBreakDFSwithMemo(s, new HashSet<>(wordDict), 0, new Boolean[s.length()]);
    }

    /*
    Time complexity : O(n^n). Consider the worst case where ss = "\text{aaaaaaa}aaaaaaa" and every prefix of ss is present in the dictionary of words, then the recursion tree can grow upto n^n

    Space complexity : O(n). The depth of the recursion tree can go upto n.
     */
    private boolean wordBreakDFS(String s, Set<String> wordSet, int start) {
        if (start == s.length()) {
            return true;
        }
        for (int end = start + 1; end <= s.length(); end++) {
            if (wordSet.contains(s.substring(start, end)) && wordBreakDFS(s, wordSet, end)) {
                return true;
            }
        }
        return false;
    }

    /*
    O(n^2) O(n)
     */
    private boolean wordBreakDFSwithMemo(String s, Set<String> wordSet, int start, Boolean[] memo) {
        if (start == s.length()) {
            return true;
        }
        if (memo[start] != null) {
            return memo[start];
        }
        for (int end = start + 1; end <= s.length(); end++) {
            if (wordSet.contains(s.substring(start, end)) && wordBreakDFSwithMemo(s, wordSet, end, memo)) {
                memo[start] = true;
                return true;
            }
        }
        memo[start] = false;
        return false;
    }

    /*
    BFS O(n^2) O(n)
     */
    public boolean wordBreakBFS(String s, List<String> wordDict) {
        Set<String> wordSet = new HashSet<>(wordDict);
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[s.length()];
        q.offer(0);
        while (!q.isEmpty()) {
            int start = q.poll();
            for (int end = start + 1; end <= s.length(); end++) {
                if (wordSet.contains(s.substring(start, end))) {
                    if (end == s.length()) {
                        return true;
                    }
                    if (visited[end]) {
                        continue;
                    }
                    q.offer(end);
                    visited[end] = true;
                }
            }
        }
        return false;
    }

    /*
    dp O(n^2) O(n)
     */
    public boolean wordBreakDP(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length() + 1];
        Set<String> wordSet = new HashSet<>(wordDict);
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }
}

