package jiuzhang.binarytree;

/**
 * Given the root and two nodes in a Binary Tree. Find the lowest common ancestor(LCA) of the two nodes.

 The lowest common ancestor is the node with largest depth which is the ancestor of both nodes.

 Example
 For the following binary tree:

 4
 / \
 3   7
 / \
 5   6
 LCA(3, 5) = 4

 LCA(5, 6) = 7

 LCA(6, 7) = 7
 */

public class LowestCommonAncestor {


  class ResultType {
    boolean hasA, hasB;

    public ResultType(boolean hasA, boolean hasB) {
      this.hasA = hasA;
      this.hasB = hasB;
    }
  }

  private TreeNode theNode = null;

  /**
   * @param root: The root of the binary search tree.
   * @param A and B: two nodes in a Binary.
   * @return: Return the least common ancestor(LCA) of the two nodes.
   */
  public TreeNode lowestCommonAncestor(TreeNode root, TreeNode A, TreeNode B) {
    int a = A.val;
    int b = B.val;
    helper(root, a, b);
    return theNode;
  }

  public ResultType helper(TreeNode root, int a, int b) {
    ResultType result = new ResultType(false, false);

    if (root == null) {
      return result;
    }

    ResultType left = helper(root.left, a, b);
    ResultType right = helper(root.right, a, b);

    if (left.hasA || right.hasA || root.val == a) {
      result.hasA = true;
    }
    if (left.hasB || right.hasB || root.val == b) {
      result.hasB = true;
    }

    if (result.hasA && result.hasB && theNode == null) {
      theNode = root;
    }

    return result;

  }
}
