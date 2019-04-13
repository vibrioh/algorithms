package amz;

import java.util.*;

public class Solutions {
    public int[][] kClosest(int[][] points, int K) {
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>((p1, p2) -> p2[0] * p2[0] + p2[1] * p2[1] - p1[0] * p1[0] - p1[1] * p1[1]);
        for (int[] p : points) {
            pq.offer(p);
            if (pq.size() > K) {
                pq.poll();
            }
        }
        int[][] res = new int[K][2];
        while (K > 0) {
            res[--K] = pq.poll();
        }
        return res;
    }

    public int[][] kClosestORI(int[][] points, int K) {
        Queue<Integer> q = new PriorityQueue<>();
        Map<Integer, List<int[]>> mp = new HashMap<>();
        int[][] res = new int[K][2];
        for (int[] point : points) {
            int x = point[0];
            int y = point[1];
            int d = (int) (Math.pow(x, 2) + Math.pow(y, 2));
            q.offer(d);
            mp.put(d, mp.getOrDefault(d, new ArrayList<int[]>()));
            // remember, list add 之后返回的事boolean！！！！！！！！！
            mp.get(d).add(point);
        }
        int idx = 0;
        while (!q.isEmpty()) {
            int d = q.poll();
            for (int[] point : mp.get(d)) {
                // System.out.println(point[0] + " " + point[1]);
                if (idx <= K - 1) {
                    res[idx] = point;
                    // System.out.println(res[idx][0] + "-" + res[idx][1]);
                    idx++;
                } else {
                    return res;
                }
            }
        }
        return res;
    }

    //verify from central
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = longestPalindromeAt(s, i, i);
            int len2 = longestPalindromeAt(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start + 1) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private int longestPalindromeAt(String s, int left, int right) {
        int len = 0;
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            len += left == right ? 1 : 2;
            left--;
            right++;
        }
        return len;
    }


    public String[] reorderLogFiles(String[] logs) {
        Arrays.sort(logs, (log1, log2) -> {
            String[] split1 = log1.split(" ", 2);
            String[] split2 = log2.split(" ", 2);
            boolean isDigit1 = Character.isDigit(split1[1].charAt(0));
            boolean isDigit2 = Character.isDigit(split2[1].charAt(0));
            if (!isDigit1 && !isDigit2) {
                int cmp = split1[1].compareTo(split2[1]);
                if (cmp != 0) return cmp;
                return split1[0].compareTo(split2[0]);
            }
            return isDigit1 ? (isDigit2 ? 0 : 1) : -1;
        });
        return logs;
    }


    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Map<String, List<String>> mp = new HashMap<>();
        Set<String> visited = new HashSet<>();
        for (String word : wordList) {
            for (int i = 0; i < word.length(); i++) {
                String star = word.substring(0, i) + "*" + word.substring(i + 1, word.length());
                List<String> words = mp.getOrDefault(star, new ArrayList<String>());
                words.add(word);
                // System.out.println(star);
                mp.put(star, words);
            }
        }
        Queue<String> q = new LinkedList<>();
        q.offer(beginWord);
        int level = 1;
        while (!q.isEmpty()) {
            int n = q.size();
            while (n > 0) {
                String curr = q.poll();
                n--;
                for (int i = 0; i < curr.length(); i++) {
                    String cstar = curr.substring(0, i) + "*" + curr.substring(i + 1, curr.length());

                    if (!visited.contains(cstar)) {
                        // System.out.println("2 " + cstar);
                        visited.add(cstar);
                        if (!mp.containsKey(cstar)) {
                            continue;
                        }
                        for (String candidate : mp.get(cstar)) {
                            // System.out.println("3 " + candidate);
                            if (candidate.equals(endWord)) {
                                return level + 1;
                            }
                            q.offer(candidate);
                        }
                    }
                }
            }
            level++;
        }
        return 0;
    }
}
