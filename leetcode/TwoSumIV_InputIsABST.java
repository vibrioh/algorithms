package leetcode;

import java.util.ArrayList;
import java.util.List;
import jiuzhang.binarytree.TreeNode;

public class TwoSumIV_InputIsABST {
  /**
   * ArrayList.toArray(new Object{.size()})
   * ArrayList.get(ArrayList.size() - 1);
   *
   *
   *
   * Definition for a binary tree node.
   * public class TreeNode {
   *     int val;
   *     TreeNode left;
   *     TreeNode right;
   *     TreeNode(int x) { val = x; }
   * }
   */
  class Solution {
    public boolean findTarget(TreeNode root, int k) {
      List<Integer> arr = toOrderedArrayList(root);

      int i = 0;
      int j = arr.size() -1;
      while (i < j) {
        if (arr.get(i) + arr.get(j) == k) {
          return true;
        } else if (arr.get(i) + arr.get(j) < k) {
          i++;
        } else if (arr.get(i) + arr.get(j) > k) {
          j--;
        }
      }

      return false;
    }

    private List<Integer> toOrderedArrayList(TreeNode root) {
      List<Integer> arrList = new ArrayList<>();
      if (root == null){
        return arrList;
      }

      arrList.addAll(toOrderedArrayList(root.left));
      arrList.add(root.val);
      arrList.addAll(toOrderedArrayList(root.right));

      return arrList;
    }
  }
}
