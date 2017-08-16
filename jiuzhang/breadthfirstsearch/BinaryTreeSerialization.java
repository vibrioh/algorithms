package jiuzhang.breadthfirstsearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import jiuzhang.binarytree.TreeNode;

public class BinaryTreeSerialization {

  /**
   * This method will be invoked first, you should design your own algorithm to serialize a binary
   * tree which denote by a root node to a string which can be easily deserialized by your own
   * "deserialize" method later.
   */
  public String serialize(TreeNode root) {
    // write your code here
    StringBuilder result = new StringBuilder("{}");

    if (root == null) {
      return result.toString();
    }

    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);

    while (!queue.isEmpty()) {
      TreeNode node = queue.poll();

      if (node == null) {
        result.insert(result.length() - 1, "#,");
      } else {
        result.insert(result.length() - 1, node.val + ",");
        queue.offer(node.left);
        queue.offer(node.right);
      }
    }

    while (result.charAt(result.length() - 2) == '#' || result.charAt(result.length() - 2) == ',') {
      result.deleteCharAt(result.length() - 2);
    }

    return result.toString();
  }

  /**
   * This method will be invoked second, the argument data is what exactly you serialized at method
   * "serialize", that means the data is not given by system, it's given by your own serialize
   * method. So the format of data is designed by yourself, and deserialize it here as you serialize
   * it in "serialize" method.
   */
  public TreeNode deserialize(String data) {
    Queue<TreeNode> queue = new LinkedList<>();
    List<TreeNode> list = new ArrayList<>();

    for (String str : data.substring(1, data.length() - 1).split(",")) {
      /**
       * us "equals()" to compare String object, or it will compare RAM address
       */
      if (str.equals("#") || str.equals("")) {    // need to consider null condition
        TreeNode node = null;
        queue.offer(node);
        list.add(node);
      } else {
        TreeNode node = new TreeNode(Integer.parseInt(str));
        queue.offer(node);
        list.add(node);
      }
    }

    TreeNode root = queue.poll();

    for (TreeNode n : list) {
      if (n != null) {
        if (!queue.isEmpty()) {
          n.left = queue.poll();
        }
        if (!queue.isEmpty()) {
          n.right = queue.poll();
        }
      }
    }

    return root;
  }


  public static void main(String[] args) {
    TreeNode a = new TreeNode(1);
    TreeNode b = new TreeNode(2);
    TreeNode c = new TreeNode(3);
    TreeNode d = new TreeNode(4);
    TreeNode e = new TreeNode(5);
    TreeNode f = new TreeNode(6);
    TreeNode g = new TreeNode(7);
    a.left = b;
    a.right = c;
    //b.left = d;
    //b.right = e;
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
    BinaryTreeSerialization classInstance = new BinaryTreeSerialization();
    System.out.println(classInstance.serialize(a));
    System.out.println(classInstance.deserialize(classInstance.serialize(a)).val);
  }
}
