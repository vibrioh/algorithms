package jiuzhang.binarytree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PathSum {

  public List<List<Integer>> binaryTreePathSum(TreeNode root, int target) {
    List<List<Integer>> allPaths = getPaths(root);
    List<List<Integer>> paths = new ArrayList<>();
    System.out.printf("binaryTreePathSum %s \n", allPaths);
    for (List<Integer> path : allPaths) {
      if (sum(path) == target) {
        paths.add(path);
      }
    }
    return paths;
  }

  public int sum(List<Integer> path) {
    int sum = 0;
    for (int n : path) {
      sum += n;
    }
    return sum;
  }

  public List<List<Integer>> getPaths(TreeNode root) {
    List<List<Integer>> paths = new ArrayList<>();

    if (root == null) {
      return paths;
    }

    List<List<Integer>> left = getPaths(root.left);
    List<List<Integer>> right = getPaths(root.right);

    if (left.isEmpty() && right.isEmpty()) {
      List<Integer> path = new ArrayList<>();
      path.add(root.val);
      paths.add(path);
      System.out.printf("if %s \n", paths);
    }
    if (!left.isEmpty()) {
      for (List<Integer> path : left) {
        path.add(0, root.val);
        paths.add(path);
      }
    }
    if (!right.isEmpty()) {
      for (List<Integer> path : right) {
        path.add(0, root.val);
        paths.add(path);
      }
    }

    System.out.printf("getPaths %s \n", paths);

    return paths;
  }

  public static void main(String[] args) {
    TreeNode a = new TreeNode(1);
    TreeNode b = new TreeNode(2);
    TreeNode c = new TreeNode(4);
    TreeNode d = new TreeNode(2);
    TreeNode e = new TreeNode(3);
//    TreeNode f = new TreeNode(4);
//    TreeNode g = new TreeNode(7);
    a.left = b;
    a.right = c;
    b.left = d;
    b.right = e;
//    c.left = f;
//    c.right = g;
    Map<String, TreeNode> map = new HashMap<>();
    map.put("a", a);
    map.put("b", b);
    map.put("c", c);
    map.put("d", d);
    map.put("e", e);
//    map.put("f", f);
//    map.put("g", g);
    PathSum classInstance = new PathSum();
    System.out.println(classInstance.binaryTreePathSum(a, 5));
  }

}
