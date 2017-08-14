package jiuzhang.binarytree;

/**
 *
 * Depth of one node means the maximum height of the subtrees plus 1.
 * So the depth of the subtrees of every node never differ by more than 1
 *
 Given a binary tree, determine if it is height-balanced.

 For this problem, a height-balanced binary tree is defined as
 a binary tree in which the depth of the two subtrees of every node never differ by more than 1.

 */
public class BalancedBinaryTree {

  public boolean isBalanced(TreeNode root) {
    return helper(root).isBalanced;
  }

  public ResultType helper(TreeNode root) {
    ResultType result = new ResultType(true, 0);

    if (root == null) {
      return result;
    }

    ResultType left = helper(root.left);
    ResultType right = helper(root.right);

    if ((!left.isBalanced) || (!right.isBalanced) || (Math.abs(left.depth - right.depth) > 1)) {
      return new ResultType(false, -1);
    }

    result.depth = Math.max(left.depth, right.depth) + 1;

    return result;
  }
}

class ResultType{
  public boolean isBalanced;
  public int depth;

  public ResultType(boolean isBalanced, int depth) {
    this.isBalanced = isBalanced;
    this.depth = depth;
  }
}
