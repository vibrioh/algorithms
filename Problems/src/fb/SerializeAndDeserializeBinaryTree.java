package fb;

import javax.swing.tree.TreeNode;
import java.util.LinkedList;
import java.util.Queue;


public class SerializeAndDeserializeBinaryTree {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            TreeNode curr = q.poll();
            if (curr == null) {
                sb.append("N");
            } else {
                sb.append(curr.val);
                q.offer(curr.left);
                q.offer(curr.right);
            }
            sb.append(" ");
        }
        return sb.toString().trim();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        Queue<TreeNode> q = new LinkedList<>();
        String[] nodes = data.split(" ");
        TreeNode root = nodes[0].equals("N") ? null : new TreeNode(Integer.valueOf(nodes[0]));
        if (root == null) {
            return root;
        }
        q.offer(root);
        int idx = 1;
        while (!q.isEmpty()) {
            TreeNode curr = q.poll();
            TreeNode left = nodes[idx].equals("N") ? null : new TreeNode(Integer.valueOf(nodes[idx]));
            idx++;
            TreeNode right = nodes[idx].equals("N") ? null : new TreeNode(Integer.valueOf(nodes[idx]));
            idx++;
            curr.left = left;
            curr.right = right;
            if (left != null) {
                q.offer(left);
            }
            if (right != null) {
                q.offer(right);
            }
        }
        return root;
    }


    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }
}
