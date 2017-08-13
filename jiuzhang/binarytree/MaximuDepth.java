package jiuzhang.binarytree;

public class MaximuDepth {

  public int maxDepth(TreeNode root) {
    // when first look at the node, the depth is initialized as 0
    int max = 0;

    // if root == null the depth is 0 as we created
    if (root == null) {
      return max;
    }

    // peek depth of branches
    int left = maxDepth(root.left);
    int right = maxDepth(root.right);
    max = (left >= right) ? (left + 1) : (right + 1);    // Math.max(left, right) + 1

    return max;
  }

}
