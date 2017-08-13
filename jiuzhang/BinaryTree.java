package jiuzhang;

import java.util.ArrayList;
import java.util.Stack;
import jiuzhang.binarytree.TreeNode;

/**
 * Solve traversal pre/in/post-order by iteration/traverse-recursion/divide-conquer-recursion
 * Travers vs Divide Conquer:
 *        # Both recursion
 *        # Result in parameter vs Result in return value
 *        # Top down vs Bottom up
 */
public class BinaryTree {


  class NonRecursion {

    public ArrayList<Integer> preOrderTraversal(TreeNode root) {
      Stack<TreeNode> stack = new Stack<>();
      ArrayList<Integer> preOrder = new ArrayList<>();

      if (root == null) {
        return preOrder;
      }

      stack.push(root);
      while (!stack.isEmpty()) {
        TreeNode node = stack.pop();
        preOrder.add(node.val);
        if (node.right != null) {
          stack.push(node.right);
        }
        if (node.left != null) {
          stack.push(node.left);
        }
      }

      return preOrder;
    }


    public ArrayList<Integer> inOrderTraversal(TreeNode root) {
      Stack<TreeNode> stack = new Stack<>();
      ArrayList<Integer> inOrder = new ArrayList<>();
      TreeNode curr = root;

      while (curr != null || !stack.isEmpty()) {
        while (curr != null) {
          stack.add(curr);
          curr = curr.left;
        }
        curr = stack.pop();
        inOrder.add(curr.val);
        curr = curr.right;
      }

      return inOrder;
    }

    public ArrayList<Integer> postOrderTraversal(TreeNode root) {
      Stack<TreeNode> stack = new Stack<>();
      ArrayList<Integer> postOrder = new ArrayList<>();
      TreeNode prev = null;    // previously traversed node
      TreeNode curr = root;

      if (root == null) {
        return postOrder;
      }

      stack.push(root);
      while (!stack.empty()) {
        curr = stack.peek();
        if (prev == null || prev.left == curr || prev.right == curr) {    // travers down the tree
          if (curr.left != null) {
            stack.push(curr.left);
          } else if (curr.right != null) {
            stack.push(curr.right);
          }
        } else if (curr.left == prev) {    // travers up the tree from the left
          if (curr.right != null) {
            stack.push(curr.right);
          }
        } else {    // traverse up the tree from right
          postOrder.add(curr.val);
          stack.pop();
        }
        prev = curr;
      }

      return postOrder;
    }
  }

  class TraverseRecursion{    // 用到全局变量，往往不需要return值

    /**
     * 递归三要素：
     * 1.递归的定义：把root为根的preorder放到preOrder（result）里面（准备好接口）
     * 2.递归的拆解：A-把root放到preOrder里面，B-把左支放到preOrder里面，C-把右支放到preOrder里面
     * 3.递归的出口：Base Case --> 递归到NULL节点，退出
     *
     * TraverseRecursion 用到全局变量， 一般不需要return一个值
     * @param root: The root of binary tree.
     * @return Inorder in ArrayList which contains node values.
     */
    public ArrayList<Integer> preOrderTraversal(TreeNode root) {
      ArrayList<Integer> preOrder = new ArrayList<>();
      traverse(root, preOrder);
      return preOrder;
    }

    public void traverse(TreeNode root, ArrayList<Integer> preOrder) {
      if (root == null) {
        return;
      }

      preOrder.add(root.val);    // for in/post-order, just put this statement in the middle/ at the end of this order
      traverse(root.left, preOrder);
      traverse(root.right, preOrder);
    }


  }


  class DivideConquerRecursion{

    /**
     * 分治，先分后合
     * 无脑分到左边，右边，再看看怎么合起来
     * 1.递归的定义：给我root，回传preorder
     * 2.递归的拆解：拆为左右两边，再合并
     * 3.递归的出口：
     */
    public ArrayList<Integer> inOrderTraversal(TreeNode root) {
      ArrayList<Integer> inOrder = new ArrayList<>();

      if (root == null) {
        return inOrder;
      }

      // 得到左右两边传回的结果，按inorder顺序放入
      ArrayList<Integer> left = inOrderTraversal(root.left);
      ArrayList<Integer> right = inOrderTraversal(root.right);

      inOrder.addAll(left);
      inOrder.add(root.val);
      inOrder.addAll(right);

      return inOrder;
    }
  }



}
