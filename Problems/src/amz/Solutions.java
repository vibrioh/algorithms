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
}
