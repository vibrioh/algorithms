package jiuzhang.binarytree;

import java.util.HashMap;
import java.util.Map;

/**
 * 全局变量是放在method所在的类下面的，创建的其他类的constructor中并不是全局变量！！
 * 弄清楚哪些参数是通过递归传递回来的，哪些参数是作为全局变量保存在类中的？
 * 【【【【每次递归都变化的参数需要通过自身return值来传递，在特定情况下才变化的可以保存在全局变量中！！！！！！！！！！！！！】】】】
 */
public class MaxMeanSubtree {
//  class ResultType {
//    public TreeNode node;
//    public int sum, num, maxSum, maxNum;
//
//    public ResultType(TreeNode node, int sum, int num, int maxSum, int maxNum) {
//      this.node = node;
//      this.sum = sum;
//      this.num = num;
//      this.maxSum = maxSum;
//      this.maxNum = maxNum;
//    }
//  }
//
//  public TreeNode maxMeanNode(TreeNode root) {
//    return helper(root).node;
//  }
//
//  public ResultType helper(TreeNode root) {
//    ResultType result = new ResultType(root, 0, 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
//
//    if (root == null) {
//      return result;
//    }
//
//    System.out.printf("P(1) node->[%d] sum=%d num=%d maxSum=%d maxNum=%d \n", result.node.val, result.sum, result.num, result.maxSum, result.maxNum);
//
//    ResultType left = helper(root.left);
//    ResultType right = helper(root.right);
//
//    System.out.printf("P(2) node->[%d] sum=%d num=%d maxSum=%d maxNum=%d \n", result.node.val, result.sum, result.num, result.maxSum, result.maxNum);
//
//    result.sum = root.val + left.sum + right.sum;
//    result.num = 1 + left.num + right.num;
//    result.maxSum = (left.maxSum * right.maxNum >= right.maxSum * left.maxNum) ? left.maxSum : right.maxSum;
//    result.maxNum = (left.maxSum * right.maxNum >= right.maxSum * left.maxNum) ? left.maxNum : right.maxNum;
//    result.node = (left.maxSum * right.maxNum >= right.maxSum * left.maxNum) ? left.node: right.node;
//
//
//
//    if (result.sum * result.maxNum >= result.maxSum * result.num) {
//      result.maxSum = result.sum;
//      result.maxNum = result.num;
//      result.node = root;
//      System.out.printf("P(3) node->[%d] sum=%d num=%d maxSum=%d maxNum=%d \n", result.node.val, result.sum, result.num, result.maxSum, result.maxNum);
//    }
//
//    System.out.printf("P(4) node->[%d] sum=%d num=%d maxSum=%d maxNum=%d \n", result.node.val, result.sum, result.num, result.maxSum, result.maxNum);
//
//    return result;
//  }

  class Mean{
    public int sum, size;

    public Mean(int sum, int size) {
      this.sum = sum;
      this.size = size;
    }
  }

  private TreeNode theNode = null;
  private Mean theMean = null;

  public TreeNode maxMeanNode(TreeNode root) {
    helper(root);
    return theNode;
  }

  public Mean helper(TreeNode root) {
    Mean mean = new Mean(0, 0);

    if (root == null) {
      return mean;
    }

    Mean left = helper(root.left);
    Mean right = helper(root.right);

    mean.sum = root.val + left.sum + right.sum;
    mean.size = 1 + left.size + right.size;

    if (theMean == null) {
      theMean = mean;
      theNode = root;
    }

    if (mean.sum * theMean.size > mean.size * theMean.sum) {
      theMean = mean;
      theNode = root;
    }

    return mean;
  }

  public static void main(String[] args) {
    TreeNode a = new TreeNode(1);
    TreeNode b = new TreeNode(-5);
    TreeNode c = new TreeNode(11);
    TreeNode d = new TreeNode(1);
    TreeNode e = new TreeNode(2);
    TreeNode f = new TreeNode(4);
    TreeNode g = new TreeNode(-2);
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
    MaxMeanSubtree classInstance = new MaxMeanSubtree();
    System.out.println(classInstance.maxMeanNode(a).val);
  }
}



