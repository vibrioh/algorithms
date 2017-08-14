package jiuzhang.binarytree;

public class FlattenBinaryTree {
  /*
       * @param root: a TreeNode, the root of the binary tree
       * @return:
       */
  public void flatten(TreeNode root) {
    // definition of recursion: return the current last node
    helper(root);
  }

  public TreeNode helper(TreeNode root) {
    if(root == null) {
      return null;
    }

    TreeNode left = helper(root.left);
    TreeNode right = helper(root.right);

    if (left == null && right == null) {
      return root;
    } else if (left == null && right != null) {
      return right;
    } else if (left != null && right == null) {
      root.right = root.left;
      root.left = null;
      return left;
    } else {
      TreeNode temp = root.right;
      root.right = root.left;
      root.left = null;
      left.right = temp;
      return right;
    }
  }
}
