package cc189.c5;

import jiuzhang.binarytree.TreeNode;

public class MinimalTree {

  public TreeNode createMinBST(int[] array) {
    return dfsHelper(array, 0, array.length-1);
  }

  public TreeNode dfsHelper(int[] arr, int start, int end) {
    if (end < start) {
      return null;
    }

    int mid = start + (end - start) / 2;
    TreeNode n = new TreeNode(arr[mid]);
    n.left = dfsHelper(arr, start, mid - 1);
    n.right = dfsHelper(arr, mid + 1, end);
    return n;
  }
}
