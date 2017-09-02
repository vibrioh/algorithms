package leetcode;

import jiuzhang.binarytree.TreeNode;

/**
 * Created by vibrioh on 9/1/17.
 */
public class InvertBinaryTree {
    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode(int x) { val = x; }
     * }
     */
    class Solution {
        public TreeNode invertTree(TreeNode root) {
            if (root == null) {
                return root;
            }

            TreeNode temp = invertTree(root.left);
            root.left = invertTree(root.right);
            root.right = temp;

            return root;
        }
    }
}
