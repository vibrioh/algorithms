package jiuzhang.binarytree;

import java.util.HashMap;
import java.util.Map;

/**
 * Description
 Given a binary tree, find the subtree with minimum sum. Return the root of the subtree.

 Notice
 LintCode will print the subtree which root is your return node.
 It's guaranteed that there is only one subtree with minimum sum and the given binary tree is not an empty tree.


 Example
 Given a binary tree:

 1
 /   \
 -5      2
 / \     /  \
 0   2 -4  -5
 return the node 1.
 */
public class MinSumSubtree {

//  public static TreeNode minSumNode (TreeNode root) {
//
//    if (root == null) {
//      return null;
//    }
//
//    TreeNode leftMinNode = minSumNode(root.left);
//    TreeNode rightMinNode = minSumNode(root.right);
//
//    int left = sumTree(leftMinNode);
//    int right = sumTree(rightMinNode);
//    int curr = sumTree(root);
//
//    if (left < right && left < curr) {
//      return leftMinNode;
//    }
//    if (right < left && right < curr) {
//      return rightMinNode;
//    }
//    return root;
//  }
//
//  public static int sumTree(TreeNode root) {
//    if (root == null) {
//      return 0;
//    }
//    return root.val + sumTree(root.left) + sumTree(root.right);
//  }

  private TreeNode minNode = null;
  private int minSum = Integer.MAX_VALUE;

  public TreeNode minSumNode(TreeNode root) {
    sum(root);
    return minNode;
  }

  public int sum(TreeNode root) {
    int sum = 0;
    if (root == null) {
      return sum;
    }

    sum = root.val + sum(root.left) +sum(root.right);

    if (sum < minSum) {
      minSum = sum;
      minNode = root;
    }

    return sum;

  }


  public static void main(String[] args) {
    TreeNode a = new TreeNode(1);
    TreeNode b = new TreeNode(-5);
    TreeNode c = new TreeNode(2);
    TreeNode d = new TreeNode(0);
    TreeNode e = new TreeNode(2);
    TreeNode f = new TreeNode(-4);
    TreeNode g = new TreeNode(-5);
    a.left = b;
    a.right = c;
    b.left = d;
    b.right = e;
    c.left = f;
    c.right = g;
    Map<String, TreeNode> map = new HashMap<>();
    map.put("a", a);
    map.put("b", b);
    map.put("c", c);
    map.put("d", d);
    map.put("e", e);
    map.put("f", f);
    map.put("g", g);
    MinSumSubtree classInstance = new MinSumSubtree();
    for (Map.Entry<String, TreeNode> entry : map.entrySet()) {
      System.out.println(entry.getKey() + "->" + classInstance.sum(entry.getValue()));
    }
    for (TreeNode node : map.values()) {
      System.out.println(classInstance.minSumNode(node).val);
      classInstance.minSum = Integer.MAX_VALUE;    // 全局变量，在外改变了就回不去了。
    }

  }
}
