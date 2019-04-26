package fb;


public class ValidateBinarySearchTree {
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isValidBSTdfs(root, null, null);

    }

    private boolean isValidBSTdfs(TreeNode root, Integer min, Integer max) {
        if (root == null) {
            return true;
        }
        if (min != null) {
            if (root.val <= min) {
                return false;
            }
        }
        if (max != null) {
            if (root.val >= max) {
                return false;
            }
        }
        return isValidBSTdfs(root.left, min, root.val) && isValidBSTdfs(root.right, root.val, max);
    }

    class TreeNode {
        TreeNode left;
        TreeNode right;
        int val;
    }
}
