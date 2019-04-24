package fb;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreeVerticalOrderTraverse {
    public List<List<Integer>> verticalOrder(TreeNode root) {
        TreeNode head = root;
        int l = 0;
        int r = 0;

        // Can not use this method to find the width of the Tree！！！！！！！
        while (head != null) {
            l++;
            head = head.left == null ? head.right : head.left;
        }
        head = root;
        while (head != null) {
            r++;
            head = head.right == null ? head.left : head.right;
        }
        int len = l + r - 1;



        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            res.add(new ArrayList<Integer>());
        }
        traverse(root, l - 1, res);
        return res;
    }

    private void traverse(TreeNode root, int idx, List<List<Integer>> res) {
        if (root == null) {
            return;
        }
        res.get(idx).add(root.val);
        traverse(root.left, idx - 1, res);
        traverse(root.right, idx + 1, res);
    }

    class TreeNode {
        TreeNode left;
        TreeNode right;
        int val;
    }
}
