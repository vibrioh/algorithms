package fb;

import javax.swing.tree.TreeNode;

public class BinaryTreeMaximumPathSum {
    int max = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        maxThroughRoot(root);
        return max;
    }

    private Integer maxThroughRoot(TreeNode root) {
        if (root == null) {
            return null;
        }
        Integer left = maxThroughRoot(root.left);
        Integer right = maxThroughRoot(root.right);
        int rootMax, currMax;
        if (left == null && right == null) {
            rootMax = root.val;
            currMax = root.val;
        } else if (left == null) {
            rootMax = Math.max(root.val, root.val + right);
            currMax = Math.max(rootMax, right);
        } else if (right == null) {
            rootMax = Math.max(root.val, root.val + left);
            currMax = Math.max(rootMax, left);
        } else {
            int childMax = Math.max(left, right);
            rootMax = Math.max(root.val, childMax + root.val);
            currMax = Math.max(childMax, rootMax);
            currMax = Math.max(currMax, root.val + left + right);
        }
        max = Math.max(max, currMax);
        return rootMax;
    }

    class TreeNode {
        TreeNode left;
        TreeNode right;
        int val;
    }
}
