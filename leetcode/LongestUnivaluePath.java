package leetcode;

import jiuzhang.binarytree.TreeNode;

public class LongestUnivaluePath {
    private int res =0;
    public int longestUnivaluePath(TreeNode root) {

        helper(root);

        return res;


    }

    private int helper(TreeNode root) {
        int curr = 0;

        if (root == null) {
            return curr;
        }

        int left = helper(root.left);
        int right = helper(root.right);

        if (root.left != null && root.left.val == root.val && root.right != null && root.right.val == root.val) {
            curr = left + right + 2;
            res = Math.max(curr, res);
            curr = Math.max(left + 1, right + 1);
        } else if (root.left != null && root.left.val == root.val) {
            curr = left + 1;
            res = Math.max(curr, res);
        } else if (root.right != null && root.right.val == root.val) {
            curr = right + 1;
            res = Math.max(curr, res);
        } else {
            curr = Math.max(left, right);
            res = Math.max(curr, res);
            curr = 0;
        }





        return curr;
    }
}
