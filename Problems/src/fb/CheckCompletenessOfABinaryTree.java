package fb;

import java.util.LinkedList;
import java.util.Queue;

/*
hen level-order traversal in a complete tree, after the last node, all nodes in the queue should be null.
Otherwise, the tree is not complete.
 */

public class CheckCompletenessOfABinaryTree {
    public boolean isCompleteTree(TreeNode root) {
        boolean allNull = false;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            TreeNode curr = q.poll();
            if (curr == null) {
                allNull = true;
            } else {
                if (allNull) {
                    return false;
                }
                q.offer(curr.left);
                q.offer(curr.right);
            }
        }
        return true;
    }

    class TreeNode {
        TreeNode left;
        TreeNode right;
    }
}
