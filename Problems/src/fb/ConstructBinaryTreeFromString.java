package fb;

public class ConstructBinaryTreeFromString {
    int idx = 0;

    public TreeNode str2tree(String s) {
        int len = s.length();
        if (len == 0 || idx >= len)
            return null;
        // get val and sign, will not start with (), must start with num strings
        int sig = 1, k = 0;
        if (s.charAt(idx) == '-') {
            sig = -1;
            idx++;
        }
        while (idx < len && s.charAt(idx) >= '0' && s.charAt(idx) <= '9') {
            k = k * 10 + s.charAt(idx) - '0';
            idx++;
        }
        TreeNode root = new TreeNode(sig * k);
        if (idx == len || s.charAt(idx) == ')') {
            idx++;
            return root;
        }
        // '(' 左边的
        idx++;
        root.left = str2tree(s);
        if (idx == len || s.charAt(idx) == ')') {
            idx++;
            return root;
        }
        // '(' 右边的
        idx++;
        root.right = str2tree(s);
        // recur 中的结束
        if (idx == len || s.charAt(idx) == ')') {
            idx++;
            return root;
        }
        // 这个root本身
        return root;
    }

    class TreeNode {
        TreeNode left;
        TreeNode right;
        int val;

        TreeNode(int val) {
            this.val = val;
        }
    }
}
