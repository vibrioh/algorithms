package leetcode;

import jiuzhang.binarytree.TreeNode;

public class ConvertBSTToGreaterTree {
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
    public TreeNode convertBST(TreeNode root) {
      inOrderHelper(root, 0);
      return root;
    }

    private int inOrderHelper(TreeNode root, int sum) {
      if (root == null) {
        return sum;
      }

      sum = inOrderHelper(root.right, sum) + root.val;
      root.val = sum;
      return inOrderHelper(root.left, sum);
    }
  }
}
