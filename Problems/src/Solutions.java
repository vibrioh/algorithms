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
        Map<String, List<String>> map = new HashMap();
        for (int i = 0; i < strs.length; i++) {
            char[] ca = strs[i].toCharArray();
            Arrays.sort(ca);
            String sca = String.valueOf(ca);
            map.putIfAbsent(sca, new ArrayList<>());
            map.get(sca).add(strs[i]);
        }
        return new ArrayList<>(map.values());
    }
}

}
