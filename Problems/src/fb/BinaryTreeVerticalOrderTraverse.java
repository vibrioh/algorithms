package fb;

import java.util.*;

public class BinaryTreeVerticalOrderTraverse {
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        List<Integer> start = new ArrayList<>();
        start.add(root.val);
        Map<TreeNode, List<Integer>> mp = new HashMap<>();
        mp.put(root, start);
        res.add(start);
        while (!q.isEmpty()) {
            TreeNode node = q.poll();
            System.out.println(node.val);
            List list = mp.get(node);
            int idx = res.indexOf(list);
            int len = res.size();

            if (node.left != null) {
                if (idx == 0) {
                    List<Integer> tail = new ArrayList<>();
                    tail.add(node.left.val);
                    mp.put(node.left, tail);
                    res.add(0, tail);
                } else {
                    System.out.println(idx);
                    res.get(idx - 1).add(node.left.val);
                    mp.put(node.left, res.get(idx - 1));
                }
                q.offer(node.left);
            }
            if (node.right != null) {
                if (idx == len - 1) {
                    List<Integer> head = new ArrayList<>();
                    head.add(node.right.val);
                    mp.put(node.right, head);
                    res.add(head);
                } else {
                    res.get(res.indexOf(list) + 1).add(node.right.val);
                    mp.put(node.right, res.get(res.indexOf(list) + 1));
                }
                q.offer(node.right);
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
