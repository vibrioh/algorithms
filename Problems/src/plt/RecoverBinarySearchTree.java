package plt;

/*
How do we find these two elements? For example, we have the following tree that is printed as in order traversal:

6, 3, 4, 5, 2

We compare each node with its next one and we can find out that 6 is the firstNode element to swap because 6 > 3 and 2 is the secondNode element to swap because 2 < 5.
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

    public void recoverTreeRecurr(TreeNode root) {
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

    //The Space O(1) using Morris Traversal

    public void recoverTree(TreeNode root) {
        TreeNode preNode = null;
        TreeNode firstNode = null, secondNode = null;
        // Morris Traversal
        TreeNode curr = null;
        while (root != null) {
            if (root.left != null) {
                // connect threading for root
                curr = root.left;
                while (curr.right != null && curr.right != root)
                    curr = curr.right;
                // the threading already exists
                if (curr.right != null) {
                    if (preNode != null && preNode.val > root.val) {
                        if (firstNode == null) {
                            firstNode = preNode;
                            secondNode = root;
                        } else {
                            secondNode = root;
                        }
                    }
                    preNode = root;

                    curr.right = null;
                    root = root.right;
                } else {
                    // construct the threading
                    curr.right = root;
                    root = root.left;
                }
            } else {
                if (preNode != null && preNode.val > root.val) {
                    if (firstNode == null) {
                        firstNode = preNode;
                        secondNode = root;
                    } else {
                        secondNode = root;
                    }
                }
                preNode = root;
                root = root.right;
            }
        }
        // swap two node values;
        if (firstNode != null && secondNode != null) {
            int tmp = firstNode.val;
            firstNode.val = secondNode.val;
            secondNode.val = tmp;
        }
    }

    public void morrisTraversal(TreeNode root) {
        TreeNode curr = null;
        while (root != null) {
            if (root.left != null) {
                // connect threading for root
                curr = root.left;
                while (curr.right != null && curr.right != root)
                    curr = curr.right;
                // the threading already exists
                if (curr.right != null) {
                    curr.right = null;
                    System.out.println(root.val);
                    root = root.right;
                } else {
                    // construct the threading
                    curr.right = root;
                    root = root.left;
                }
            } else {
                System.out.println(root.val);
                root = root.right;
            }
        }
    }
}
