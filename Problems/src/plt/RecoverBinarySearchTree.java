package plt;

/*
How do we find these two elements? For example, we have the following tree that is printed as in order traversal:

6, 3, 4, 5, 2

We compare each node with its next one and we can find out that 6 is the first element to swap because 6 > 3 and 2 is the second element to swap because 2 < 5.
 */
class TreeNode {
    TreeNode left;
    TreeNode right;
    int val;
}

public class RecoverBinarySearchTree {
    TreeNode preNode = null;
    TreeNode firstNode = null;
    TreeNode secondNode = null;

    public void recoverTree(TreeNode root) {
        if (root == null)
            return;
        traverse(root);
        int tmp = firstNode.val;
        firstNode.val = secondNode.val;
        secondNode.val = tmp;
    }

    private void traverse(TreeNode root) {
        if (root == null)
            return;
        traverse(root.left);
        if (preNode != null) {
            if (firstNode == null && preNode.val >= root.val)
                firstNode = preNode;
            if (firstNode != null && preNode.val >= root.val)
                secondNode = root;
        }
        preNode = root;
        traverse(root.right);
    }
}
