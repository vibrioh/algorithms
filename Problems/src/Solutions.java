import java.util.*;

public class Solutions {

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }


    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                return new int[]{map.get(nums[i]), i};
            }
            map.put(target - nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode head = dummyHead;
        int carry = 0;
        while (l1 != null || l2 != null || carry != 0) {
            int sum = 0;
            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }
            sum += carry;
            carry = sum / 10;
            head.next = new ListNode(sum % 10);
            head = head.next;
        }
        return dummyHead.next;
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

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }
        int len = nums1.length + nums2.length;
        int cut1 = 0;
        int cut2 = 0;
        int cutL = 0;
        int cutR = nums1.length;
        while (cut1 <= nums1.length) {
            cut1 = (cutR - cutL) / 2 + cutL;
            cut2 = len / 2 - cut1;
            double L1 = (cut1 == 0) ? Integer.MIN_VALUE : nums1[cut1 - 1];
            double L2 = (cut2 == 0) ? Integer.MIN_VALUE : nums2[cut2 - 1];
            double R1 = (cut1 == nums1.length) ? Integer.MAX_VALUE : nums1[cut1];
            double R2 = (cut2 == nums2.length) ? Integer.MAX_VALUE : nums2[cut2];
            if (L1 > R2) {
                cutR = cut1 - 1;
            } else if (L2 > R1) {
                cutL = cut1 + 1;
            } else {
                if (len % 2 == 0) {
                    L1 = (L1 > L2) ? L1 : L2;
                    R1 = (R1 < R2) ? R1 : R2;
                    return (L1 + R1) / 2;
                } else {
                    R1 = (R1 < R2) ? R1 : R2;
                    return R1;
                }
            }
        }
        return -1;
    }

    public String longestPalindrome(String s) {
        int len = s.length();
        if (len <= 1) {
            return s;
        }
        int idxStart = 0;
        int lenMax = 0;
        for (int i = 0; i < len; i++) {
            int len1 = checkPalindrome(s, i, i);
            int len2 = checkPalindrome(s, i, i + 1);
            int lenCurr = Math.max(len1, len2);
            idxStart = (lenCurr > lenMax) ? i : idxStart;
            lenMax = (lenCurr > lenMax) ? lenCurr : lenMax;
        }
        return s.substring(idxStart - (lenMax - 1) / 2, idxStart + lenMax / 2 + 1);
    }

    private int checkPalindrome(String s, int i, int j) {
        int lenMax = 0;
        while (i >= 0 && j < s.length()) {
            if (s.charAt(i) == s.charAt(j)) {
                lenMax = (j - i + 1 > lenMax) ? j - i + 1 : lenMax;
                i--;
                j++;
            } else {
                return lenMax;
            }
        }
        return lenMax;
    }

    public String convert(String s, int numRows) {
        if (numRows >= s.length() || numRows == 1) {
            return s;
        }
        StringBuilder res = new StringBuilder(s.length());
        for (int i = 0; i < numRows; i++) {
            res.append(s.charAt(i));
            int j = 1;
            if (i == 0 || i == numRows - 1) {
                while (i + 2 * (numRows - 1) * j < s.length()) {
                    res.append(s.charAt(i + 2 * (numRows - 1) * j));
                    j++;
                }
            } else {
                System.out.println(i + "-" + j);
                while (2 * (numRows - 1) * j - i < s.length()) {
                    res.append(s.charAt(2 * (numRows - 1) * j - i));
                    if (i + 2 * (numRows - 1) * j < s.length()) {
                        res.append(s.charAt(i + 2 * (numRows - 1) * j));
                    }
                    j++;
                }
            }
        }
        return res.toString();
    }

    public int reverse(int x) {
        long resl = 0;
        while (x != 0) {
            resl = resl * 10 + x % 10;
            x /= 10;
        }
        int res = (int) resl;
        return (res == resl) ? res : 0;
    }

    public int myAtoi(String str) {
        if (str == null) {
            return 0;
        }

        str = str.trim();

        if (str.length() == 0) {
            return 0;
        }

        int sign = 1;
        int idx = 0;
        long res = 0;

        if (str.charAt(idx) == '+') {
            idx++;
        } else if (str.charAt(idx) == '-') {
            sign = -1;
            idx++;
        }

        for (; idx < str.length(); idx++) {
            if (str.charAt(idx) < '0' || str.charAt(idx) > '9') {
                break;
            }
            res = res * 10 + (str.charAt(idx) - '0');
            if (res > Integer.MAX_VALUE) {
                break;
            }
        }

        res *= sign;

        if (res > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }

        if (res < Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        }

        return (int) res;
    }

    public boolean isPalindrome(int x) {
        if (x < 0 || x % 10 == 0 && x != 0) {
            return false;
        } else if (x < 10) {
            return true;
        }

        int rev = 0;
        while (x > rev) {
            rev = rev * 10 + x % 10;
            x /= 10;
        }
        return x == rev || x == rev / 10;
    }

    public int maxArea(int[] height) {
        // always move the short one
        int max_area = 0;
        int i = 0;
        int j = height.length - 1;
        while (i < j) {
            if (height[i] < height[j]) {
                max_area = Math.max(max_area, height[i] * (j - i));
                i++;
            } else {
                max_area = Math.max(max_area, height[j] * (j - i));
                j--;
            }
        }
        return max_area;
    }

    public String intToRoman(int num) {
        String M[] = {"", "M", "MM", "MMM"};
        String C[] = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String X[] = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String I[] = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
        return M[num / 1000] + C[(num / 100) % 10] + X[(num / 10) % 10] + I[num % 10];
    }

    public int romanToInt(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        Map<Character, Integer> map_char = new HashMap<>();
        map_char.put('I', 1);
        map_char.put('V', 5);
        map_char.put('X', 10);
        map_char.put('L', 50);
        map_char.put('C', 100);
        map_char.put('D', 500);
        map_char.put('M', 1000);

        int res = map_char.get(s.charAt(s.length() - 1));

        for (int i = s.length() - 2; i >= 0; i--) {
            if (map_char.get(s.charAt(i)) < map_char.get(s.charAt(i + 1))) {
                res -= map_char.get(s.charAt(i));
            } else {
                res += map_char.get(s.charAt(i));
            }
        }

        return res;
    }

    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        if (strs.length == 1) {
            return strs[0];
        }
        StringBuilder res = new StringBuilder();
        int idx_char = 0;
        while (true) {
            for (int i = 0; i < strs.length - 1; i++) {
                if (strs[i].length() <= idx_char || strs[i + 1].length() <= idx_char || strs[i].charAt(idx_char) != strs[i + 1].charAt(idx_char)) {
                    return res.toString();
                }
            }
            res.append(strs[0].charAt(idx_char));
            idx_char++;
        }
    }

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length < 3) {
            return res;
        }
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {              // skip same result
                continue;
            }
            int m = i + 1;
            int n = nums.length - 1;
            while (m < n) {
                int sum_two = nums[m] + nums[n];
                int target = 0 - nums[i];
                if (sum_two < target) {
                    m++;
                } else if (sum_two > target) {
                    n--;
                } else {
                    List<Integer> sum_three = new ArrayList<>();
                    sum_three.add(nums[i]);
                    sum_three.add(nums[m]);
                    sum_three.add(nums[n]);
                    res.add(sum_three);
                    m++;
                    n--;
                    while (m < n && nums[m] == nums[m - 1]) m++;  // skip same result
                    while (m < n && nums[n] == nums[n + 1]) n--;  // skip same result
                }
            }
        }
        return res;
    }

    public int threeSumClosest(int[] nums, int target) {
        if (nums == null || nums.length < 3) {
            throw new NullPointerException("nums array at least 3");
        }
        int sum_closest = nums[0] + nums[1] + nums[2];
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            int lo = i + 1;
            int hi = nums.length - 1;
            while (lo < hi) {
                int sum_curr = nums[i] + nums[lo] + nums[hi];
                if (sum_curr > target) {
                    sum_closest = (Math.abs(sum_closest - target) > Math.abs(sum_curr - target)) ? sum_curr : sum_closest;
                    hi--;
                } else if (sum_curr < target) {
                    sum_closest = (Math.abs(sum_closest - target) > Math.abs(sum_curr - target)) ? sum_curr : sum_closest;
                    lo++;
                } else {
                    return sum_curr;
                }
            }
        }
        return sum_closest;
    }

    public List<String> letterCombinations(String digits) {
        String[][] reference = {{"a", "b", "c"}, {"d", "e", "f"}, {"g", "h", "i"}, {"j", "k", "l"}, {"m", "n", "o"}, {"p", "q", "r", "s"}, {"t", "u", "v"}, {"w", "x", "y", "z"}};
        List<String> res = new ArrayList<>();
        if (digits == null) {
            return res;
        }
        List<String> strs = new ArrayList<>();
        strs.add("");
        for (int i = 0; i < digits.length(); i++) {
            int num = digits.charAt(i) - '0';
            int idx = num - 2;
            res.clear();
            for (String s : strs) {
                for (String t : reference[idx]) {
                    res.add(s + t);
                }
            }
            strs = new ArrayList<>(res);
        }
        return res;
    }

    public int divide(int dividend, int divisor) {
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }
        boolean isNegative = (dividend > 0 && divisor < 0) || (dividend < 0 && divisor > 0);
        long dividendL = Math.abs((long) dividend);
        long divisorL = Math.abs((long) divisor);
        int result = 0;
        while (dividendL >= divisorL) {
            int shift = 0;
            while (dividendL >= (divisorL << shift)) {
                shift++;
            }
            dividendL -= divisorL << (shift - 1);
            result += 1 << (shift - 1);
        }
        return isNegative ? -result : result;
    }

    public int[] searchRange(int[] nums, int target) {
        int firstIdx = -1;
        int lastIdx = -1;
        int[] nonRes = {firstIdx, lastIdx};
        if (nums == null || nums.length == 0 || target < nums[0] || target > nums[nums.length - 1]) {
            return nonRes;
        }
        int startIdx = 0;
        int endIdx = nums.length - 1;
        int midIdx;
        while (startIdx + 1 < endIdx) {
            midIdx = startIdx + (endIdx - startIdx) / 2;
            if (nums[midIdx] > target) {
                endIdx = midIdx;
            } else if (nums[midIdx] < target) {
                startIdx = midIdx;
            } else {
                endIdx = midIdx;
            }
        }
        if (target == nums[startIdx]) {
            firstIdx = startIdx;
        } else if (target == nums[endIdx]) {
            firstIdx = endIdx;
        } else {
            return nonRes;
        }
        endIdx = nums.length - 1;
        while (startIdx + 1 < endIdx) {
            midIdx = startIdx + (endIdx - startIdx) / 2;
            if (nums[midIdx] > target) {
                endIdx = midIdx;
            } else if (nums[midIdx] < target) {
                startIdx = midIdx;
            } else {
                startIdx = midIdx;
            }
        }
        if (target == nums[endIdx]) {
            lastIdx = endIdx;
        } else if (target == nums[startIdx]) {
            lastIdx = startIdx;
        } else {
            return nonRes;
        }
        return new int[]{firstIdx, lastIdx};
    }

    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<String> nodeList = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    nodeList.add(String.valueOf(node.left.val));
                    queue.add(node.left);
                } else {
                    nodeList.add("#");
                }
                if (node.right != null) {
                    nodeList.add(String.valueOf(node.right.val));
                    queue.add(node.right);
                } else {
                    nodeList.add("#");
                }
            }
            if (!isPalindromeStr(nodeList)) {
                return false;
            }
        }
        return true;
    }

    private boolean isPalindromeStr(List<String> nodeList) {
        int startIdx = 0;
        int endIdx = nodeList.size() - 1;
        while (startIdx + 1 < endIdx) {
            if (!nodeList.get(startIdx).equals(nodeList.get(endIdx))) {
                return false;
            }
            startIdx++;
            endIdx--;
        }
        if (!nodeList.get(startIdx).equals(nodeList.get(endIdx))) {
            return false;
        }
        return true;
    }

    public class Coordinate {
        int r;
        int c;

        Coordinate(int r, int c) {
            this.r = r;
            this.c = c;
        }

        public Coordinate move(int x, int y) {
            return new Coordinate(this.r + x, this.c + y);
        }

        public boolean inBound(int[][] matrix) {
            return this.r < matrix.length && this.c < matrix[0].length && this.r >= 0 && this.c >= 0;
        }

        // @Override
        // public boolean equals(Object obj) {
        // if (this == obj)
        // 	return true;
        // if (obj == null)
        // 	return false;
        // if (getClass() != obj.getClass())
        // 	return false;
        // Coordinate other = (Coordinate) obj;
        // if (this.r != other.r)
        // 	return false;
        // if (this.c != other.c)
        // 	return false;
        // return true;
        // }
    }

    public int[][] updateMatrix(int[][] matrix) {
        if (matrix == null) {
            return matrix;
        }

        for (int r = 0; r < matrix.length; r++) {
            for (int c = 0; c < matrix[r].length; c++) {
                if (matrix[r][c] == 1) {
                    matrix[r][c] = bfsHelper(new Coordinate(r, c), matrix);
                }
            }
        }
        return matrix;
    }

    private int bfsHelper(Coordinate coor, int[][] matrix) {
        int[] deltaX = {1, 0, 0, -1};
        int[] deltaY = {0, 1, -1, 0};
        int distance = 0;
        Queue<Coordinate> queue = new LinkedList<>();
        // Set<Coordinate> visited = new HashSet<>();
        queue.add(coor);
        while (!queue.isEmpty()) {
            int size = queue.size();
            distance++;
            for (int i = 0; i < size; i++) {
                Coordinate currCoor = queue.poll();
                for (int j = 0; j < 4; j++) {
                    Coordinate newCoor = currCoor.move(deltaX[j], deltaY[j]);
                    // System.out.println(newCoor.r + "," + newCoor.c + ": " + distance);
                    if (newCoor.inBound(matrix)) {
                        // System.out.println(newCoor.r + "," + newCoor.c + ": " + "inBound");
                        if (matrix[newCoor.r][newCoor.c] == 0) {
                            // System.out.println(newCoor.r + "," + newCoor.c + ": " + "==0");
                            return distance;
                        }
                        // if (!visited.contains(newCoor)) {
                        queue.add(newCoor);
                        // }
                    }
                }
            }
        }
        return distance;
    }

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // how to declare and new the array of List!!
        List<Integer>[] neighbors = new ArrayList[numCourses];
        int[] inDegrees = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            neighbors[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < prerequisites.length; i++) {
            neighbors[prerequisites[i][1]].add(prerequisites[i][0]);
            inDegrees[prerequisites[i][0]]++;
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegrees[i] == 0) {
                queue.add(i);
            }
        }

        int finished = 0;

        while (!queue.isEmpty()) {
            finished++;
            int courseFinished = queue.poll();
            // ArrayList is not array!!
            for (int i = 0; i < neighbors[courseFinished].size(); i++) {
                int course = neighbors[courseFinished].get(i);
                inDegrees[course]--;
                if (inDegrees[course] == 0) {
                    queue.add(course);
                }
            }
        }

        return finished == numCourses;
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        } else if (p == null || q == null) {
            return false;
        } else if (p.val != q.val) {
            return false;
        } else {
            // below is wrong, if both false will be true
            //// return isSameTree(p.left, p.right) && isSameTree(p.right, q.right);
            if (!isSameTree(p.left, q.left) || !isSameTree(p.right, q.right)) {
                return false;
            } else {
                return true;
            }
        }
    }

    public boolean hasPathSum(TreeNode root, int sum) {
        // key is to check leaf node
        if (root == null) {
            return false;
        } else if (root.left == null && root.right == null) {
            return sum == root.val;
        } else {
            boolean left = hasPathSum(root.left, sum - root.val);
            boolean right = hasPathSum(root.right, sum - root.val);
            return left || right;
        }
    }

    public List<String> binaryTreePaths(TreeNode root) {
        List<String> paths = new ArrayList<>();
        if (root == null) {
            return paths;
        }
        dfsPaths(root, paths, new StringBuilder());
        return paths;
    }

    private void dfsPaths(TreeNode root, List paths, StringBuilder path) {
        if (root.left == null && root.right == null) {
            path.append(String.valueOf(root.val));
            paths.add(path.toString());
            return;
        }
        path.append(String.valueOf(root.val) + "->");
        if (root.left != null) {
            dfsPaths(root.left, paths, new StringBuilder(path.toString()));
        }
        if (root.right != null) {
            dfsPaths(root.right, paths, new StringBuilder(path.toString()));
        }
    }

    public boolean isValid(String s) {
        Map<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put(']', '[');
        map.put('}', '{');
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                if (stack.isEmpty() || map.get(s.charAt(i)) != stack.pop()) {
                    return false;
                }
            } else {
                stack.push(s.charAt(i));
            }
        }
        return stack.isEmpty();
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        //always return the min node;
        if (l1 == null && l2 == null) {
            return null;
        } else if (l1 == null && l2 != null) {
            // System.out.println(l2.val);
            return l2;
        } else if (l1 != null && l2 == null) {
            // System.out.println(l1.val);
            return l1;
        } else {
            ListNode minNode = l1.val < l2.val ? l1 : l2;
            ListNode maxNode = l1.val < l2.val ? l2 : l1;
            // System.out.println(l1.val + "--" + l2.val);
            minNode.next = mergeTwoLists(maxNode, minNode.next);
            return minNode;
        }
    }

    public int numJewelsInStones(String J, String S) {
        int num = 0;
        for (char j : J.toCharArray()) {
            for (char s : S.toCharArray()) {
                if (j == s) {
                    num++;
                }
            }
        }
        return num;
    }

    public int minCost(int[][] costs) {
        if (costs == null || costs.length == 0) {
            return 0;
        }
        int m = costs.length;
        int n = costs[0].length;
        // the lowest cost at the current position
        int[][] dp = new int[m][n];
        for (int i = 0; i < n; i++) {
            dp[0][i] = costs[0][i];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int min = Integer.MAX_VALUE;
                for (int k = 0; k < n; k++) {
                    if (k == j) {
                        continue;
                    }
                    min = Math.min(min, dp[i - 1][k]);
                    // System.out.println("(" + i + j + k + ")" + min + "--" + dp[i - 1][k]);
                }
                dp[i][j] = min + costs[i][j];
                System.out.println("(" + i + j + ")" + min + "--" + dp[i][j]);
            }
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            min = Math.min(min, dp[m - 1][i]);
        }
        return min;
    }

    public int maxProfit(int[] prices) {
        int min = Integer.MAX_VALUE;
        int max = 0;
        for (int i = 0; i < prices.length; i++) {
            max = Math.max(max, prices[i] - min);
            min = Math.min(min, prices[i]);
        }
        return max;
    }

    public int minCostClimbingStairs(int[] cost) {
        if (cost == null) {
            return 0;
        }
        int n = cost.length;
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return cost[0];
        }
        int[] dp = new int[n];
        // Don't forget initialize the status
        dp[0] = cost[0];
        dp[1] = cost[1];
        for (int i = 2; i < n; i++) {
            dp[i] = Math.min(dp[i - 1], dp[i - 2]) + cost[i];
            // System.out.println(i + "->" + dp[i]);
        }
        // Need to decide what is the answer
        return Math.min(dp[n - 1], dp[n - 2]);
    }

    public int climbStairs(int n) {
        // current solutions at step n
        if (n == 1) {
            return 1;
        }
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i < n + 1; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int minSubarray = Integer.MAX_VALUE;
        int maxSubarray = Integer.MIN_VALUE;
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            minSubarray = Math.min(minSubarray, sum);
            sum += nums[i];
            maxSubarray = Math.max(maxSubarray, sum - minSubarray);
        }
        return maxSubarray;
    }

    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int[] dp = new int[nums.length + 1];
        dp[1] = nums[0];
        for (int i = 2; i < nums.length + 1; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i - 1], dp[i - 1]);
        }
        return dp[nums.length];
    }

    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = head.next;
        head.next = swapPairs(newHead.next);
        newHead.next = head;
        return newHead;
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        //dummy node is not dummy pointers, multiple pointers but only one dummy node!!
        //the step left to the end is the posion of nth from end
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode slow = dummy;
        ListNode fast = dummy;
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return dummy.next;
    }

    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = 1;
        int num = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (num != nums[i]) {
                n += 1;
                num = nums[i];
                nums[n - 1] = nums[i];
            }
        }
        return n;
    }

    public int removeElement(int[] nums, int val) {
        int start = 0;
        int end = nums.length - 1;
        while (start <= end) {
            // even when equal, need to check if this position is delete of not
            // array reduce one each time
            if (nums[start] == val) {
                nums[start] = nums[end];
                end--;
            } else {
                start++;
            }
        }
        return start;
    }

    public int strStr(String haystack, String needle) {
        if (haystack == null || needle == null) {
            return -1;
        }
        if (needle.equals("")) {
            return 0;
        }
        char[] hs = haystack.toCharArray();
        char[] nd = needle.toCharArray();
        for (int i = 0; i < hs.length; i++) {
            if (hs[i] == nd[0] && nd.length <= hs.length - i) {
                int j = 1;
                while (j < nd.length) {
                    if (hs[i + j] != nd[j]) {
                        break;
                    }
                    j++;
                }
                if (j == nd.length) {
                    return i;
                }
            }
        }
        return -1;
    }

    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length < 2) {
            return;
        }
        int i = nums.length - 1;
        for (i = nums.length - 1; i > 0; i--) {
            if (nums[i] > nums[i - 1]) {
                int j = nums.length - 1;
                while (nums[j] <= nums[i - 1]) {
                    j--;
                }
                int tmp = nums[i - 1];
                nums[i - 1] = nums[j];
                nums[j] = tmp;
                break;
            }

        }
        System.out.println(Arrays.toString(nums));
        for (int j = nums.length - 1; i < j; i++, j--) {
            int tmp = nums[j];
            nums[j] = nums[i];
            nums[i] = tmp;
        }
        System.out.println(Arrays.toString(nums));
    }

    public int searchInsert(int[] nums, int target) {
        if (target < nums[0]) {
            return 0;
        }
        if (target > nums[nums.length - 1]) {
            return nums.length;
        }
        int start = 0;
        int end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (target == nums[mid]) {
                return mid;
            } else if (target < nums[mid]) {
                end = mid;
            } else {
                start = mid;
            }
        }
        if (target == nums[start]) {
            return start;
        } else if (target == nums[end]) {
            return end;
        } else {
            return start + 1;
        }
    }

    public boolean isValidSudoku(char[][] board) {
        Set<String> seen = new HashSet<>();
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                char digit = board[r][c];
                if (digit != '.') {
                    if (!seen.add("row" + r + digit) || !seen.add("col" + c + digit) || !seen.add("block" + (r / 3) + (c / 3) + digit)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public String countAndSay(int n) {
        if (n == 1) {
            return "1";
        }
        String res = "1";
        while (n > 1) {
            res = dfsRead(res);
            n--;
        }
        return res;
    }

    private String dfsRead(String str) {
        StringBuilder res = new StringBuilder();
        int n = 1;
        for (int i = 0; i < str.length(); i++) {
            if (i == str.length() - 1 || str.charAt(i + 1) != str.charAt(i)) {
                res.append(n).append(str.charAt(i));
                n = 1;
            } else {
                n++;
            }
        }
        System.out.println(res.toString());
        return res.toString();
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> results = new ArrayList<>();
        dfsComSum(candidates, target, new ArrayList<Integer>(), 0, results);
        // for (List<Integer> l : results) {
        //     Collections.sort(l);
        // }
        // ddpList(results);
        return results;
    }

    private void dfsComSum(int[] candidates, int target, List<Integer> result, int idx, List<List<Integer>> results) {
        if (target < 0) {
            return;
        }
        for (int i = idx; i < candidates.length; i++) {
            result.add(candidates[i]);
            if (target == candidates[i]) {
                results.add(new ArrayList<>(result));
            } else {
                dfsComSum(candidates, target - candidates[i], result, i, results);
            }
            result.remove(result.size() - 1);
        }
    }

    // private void ddpList(List<List<Integer>> list) {
    //     HashSet<List<Integer>> set = new HashSet<>(list);
    //     list.clear();
    //     list.addAll(set);
    // }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> results = new ArrayList<>();
        Arrays.sort(candidates);
        dfsComSum2(candidates, target, results, new ArrayList<Integer>(), 0);
        return results;
    }

    private void dfsComSum2(int[] candidates, int target, List<List<Integer>> results, List<Integer> result, int idx) {
        if (target < 0) {
            return;
        }
        for (int i = idx; i < candidates.length; i++) {
            // choose the first time of appearance, 和第一次加入后相等的都略过
            if (i > idx && candidates[i - 1] == candidates[i]) {
                continue;
            }
            result.add(candidates[i]);
            // System.out.println(result);
            if (target == candidates[i]) {
                results.add(new ArrayList<>(result));
                // you can not return here because you need to remove the last one even if you got one result!!
            } else {
                dfsComSum2(candidates, target - candidates[i], results, result, i + 1);
            }
            result.remove(result.size() - 1);
        }
        return;
    }

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();
        dfsPermute(nums, results, new LinkedHashSet<Integer>());
        return results;
    }

    private void dfsPermute(int[] nums, List<List<Integer>> results, Set<Integer> result) {
        if (nums.length == result.size()) {
            results.add(new ArrayList<>(result));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!result.contains(nums[i])) {
                result.add(nums[i]);
                dfsPermute(nums, results, result);
                result.remove(nums[i]);
            }
        }
    }

    public String multiply(String num1, String num2) {
        // `num1[i] * num2[j]` will be placed at indices `[i + j`, `i + j + 1]`
        char[] nchar1 = num1.toCharArray();
        char[] nchar2 = num2.toCharArray();
        int n1 = nchar1.length;
        int n2 = nchar2.length;
        int[] product = new int[n1 + n2];
        for (int i = n1 - 1; i >= 0; i--) {
            for (int j = n2 - 1; j >= 0; j--) {
                int digit1 = nchar1[i] - '0';
                int digit2 = nchar2[j] - '0';
                int pro = digit1 * digit2;
                // first, add right pos to have current sum
                // second, get the left pos and add for current pos
                // third, get the current right pos
                int sum = pro + product[i + j + 1];
                product[i + j] += sum / 10;
                product[i + j + 1] = sum % 10;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int n : product) {
            if (sb.length() == 0 && n == 0) {
                continue;
            }
            sb.append(n);
        }
        return sb.length() == 0 ? "0" : sb.toString();
    }

    public List<List<Integer>> permuteUnique(int[] nums) {
        if (nums == null) {
            return null;
        }
        List<List<Integer>> results = new ArrayList<>();
        Arrays.sort(nums);
        boolean[] visited = new boolean[nums.length];
        dfsPermuteUnique(results, new ArrayList<Integer>(), nums, visited);
        return results;
    }

    private void dfsPermuteUnique(List<List<Integer>> results,
                                  List<Integer> result,
                                  int[] nums,
                                  boolean[] visited) {
        if (result.size() == nums.length) {
            results.add(new ArrayList<>(result));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!visited[i]) {
                if (i > 0 && nums[i] == nums[i - 1] && !visited[i - 1]) {
                    continue;
                }
                result.add(nums[i]);
                visited[i] = true;
                dfsPermuteUnique(results, result, nums, visited);
                result.remove(result.size() - 1);
                visited[i] = false;
            }
        }
        return;
    }

    public void rotate(int[][] matrix) {
        if (matrix == null) {
            return;
        }
        int len = matrix.length;
        for (int r = 0; r < len; r++) {
            for (int c = 0; c < len / 2; c++) {
                int tmp = matrix[r][c];
                matrix[r][c] = matrix[r][len - 1 - c];
                matrix[r][len - 1 - c] = tmp;
            }
        }
        for (int r = 0; r < len - 1; r++) {
            for (int c = 0; c < len - 1 - r; c++) {
                int tmp = matrix[r][c];
                matrix[r][c] = matrix[len - 1 - c][len - 1 - r];
                matrix[len - 1 - c][len - 1 - r] = tmp;
            }
        }
        return;
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            int[] abc = new int[26];
            for (Character c : str.toCharArray()) {
                abc[c - 'a']++;
            }
            StringBuilder sb = new StringBuilder();
            for (int i : abc) {
                sb.append("#" + i);
            }
            String s = sb.toString();
            map.putIfAbsent(s, new ArrayList<>());
            map.get(s).add(str);
        }
        return new ArrayList<>(map.values());
    }

    public double myPow(double x, int n) {
        long nl = n;
        if (n < 0) {
            nl = -nl;
            x = 1 / x;
        }
        double res = 1.0;
        double currProd = x;
        for (long i = nl; i > 0; i /= 2) {
            if (i % 2 == 1) {
                res *= currProd;
            }
            currProd *= currProd;
        }
        return res;
    }

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        if (matrix == null || matrix.length == 0) {
            return res;
        }
        int startR = 0;
        int startC = 0;
        int endR = matrix.length - 1;
        int endC = matrix[0].length - 1;
        while (startR <= endR && startC <= endC) {
            for (int i = startC; i < endC; i++) {
                res.add(matrix[startR][i]);
            }
            for (int i = startR; i <= endR; i++) {
                res.add(matrix[i][endC]);
            }
            // note: when loop of while into a special end, need to check the boundary case!!!!!!!!!!!
            if (startR < endR && startC < endC) {
                for (int i = endC - 1; i > startC; i--) {
                    res.add(matrix[endR][i]);
                }
                for (int i = endR; i > startR; i--) {
                    res.add(matrix[i][startC]);
                }
            }
            startR++;
            startC++;
            endR--;
            endC--;
        }
        return res;
    }

    public boolean canJump(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        boolean[] can = new boolean[nums.length];
        int farest = nums[0];
        for (int i = 0; i < nums.length; i++) {
            if (i <= farest) {
                farest = Math.max(farest, i + nums[i]);
            }
        }
        return farest >= nums.length - 1;
    }

    public class Interval {
        int start;
        int end;

        Interval() {
            start = 0;
            end = 0;
        }

        Interval(int s, int e) {
            start = s;
            end = e;
        }
    }

    public List<Interval> merge(List<Interval> intervals) {
        List<Interval> res = new ArrayList<>();
        if (intervals == null || intervals.size() == 0) {
            return res;
        }
        intervals.sort(Comparator.comparing(i -> i.start));
        Interval last = null;
        for (Interval curr : intervals) {
            if (last == null || last.end < curr.start) {
                res.add(curr);
                last = curr;
            } else {
                last.end = Math.max(curr.end, last.end);
            }
        }
        return res;
    }

    public int lengthOfLastWord(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        String st = s.trim();
        int len = 0;
        for (int i = st.length() - 1; i >= 0; i--) {
            if (st.charAt(i) == ' ') {
                break;
            }
            len++;
        }
        return len;
    }

    public int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];
        int value = 1;
        for (int start = 0, end = n - 1; start <= end; start++, end--) {
            for (int c = start; c <= end; c++) {
                res[start][c] = value;
                value++;
            }
            for (int r = start + 1; r < end; r++) {
                res[r][end] = value;
                value++;
            }
            for (int c = end; c > start; c--) {
                res[end][c] = value;
                value++;
            }
            for (int r = end; r > start; r--) {
                res[r][start] = value;
                value++;
            }
        }
        return res;
    }

    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || k == 0) {
            return head;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        int len = 1;
        while (head.next != null) {
            head = head.next;
            len++;
        }
        head.next = dummy.next;
        int forward = len - (k % len);
        while (forward > 0) {
            head = head.next;
            forward--;
        }
        dummy.next = head.next;
        head.next = null;
        return dummy.next;
    }

    public String getPermutation(int n, int k) {
        List<Integer> nums = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        int combN = n;
        int comb = 1;
        for (int i = 1; i <= n; i++) {
            nums.add(i);
            comb *= i;
        }
        k--;
        while (combN > 0) {
            comb /= combN;
            int idx = k / comb;
            // System.out.println("comb" + comb);
            // System.out.println("k" + k);
            // System.out.println("idx" + idx);
            sb.append(nums.get(idx));
            nums.remove(idx);
            k %= comb;
            combN--;
        }
        return sb.toString();
    }

    public int firstUniqChar(String s) {
        char[] carr = s.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(carr[i])) {
                map.put(carr[i], -1);
            } else {
                map.put(carr[i], i);
            }
        }
        for (Character c : carr) {
            if (map.get(c) != -1) {
                return map.get(c);
            }
        }
        return -1;
    }

    public int minFallingPathSum(int[][] A) {
        int n = A.length;
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            dp[0][i] = A[0][i];
        }
        for (int r = 1; r < n; r++) {
            for (int c = 0; c < n; c++) {
                int lastMin = dp[r - 1][c];
                // System.out.println("r c " + r + " " + c);
                // System.out.println("lastMin " + lastMin);
                if (c - 1 >= 0 && dp[r - 1][c - 1] < lastMin) {
                    lastMin = dp[r - 1][c - 1];
                }
                if (c + 1 < n && dp[r - 1][c + 1] < lastMin) {
                    lastMin = dp[r - 1][c + 1];
                }
                dp[r][c] = lastMin + A[r][c];
            }
        }
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            res = Math.min(res, dp[n - 1][i]);
        }
        return res;
    }

    public int maxProfitII(int[] prices) {
        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                profit += prices[i] - prices[i - 1];
            }
        }
        return profit;
    }

    public int maximumProduct(int[] nums) {
        int min1 = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;
        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;
        int max3 = Integer.MIN_VALUE;
        for (int n : nums) {
            if (n < min1) {
                min2 = min1;
                min1 = n;
            } else if (n < min2) {
                min2 = n;
            }
            if (n > max1) {
                max3 = max2;
                max2 = max1;
                max1 = n;
            } else if (n > max2) {
                max3 = max2;
                max2 = n;
            } else if (n > max3) {
                max3 = n;
            }
        }
        return Math.max(max1 * max2 * max3, min1 * min2 * max1);
    }

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> results = new ArrayList<>();
        if (numRows == 0) {
            return results;
        }
        List<Integer> lastResult = new ArrayList<>();
        for (int i = 1; i <= numRows; i++) {
            List<Integer> result = new ArrayList<>();
            for (int j = 0; j < i; j++) {
                if (j == 0 || j == i - 1) {
                    result.add(1);
                } else {
                    result.add(lastResult.get(j - 1) + lastResult.get(j));
                }

            }
            results.add(new ArrayList<>(result));
            lastResult = new ArrayList<>(result);
        }
        return results;
    }

    public boolean isAnagram(String s, String t) {
        if (s == null || t == null || s.length() != t.length()) {
            return false;
        }
        int[] abc = new int[26];
        for (int i = 0; i < s.length(); i++) {
            int cs = s.charAt(i) - 'a';
            abc[cs]++;
            int ct = t.charAt(i) - 'a';
            abc[ct]--;
        }
        for (int n : abc) {
            if (n != 0) {
                return false;
            }
        }
        return true;
    }


    public int arrangeCoins(int n) {
        return (int) (Math.sqrt(8L * n + 1) - 1) / 2;
    }

    public int compress(char[] chars) {
        int slow = 0;
        int fast = 0;
        while (fast < chars.length) {
            char curr = chars[fast];
            int count = 0;
            while (fast < chars.length && chars[fast] == curr) {
                fast++;
                count++;
            }
            chars[slow++] = curr;
            if (count > 1) {
                for (char c : String.valueOf(count).toCharArray()) {
                    chars[slow++] = c;
                }
            }
        }
        return slow;
    }

    public boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }

    class MinStack {
        private Stack<Integer> stack;
        private Stack<Integer> minStack;

        /**
         * initialize your data structure here.
         */
        public MinStack() {
            stack = new Stack<>();
            minStack = new Stack<>();
        }

        public void push(int x) {
            if (minStack.isEmpty() || x <= minStack.peek()) {
                minStack.push(x);
            }
            stack.push(x);
            return;
        }

        public void pop() {
            if (stack.pop().equals(minStack.peek())) {
                minStack.pop();
            }
            return;
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            return minStack.peek();
        }
    }

    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        } else if (root.left == null) {
            return minDepth(root.right) + 1;
        } else if (root.right == null) {
            return minDepth(root.left) + 1;
        } else {
            int left = minDepth(root.left);
            int right = minDepth(root.right);
            return Math.min(left, right) + 1;
        }
    }

    public int thirdMax(int[] nums) {
        long first = Long.MIN_VALUE;
        long second = Long.MIN_VALUE;
        long third = Long.MIN_VALUE;
        // int n = 0;
        for (int i : nums) {
            if (i > first) {
                third = second;
                second = first;
                first = i;
                // n++;
            } else if (i > second && i < first) {
                third = second;
                second = i;
                // n++;
            } else if (i > third && i < second) {
                third = i;
                // n++;
            }
            // System.out.println(first + " " + second + " " + third);
        }
        // System.out.println(n);
        // System.out.println(first + " " + second + " " + third);
        return third > Long.MIN_VALUE ? (int) third : (int) first;
    }

    public int countPrimes(int n) {
        boolean[] noPrimes = new boolean[n];
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (!noPrimes[i]) {
                count++;
                for (int j = 2; j * i < n; j++) {
                    noPrimes[j * i] = true;
                }
            }

        }
        return count;
    }

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();
        dfsSubsets(nums, new ArrayList<>(), results, 0);
        return results;
    }

    private void dfsSubsets(int[] nums, List<Integer> result, List<List<Integer>> results, int idx) {
        if (idx == nums.length) {
            results.add(new ArrayList<>(result));
            return;
        }

        result.add(nums[idx]);
        dfsSubsets(nums, result, results, idx + 1);
        result.remove(result.size() - 1);
        dfsSubsets(nums, result, results, idx + 1);
    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        for (int i = m + n - 1; i >= 0; i--) {
            if (m > 0 && n > 0) {
                if (nums2[n - 1] >= nums1[m - 1]) {
                    nums1[i] = nums2[n - 1];
                    n--;
                } else {
                    nums1[i] = nums1[m - 1];
                    m--;
                }
            } else if (m == 0) {
                nums1[i] = nums2[n - 1];
                n--;
            }
        }
        return;
    }

    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        dp[0][0] = 1;
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (r > 0 && c > 0) {
                    dp[r][c] = dp[r - 1][c] + dp[r][c - 1];
                } else if (r > 0) {
                    dp[r][c] = dp[r - 1][c];
                } else if (c > 0) {
                    dp[r][c] = dp[r][c - 1];
                }
            }
        }
        return dp[m - 1][n - 1];
    }

    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int n : nums) {
            pq.add(n);
            if (pq.size() == k + 1) {
                pq.poll();
            }
        }
        return pq.poll();
    }

    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                dp[r][c] = grid[r][c];
                if (r == 0 && c == 0) {
                    continue;
                }
                int left = Integer.MAX_VALUE;
                int up = Integer.MAX_VALUE;
                if (r > 0) {
                    left = dp[r - 1][c];
                }
                if (c > 0) {
                    up = dp[r][c - 1];
                }
                dp[r][c] += Math.min(left, up);
            }
        }
        // for (int[] a : dp) {
        //     for (int b : a) {
        //         System.out.print(b);
        //     }
        //     System.out.println();
        // }

        return dp[m - 1][n - 1];
    }

    public double knightProbability(int N, int K, int r, int c) {
        if (r >= N || c >= N || r < 0 || c < 0) {
            return 0.0;
        }
        if (K == 0) {
            return 1.0;
        }

        double[][][] dp = new double[K + 1][N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[0][i], 1.0);
        }

        int[] dx = new int[]{-1, -1, 1, 1, -2, -2, 2, 2};
        int[] dy = new int[]{-2, 2, -2, 2, -1, 1, -1, 1};

        for (int k = 1; k <= K; k++) {
            for (int m = 0; m < N; m++) {
                for (int n = 0; n < N; n++) {
                    double sum = 0.0;
                    for (int i = 0; i < 8; i++) {
                        if (m + dx[i] < N && m + dx[i] >= 0 && n + dy[i] < N && n + dy[i] >= 0) {
                            sum += dp[k - 1][m + dx[i]][n + dy[i]];
                        }
                    }
                    dp[k][m][n] = sum / 8;
                    // System.out.print(sum / 8 +  " ");
                }
                // System.out.println();
            }
            // System.out.println();
        }

        return dp[K][r][c];
    }

    public int subarraySum(int[] nums, int k) {
        int[] subSum = new int[nums.length];
        subSum[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            subSum[i] = subSum[i - 1] + nums[i];
        }

        int n = 0;
        for (int i = 0; i < nums.length; i++) {
            if (subSum[i] == k) {
                n++;
            }
            for (int j = 0; j < i; j++) {
                if (subSum[i] - subSum[j] == k) {
                    n++;
                }
            }
        }
        return n;
    }

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ress = new ArrayList<>();
        if (root == null) {
            return ress;
        }

        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        boolean zigzag = false;
        while (!q.isEmpty()) {
            int size = q.size();
            List<Integer> res = new ArrayList<>();
            while (size > 0) {
                TreeNode node = q.poll();
                if (zigzag) {
                    res.add(0, node.val);
                } else {
                    res.add(node.val);
                }
                size--;
                if (node.left != null) {
                    q.add(node.left);
                }
                if (node.right != null) {
                    q.add(node.right);
                }
            }
            if (zigzag) {
                zigzag = false;
            } else {
                zigzag = true;
            }
            ress.add(new ArrayList<>(res));
        }
        return ress;
    }

    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) {
            return false;
        }
        int r = 0, c = matrix[0].length - 1;
        while (r < matrix.length && c >= 0) {
            if (matrix[r][c] == target) {
                return true;
            } else if (matrix[r][c] > target) {
                c--;
            } else {
                r++;
            }
        }
        return false;
    }

    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int n = 0;
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if (grid[r][c] == '1') {
                    dfsIsland(r, c, grid);
                    n++;
                    // for (char[] cs : grid) {
                    //     for (char ch : cs) {
                    //         System.out.print(ch + " ");
                    //     }
                    //     System.out.println();
                    // }
                }

            }
        }
        return n;
    }

    private void dfsIsland(int r, int c, char[][] grid) {
        grid[r][c] = '0';
        if (r - 1 >= 0 && grid[r - 1][c] == '1') {
            dfsIsland(r - 1, c, grid);
        }
        if (r + 1 <= grid.length - 1 && grid[r + 1][c] == '1') {
            dfsIsland(r + 1, c, grid);
        }
        if (c - 1 >= 0 && grid[r][c - 1] == '1') {
            dfsIsland(r, c - 1, grid);
        }
        if (c + 1 <= grid[0].length - 1 && grid[r][c + 1] == '1') {
            dfsIsland(r, c + 1, grid);
        }
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // exit null, or p/q/lca
        if (root == null) {
            return root;
        }
        // System.out.println("r=" + root.val + " p=" + p.val + " q=" + q.val);
        if (root == p || root == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if ((left == p && right == q) || (left == q && right == p)) {
            return root;
        } else if (left == p || left == q || right == null) {
            return left;
        } else if (right == p || right == q || left == null) {
            return right;
        }
        return null;
    }

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0 || obstacleGrid[0].length == 0) {
            return 0;
        }
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (r == 0 && c == 0) {
                    dp[r][c] = 1;
                }
                if (obstacleGrid[r][c] == 1) {
                    dp[r][c] = 0;
                    continue;
                }
                if (r - 1 >= 0) {
                    dp[r][c] += dp[r - 1][c];
                }
                if (c - 1 >= 0) {
                    dp[r][c] += dp[r][c - 1];
                }
            }
        }
        return dp[m - 1][n - 1];
    }

    public int minSubArrayLen(int s, int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int left = 0;
        int sum = 0;
        int res = Integer.MAX_VALUE;

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            while (sum >= s) {
                res = Math.min(res, i + 1 - left);
                sum -= nums[left++];
            }
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }

    class RandomListNode {
        int label;
        RandomListNode next, random;

        RandomListNode(int x) {
            this.label = x;
        }
    }

    public RandomListNode copyRandomList(RandomListNode head) {
        if (head == null) {
            return null;
        }
        RandomListNode dummy = new RandomListNode(0);
        Map<RandomListNode, RandomListNode> map = new HashMap<>();
        RandomListNode cpHead = new RandomListNode(head.label);
        cpHead.random = head.random;
        map.put(head, cpHead);
        dummy.next = cpHead;
        while (head.next != null) {
            cpHead.next = new RandomListNode(head.next.label);
            cpHead.next.random = head.next.random;
            map.put(head.next, cpHead.next);
            head = head.next;
            cpHead = cpHead.next;
        }
        cpHead = dummy.next;
        while (cpHead != null) {
            if (cpHead.random != null) {
                cpHead.random = map.get(cpHead.random);
            }
            cpHead = cpHead.next;
        }
        return dummy.next;
    }

    public int jump(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = 1; j <= nums[i]; j++) {
                if (i + j < nums.length) {
                    dp[i + j] = Math.min(dp[i + j], dp[i] + 1);
                }
            }
        }
        return dp[nums.length - 1];
    }

    class MyHashMap {
        private int[] map;

        /**
         * Initialize your data structure here.
         */
        public MyHashMap() {
            map = new int[1000000];
            Arrays.fill(map, -1);
        }

        /**
         * value will always be non-negative.
         */
        public void put(int key, int value) {
            map[key] = value;
        }

        /**
         * Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key
         */
        public int get(int key) {
            return map[key];
        }

        /**
         * Removes the mapping of the specified value key if this map contains a mapping for the key
         */
        public void remove(int key) {
            map[key] = -1;
        }
    }

    public String fractionAddition(String expression) {
        // scanner to take a string
        // regex and zero-width positive lookahead assertion
        Scanner sc = new Scanner(expression).useDelimiter("/|(?=[+-])");
        int Numerator = 0;
        int Denominator = 1;
        while (sc.hasNext()) {
            int num = sc.nextInt();
            int den = sc.nextInt();
            // System.out.println(num + "/" + den);
            Numerator = Numerator * den + Denominator * num;
            Denominator *= den;
            // BE CAREFULL!! GCD of the new N/D
            // System.out.println(Numerator + "/" + Denominator);
            int gcd = gcd(Numerator, Denominator);
            // System.out.println(gcd);
            Numerator /= gcd;
            Denominator /= gcd;
            // System.out.println(Numerator + "/" + Denominator);
        }
        return Numerator + "/" + Denominator;
    }

    private int gcd(int a, int b) {
        return b != 0 ? gcd(b, a % b) : Math.abs(a);
    }


    public int coinChange(int[] coins, int amount) {
        if (coins == null || coins.length == 0) {
            return -1;
        }
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, -1);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (i - coin >= 0 && dp[i - coin] != -1) {
                    dp[i] = dp[i] == -1 ? dp[i - coin] + 1 : Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        return dp[amount];
    }

    public List<String> removeComments(String[] source) {
        List<String> res = new ArrayList<>();
        if (source == null || source.length == 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        boolean inBlock = false;
        for (String line : source) {
            // 1. comment out
            for (int i = 0; i < line.length(); i++) {
                if (inBlock) {
                    if (i < line.length() - 1 && line.charAt(i) == '*' && line.charAt(i + 1) == '/') {
                        inBlock = false;
                        i++;
                    }
                } else {
                    if (i < line.length() - 1 && line.charAt(i) == '/' && line.charAt(i + 1) == '*') {
                        inBlock = true;
                        i++;
                    } else if (i < line.length() - 1 && line.charAt(i) == '/' && line.charAt(i + 1) == '/') {
                        break;
                    } else {
                        sb.append(line.charAt(i));
                    }
                }
            }
            // 2. added to
            if (!inBlock && sb.length() > 0) {
                res.add(sb.toString());
                sb = new StringBuilder();
            }
        }
        return res;
    }

    public int numDecodings(String s) {
        // 先一个一个的看，再两个两个的看
        if (s == null || s.length() == 0) {
            return 0;
        }

        int[] dp = new int[s.length() + 1];
        dp[0] = 1;
        dp[1] = s.charAt(0) == '0' ? 0 : 1;
        for (int i = 2; i <= s.length(); i++) {
            //sigle digit
            dp[i] = s.charAt(i - 1) == '0' ? 0 : dp[i - 1]; // no 0 then accumulate, with 0 then no single digit.
            //two digits
            int twoDigits = Integer.valueOf(s.substring(i - 2, i));
            System.out.println(twoDigits);
            dp[i] += twoDigits >= 10 && twoDigits <= 26 ? dp[i - 2] : 0;
        }
        return dp[s.length()];
    }

    public int[][] multiply(int[][] A, int[][] B) {
        int m = A.length;
        int n = B.length;
        int o = B[0].length;
        int[][] res = new int[m][o];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (A[i][j] == 0) {
                    continue;
                }
                for (int k = 0; k < o; k++) {
                    if (B[j][k] == 0) {
                        continue;
                    }
                    res[i][k] += A[i][j] * B[j][k];
                }
            }
        }
        return res;
    }

    public int trap(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int res = 0;
        int left = 0;
        int right = height.length - 1;
        int curr = 0;
        while (left < right) {
            if (height[left] <= height[right]) {
                if (curr < height[left]) {
                    curr = height[left];
                }
                if (curr > height[left]) {
                    res += curr - height[left];
                }
                left++;
            } else {
                if (curr < height[right]) {
                    curr = height[right];
                }
                if (curr > height[right]) {
                    res += curr - height[right];
                }
                right--;
            }
        }
        return res;
    }

    public List<int[]> getSkyline(int[][] buildings) {
        List<int[]> res = new ArrayList<>();
        if (buildings == null || buildings.length == 0) {
            return res;
        }
        List<int[]> posHei = new ArrayList<>();
        for (int[] bd : buildings) {
            posHei.add(new int[]{bd[0], bd[2]});
            posHei.add(new int[]{bd[1], -bd[2]});
        }
        Collections.sort(posHei, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                // 先放进去，再出来。否则，当拿出和放入的是同一高度，会造成一个gap！！
                return a[0] == b[0] ? b[1] - a[1] : a[0] - b[0];
            }
        });
        TreeMap<Integer, Integer> heiNum = new TreeMap<>();
        heiNum.put(0, 1);
        int prev = 0;
        for (int[] ph : posHei) {
            if (ph[1] > 0) {
                heiNum.put(ph[1], heiNum.getOrDefault(ph[1], 0) + 1);
            } else {
                if (heiNum.get(-ph[1]) > 1) {
                    heiNum.put(-ph[1], heiNum.get(-ph[1]) - 1);
                } else {
                    heiNum.remove(-ph[1]);
                }
            }
            int curr = heiNum.lastKey();
            if (curr != prev) {
                res.add(new int[]{ph[0], curr});
                prev = curr;
            }
        }
        return res;
    }

    static class LRUCache {

        class ListNode {
            int key;
            int val;
            ListNode prev;
            ListNode next;

            public ListNode(int key, int val) {
                this.key = key;
                this.val = val;
            }
        }

        ListNode head;
        ListNode tail;
        int capacity;
        Map<Integer, ListNode> map;

        public LRUCache(int capacity) {
            this.capacity = capacity;
            this.head = new ListNode(0, 0);
            this.tail = new ListNode(0, 0);
            head.next = tail;
            tail.prev = head;
            this.map = new HashMap<>();
        }

        public int get(int key) {
            if (!map.containsKey(key)) {
                return -1;
            }
            ListNode curr = map.get(key);
            curr.next.prev = curr.prev;
            curr.prev.next = curr.next;
            moveToTail(curr);
            return map.get(key).val;
        }

        public void put(int key, int value) {
            if (get(key) != -1) {
                map.get(key).val = value;
                return;
            }
            if (map.size() == capacity) {
                map.remove(head.next.key);
                head.next.next.prev = head;
                head.next = head.next.next;

            }
            ListNode newAdd = new ListNode(key, value);
            map.put(key, newAdd);
            moveToTail(newAdd);
        }

        public void moveToTail(ListNode node) {
            tail.prev.next = node;
            node.prev = tail.prev;
            tail.prev = node;
            node.next = tail;
        }
    }

    public int shortestSubarray(int[] A, int K) {
        int res = Integer.MAX_VALUE;
        int[] pSums = new int[A.length + 1];
        for (int i = 1; i <= A.length; i++) {
            pSums[i] = pSums[i - 1] + A[i - 1];
        }
        Deque<Integer> dq = new LinkedList<>();
        for (int i = 0; i <= A.length; i++) {
            while (!dq.isEmpty() && pSums[i] <= pSums[dq.getLast()]) {
                dq.removeLast();
            }
            while (!dq.isEmpty() && pSums[i] - pSums[dq.getFirst()] >= K) {
                res = Math.min(res, i - dq.removeFirst());
            }
            dq.addLast(i);
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    public List<int[]> pacificAtlantic(int[][] matrix) {
        List<int[]> res = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return res;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        boolean[][] visitPa = new boolean[m][n];
        boolean[][] visitAt = new boolean[m][n];
        Queue<int[]> qPa = new LinkedList<>();
        Queue<int[]> qAt = new LinkedList<>();
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (r == 0 || c == 0) {
                    qPa.add(new int[]{r, c});
                    visitPa[r][c] = true;
                }
                if (r == m - 1 || c == n - 1) {
                    qAt.add(new int[]{r, c});
                    visitAt[r][c] = true;
                }
            }
        }
        paBfs(matrix, qPa, visitPa);
        paBfs(matrix, qAt, visitAt);
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                // System.out.println("pa " + visitPa[r][c]);
                // System.out.println("at " + visitAt[r][c]);
                if (visitPa[r][c] && visitAt[r][c]) {
                    res.add(new int[]{r, c});
                }
            }
        }
        return res;
    }

    public void paBfs(int[][] matrix, Queue<int[]> q, boolean[][] visit) {
        int[] dx = new int[]{0, 0, -1, 1};
        int[] dy = new int[]{-1, 1, 0, 0};
        while (!q.isEmpty()) {
            int[] cox = q.poll();
            for (int i = 0; i < 4; i++) {
                int x = cox[0] + dx[i];
                int y = cox[1] + dy[i];
                if (x >= 0 && y >= 0 && x < matrix.length && y < matrix[0].length && !visit[x][y] && matrix[x][y] >= matrix[cox[0]][cox[1]]) {
                    q.add(new int[]{x, y});
                    visit[x][y] = true;
                }
            }
        }
    }

    public String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0) {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        // (numerator < 0 && denominator > 0) || (numerator > 0 && denominator < 0)
        // XOR from logic operation: (numerator < 0 ^ denominator < 0)
        // Practical way:
        if (numerator < 0 != denominator < 0) sb.append("-");
        // Math.abs(Integer.MIN_VALUE) overflow
        long num = Math.abs((long) numerator);
        long den = Math.abs((long) denominator);
        sb.append(String.valueOf(num / den));
        long remainder = num % den;
        if (remainder == 0) return sb.toString();
        sb.append(".");
        Map<Long, Integer> map = new HashMap<>();
        while (remainder != 0) {
            if (map.containsKey(remainder)) {
                sb.insert(map.get(remainder), "(");
                sb.append(")");
                break;
            }
            map.put(remainder, sb.length());
            remainder *= 10;
            sb.append(String.valueOf(remainder / den));
            remainder %= den;
        }
        return sb.toString();
    }

    public int[] plusOne(int[] digits) {
        if (digits == null || digits.length == 0) {
            return digits;
        }
        int carry = 1;
        int idx = digits.length - 1;
        while (idx >= 0) {
            if (carry == 0) {
                break;
            }
            int sum = digits[idx] + carry;
            digits[idx] = sum % 10;
            carry = sum / 10;
            idx--;
        }
        int[] carryDigits = new int[digits.length + 1];
        if (carry == 1) {
            carryDigits[0] = 1;
            for (int i = 1; i < carryDigits.length; i++) {
                carryDigits[i] = digits[i - 1];
            }
        }
        return carry == 1 ? carryDigits : digits;
    }

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        while (head != null) {
            while (head.next != null && head.next.val == head.val) {
                head.next = head.next.next;
            }
            head = head.next;
        }
        return dummy.next;
    }
}
