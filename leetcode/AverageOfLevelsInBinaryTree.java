package leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import jiuzhang.binarytree.TreeNode;

public class AverageOfLevelsInBinaryTree {
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
    public List<Double> averageOfLevels(TreeNode root) {
      List<Double> res = new ArrayList<>();
      Queue<TreeNode> queue = new LinkedList<>();

      queue.offer(root);
      while(!queue.isEmpty()) {
        int level = queue.size();
        long sum = 0;
        for (int i = 0; i < level; i++) {
          TreeNode node = queue.poll();
          if (node.left != null) {
            queue.offer(node.left);
          }
          if (node.right != null) {
            queue.offer(node.right);
          }
          sum += node.val;
        }
        res.add((double) sum / level);
      }

      return res;
    }
  }
}
