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
            mp.putIfAbsent(d, new ArrayList<int[]>());
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

    public int flyPathRoute(int[] trees) {
        if (trees == null || trees.length == 0) {
            return 0;
        }
        int res = 0;
        for (int i = 0; i < trees.length; i++) {
            for (int j = i + 1; j < trees.length && trees[j] <= trees[i]; j++) {
                if (trees[j] == trees[i]) {
                    res += 2;
                }
            }
        }
        return res;
    }

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> mp = new HashMap<>();  // to save the node pre for other nodes
        int[] indegree = new int[numCourses];
        for (int[] edge : prerequisites) {
            int from = edge[1];
            int to = edge[0];
            List<Integer> children = mp.getOrDefault(from, new ArrayList<Integer>());
            children.add(to);
            mp.put(from, children);
            indegree[to] += 1;
        }
        Queue<Integer> q = new LinkedList<>();
        int finished = 0;
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                q.offer(i);
            }
        }
        while (!q.isEmpty()) {
            int curr = q.poll();
            // System.out.println("curr " + curr);
            finished++;
            // System.out.println(finished);
            if (!mp.containsKey(curr)) {
                // System.out.println("no");
                continue;
            }
            for (Integer i : mp.get(curr)) {
                if (--indegree[i] == 0) {
                    q.offer(i);
                }
            }
        }

        return finished == numCourses;
    }

    class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    public ListNode reverseLinkedList(ListNode head) {
        ListNode prev = null;
        while (head != null) {
            ListNode tmp = head.next;
            head.next = prev;
            prev = head;
            head = tmp;
        }
        return prev;
    }

    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> words = new HashSet<>(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int end = 1; end <= s.length(); end++) {
            for (int start = 0; start < end; start++) {
                if (dp[start] && words.contains(s.substring(start, end))) {
                    dp[end] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }

    public int lengthOfLongestSubstring(String s) {
        int len_max = 0;
        int index_start_curr = 0;
        Map<Character, Integer> charMap = new HashMap<>();
        char[] charList = s.toCharArray();
        for (int i = 0; i < charList.length; i++) {
            char char_curr = charList[i];
            if (charMap.containsKey(char_curr) && charMap.get(char_curr) >= index_start_curr) {
                index_start_curr = charMap.get(char_curr) + 1;
            }
            charMap.put(char_curr, i);
            len_max = Math.max(len_max, i - index_start_curr + 1);
        }
        return len_max;
    }

    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        if (maze == null || maze.length == 0) {
            return false;
        }
        int m = maze.length;
        int n = maze[0].length;
        boolean[][] visited = new boolean[m][n];
        Queue<int[]> q = new LinkedList<>();
        q.offer(start);
        visited[start[0]][start[1]] = true;
        int[] dr = new int[]{0, 0, 1, -1};
        int[] dc = new int[]{1, -1, 0, 0};
        while (!q.isEmpty()) {
            int[] curr = q.poll();
            if (curr[0] == destination[0] && curr[1] == destination[1]) {
                return true;
            }
            for (int i = 0; i < 4; i++) {
                int r = curr[0] + dr[i];
                int c = curr[1] + dc[i];

                // Don't check && !visited[r][c]  here, you will let r, c stop at middle before end to wall coner
                while (r >= 0 && r < m && c >= 0 && c < n && maze[r][c] == 0) {
                    r += dr[i];
                    c += dc[i];
                }
                r -= dr[i];
                c -= dc[i];
                if (!visited[r][c]) {
                    q.offer(new int[]{r, c});
                    visited[r][c] = true;
                }
            }
        }
        return false;
    }

    public String one(int num) {
        switch (num) {
            case 1:
                return "One";
            case 2:
                return "Two";
            case 3:
                return "Three";
            case 4:
                return "Four";
            case 5:
                return "Five";
            case 6:
                return "Six";
            case 7:
                return "Seven";
            case 8:
                return "Eight";
            case 9:
                return "Nine";
        }
        return "";
    }

    public String twoLessThan20(int num) {
        switch (num) {
            case 10:
                return "Ten";
            case 11:
                return "Eleven";
            case 12:
                return "Twelve";
            case 13:
                return "Thirteen";
            case 14:
                return "Fourteen";
            case 15:
                return "Fifteen";
            case 16:
                return "Sixteen";
            case 17:
                return "Seventeen";
            case 18:
                return "Eighteen";
            case 19:
                return "Nineteen";
        }
        return "";
    }

    public String ten(int num) {
        switch (num) {
            case 2:
                return "Twenty";
            case 3:
                return "Thirty";
            case 4:
                return "Forty";
            case 5:
                return "Fifty";
            case 6:
                return "Sixty";
            case 7:
                return "Seventy";
            case 8:
                return "Eighty";
            case 9:
                return "Ninety";
        }
        return "";
    }

    public String two(int num) {
        if (num == 0)
            return "";
        else if (num < 10)
            return one(num);
        else if (num < 20)
            return twoLessThan20(num);
        else {
            int tenner = num / 10;
            int rest = num - tenner * 10;
            if (rest != 0)
                return ten(tenner) + " " + one(rest);
            else
                return ten(tenner);
        }
    }

    public String three(int num) {
        int hundred = num / 100;
        int rest = num - hundred * 100;
        String res = "";
        if (hundred * rest != 0)
            res = one(hundred) + " Hundred " + two(rest);
        else if ((hundred == 0) && (rest != 0))
            res = two(rest);
        else if ((hundred != 0) && (rest == 0))
            res = one(hundred) + " Hundred";
        return res;
    }

    public String numberToWords(int num) {
        if (num == 0)
            return "Zero";

        int billion = num / 1000000000;
        int million = (num - billion * 1000000000) / 1000000;
        int thousand = (num - billion * 1000000000 - million * 1000000) / 1000;
        int rest = num - billion * 1000000000 - million * 1000000 - thousand * 1000;

        String result = "";
        if (billion != 0)
            result = three(billion) + " Billion";
        if (million != 0) {
            if (!result.isEmpty())
                result += " ";
            result += three(million) + " Million";
        }
        if (thousand != 0) {
            if (!result.isEmpty())
                result += " ";
            result += three(thousand) + " Thousand";
        }
        if (rest != 0) {
            if (!result.isEmpty())
                result += " ";
            result += three(rest);
        }
        return result;
    }


    /*
    Simple iterative solution by identifying characters one by one. One important thing is that the input is valid, which means the parentheses are always paired and in order.
Only 5 possible input we need to pay attention:

digit: it should be one digit from the current number
'+': number is over, we can add the previous number and start a new number
'-': same as above
'(': push the previous result and the sign into the stack, set result to 0, just calculate the new result within the parenthesis.
')': pop out the top two numbers from stack, first one is the sign before this pair of parenthesis, second is the temporary result before this pair of parenthesis. We add them together.
Finally if there is only one number, from the above solution, we haven't add the number to the result, so we do a check see if the number is zero.
     */

    public int calculate(String s) {
        Stack<Integer> stack = new Stack<Integer>();
        int result = 0;
        int number = 0;
        int sign = 1;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                number = 10 * number + (int) (c - '0');
            } else if (c == '+') {
                result += sign * number;
                number = 0;
                sign = 1;
            } else if (c == '-') {
                result += sign * number;
                number = 0;
                sign = -1;
            } else if (c == '(') {
                //we push the result first, then sign;
                stack.push(result);
                stack.push(sign);
                //reset the sign and result for the value in the parenthesis
                sign = 1;
                result = 0;
            } else if (c == ')') {
                result += sign * number;
                number = 0;
                result *= stack.pop();    //stack.pop() is the sign before the parenthesis
                result += stack.pop();   //stack.pop() now is the result calculated before the parenthesis

            }
        }
        if (number != 0) result += sign * number;
        return result;
    }


    public String simplifyPath(String path) {
        String[] list = path.split("/");
        Stack<String> sk = new Stack<>();
        // int n = 1;
        for (String s : list) {
            if (s == null) {
                continue;
            }
            // System.out.println(n++ + " {" + s + "}");
            if (s.equals("..")) {
                if (sk.isEmpty()) {
                    continue;
                }
                sk.pop();
            } else if (!s.equals(".") && !s.equals("")) {
                sk.push(s);
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!sk.isEmpty()) {
            String curr = sk.pop();
            sb.insert(0, curr);
            sb.insert(0, "/");

            // System.out.println(n++ + " " + curr);
        }

        return sb.length() == 0 ? "/" : sb.toString();
    }

    public int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return new int[]{-1, -1};
        }
        int start = 0;
        int end = nums.length - 1;
        int left = -1;
        int right = -1;
        while (start + 1 < end) {
            int mid = (end - start) / 2 + start;
            if (nums[mid] >= target) {
                end = mid;
            } else {
                start = mid;
            }
        }
        if (nums[start] == target) {
            left = start;
        } else if (nums[end] == target) {
            left = end;
        }
        start = 0;
        end = nums.length - 1;
        while (start + 1 < end) {
            int mid = (end - start) / 2 + start;
            if (nums[mid] <= target) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if (nums[end] == target) {
            right = end;
        } else if (nums[start] == target) {
            right = start;
        }
        return new int[]{left, right};
    }
}
