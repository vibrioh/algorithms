package fb;

import javax.swing.tree.TreeNode;

public class ValidateBinarySearchTree {
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (root.left != null && root.left.val >= root.val) {
            return false;
        }
        if (root.right != null && root.right.val <= root.val) {
            return false;
        }
        return isValidBSTdfs(root.left, Long.MIN_VALUE, root.val) && isValidBSTdfs(root.right, root.val, Long.MAX_VALUE);

    }

    private boolean isValidBSTdfs(TreeNode root, long min, long max) {
        if (root == null) {
            return true;
        }
        System.out.println(root.val);
        if (root.left != null && (root.left.val >= max || root.left.val <= min || root.left.val >= root.val)) {
            return false;
        }
        if (root.right != null && (root.right.val >= max || root.right.val <= min || root.right.val <= root.val)) {
            return false;
        }
        return isValidBSTdfs(root.left, min, root.val) && isValidBSTdfs(root.right, root.val, max);
    }
}
