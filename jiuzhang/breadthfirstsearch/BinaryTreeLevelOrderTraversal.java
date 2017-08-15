package jiuzhang.breadthfirstsearch;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import jiuzhang.binarytree.TreeNode;

public class BinaryTreeLevelOrderTraversal {
  /**
   * @param root: The root of binary tree.
   * @return Level order a list of lists of integer
   */
  public ArrayList<ArrayList<Integer>> levelOrder(TreeNode root) {

    ArrayList<ArrayList<Integer>> result = new ArrayList<>();

    if (root == null) {
      return result;
    }

    Queue<TreeNode> queue = new LinkedList<>();
    queue.add(root);

    while(!queue.isEmpty()) {
      ArrayList<Integer> level = new ArrayList<>();
      int size = queue.size();    // because queue.size() is dynamic, we need to archive current level size
      for (int i = 0; i < size; i++) {    // add this for loop to poll all of this level
        TreeNode node = queue.poll();
        level.add(node.val);
        if (node.left != null) {
          queue.offer(node.left);
        }
        if (node.right != null) {
          queue.offer(node.right);
        }
      }
      result.add(level);
    }

    return result;
  }
}
