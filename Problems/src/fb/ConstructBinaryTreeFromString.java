package fb;

import java.util.Stack;

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

    public TreeNode str2treeSTACK(String s) {
        Stack<TreeNode> stack = new Stack<>();
        for (int i = 0, j = i; i < s.length(); i++, j = i) {
            char c = s.charAt(i);
            if (c == ')') stack.pop();
            else if (c >= '0' && c <= '9' || c == '-') {
                while (i + 1 < s.length() && s.charAt(i + 1) >= '0' && s.charAt(i + 1) <= '9') i++;
                TreeNode currentNode = new TreeNode(Integer.valueOf(s.substring(j, i + 1)));
                if (!stack.isEmpty()) {
                    TreeNode parent = stack.peek();
                    if (parent.left != null) parent.right = currentNode;
                    else parent.left = currentNode;
                }
                stack.push(currentNode);
            }
        }
        return stack.isEmpty() ? null : stack.peek();
    }
}
