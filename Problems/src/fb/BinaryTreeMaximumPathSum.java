package fb;

import javax.swing.tree.TreeNode;

public class BinaryTreeMaximumPathSum {
    private int max = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        maxRootPath(root);
        return max;
    }

    private int maxRootPath(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = Math.max(0, maxRootPath(root.left));
        int right = Math.max(0, maxRootPath(root.right));
        max = Math.max(left + right + root.val, max);
        return Math.max(root.val + left, root.val + right);
    }

    class TreeNode {
        TreeNode left;
        TreeNode right;
        int val;
    }
}
