package fb;

import javax.swing.tree.TreeNode;
import java.util.Stack;

// initial, put current left branch into the stack
// finished left, if there is right branch, always put current left branch into the stack

// In a word, always put current left branch into the stack
public class BinarySearchTreeIterator {
    class BSTIterator {
        Stack<TreeNode> leftBranch = new Stack<>();

        public BSTIterator(TreeNode root) {
            while (root != null) {
                leftBranch.push(root);
                root = root.left;
            }
        }

        /**
         * @return the next smallest number
         */
        public int next() {
            TreeNode next = leftBranch.pop();
            TreeNode newRoot = next.right;
            while (newRoot != null) {
                leftBranch.push(newRoot);
                newRoot = newRoot.left;
            }
            return next.val;
        }

        /**
         * @return whether we have a next smallest number
         */
        public boolean hasNext() {
            return !leftBranch.isEmpty();
        }
    }
}
