package jiuzhang.binarytree;

import java.util.HashMap;
import java.util.Map;

public class LongestConsecutive {

  private int longest;

  public int longestConsecutive(TreeNode root) {
    helper(root);
    return longest;
  }

  public int helper(TreeNode root) {
    int len = 0;

    if (root == null) {
      return len;
    }

    int left = helper(root.left);
    int right = helper(root.right);

    int lenL = (root.left != null && root.val + 1 == root.left.val) ? left + 1 : 1;
    int lenR = (root.right != null && root.val + 1 == root.right.val) ? right + 1 : 1;
    len = (lenL > lenR) ? lenL : lenR;

    if (len > longest) {
      longest = len;
    }

    return len;
  }

  public static void main(String[] args) {
    TreeNode a = new TreeNode(1);
    TreeNode b = new TreeNode(2);
    TreeNode c = new TreeNode(6);
    TreeNode d = new TreeNode(3);
    TreeNode e = new TreeNode(5);
    TreeNode f = new TreeNode(4);
    TreeNode g = new TreeNode(7);
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
    LongestConsecutive classInstance = new LongestConsecutive();
    System.out.println(classInstance.longestConsecutive(a));
  }
}
