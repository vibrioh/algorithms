package jiuzhang.binarytree;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreePaths {

  public List<String> binaryTreePaths(TreeNode root) {
    List<String> paths = new ArrayList<>();

    // is null node, no path at all
    if (root == null) {
      return paths;
    }

    // if leaf node, no more paths will get
    if (root.left == null && root.right == null) {
      paths.add(Integer.toString(root.val));    // also String.valueOf(root.val)
      return paths;
    }

    // then add other paths from right left without brain
    List<String> leftPaths = binaryTreePaths(root.left);
    List<String> rightPaths = binaryTreePaths(root.right);

    for (String path : leftPaths) {    // if leftPaths/rightPaths null, won't do for loop, won't add anything
      paths.add(root.val + "->" + path);
    }
    for (String path : rightPaths) {    // be care of missing for each type
      paths.add(root.val + "->" + path);
    }

    return paths;
  }
}
