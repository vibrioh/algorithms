package amz;

import java.util.*;

public class ConcatenatedWords {
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        List<String> res = new ArrayList<>();
        Arrays.sort(words, (a, b) -> a.length() - b.length());
        Set<String> wordSet = new HashSet<>(Arrays.asList(words));
        for (int i = words.length - 1; i > 0; i--) {
            String curr = words[i];
            wordSet.remove(curr);
            if (wordBreakBFS(curr, wordSet)) {
                res.add(curr);
            }
        }
        return res;
    }

    public boolean wordBreakBFS(String s, Set<String> wordSet) {
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
}
