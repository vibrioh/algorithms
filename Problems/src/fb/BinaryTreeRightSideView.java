package fb;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTreeRightSideView {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int num = q.size();
            while (num > 0) {
                TreeNode curr = q.poll();
                if (num == 1) {
                    res.add(curr.val);
                }
                if (curr.left != null) {
                    q.offer(curr.left);
                }
                if (curr.right != null) {
                    q.offer(curr.right);
                }
                num--;
            }
        }
        return res;
    }

    class TreeNode {
        TreeNode left;
        TreeNode right;
        int val;
    }

}
