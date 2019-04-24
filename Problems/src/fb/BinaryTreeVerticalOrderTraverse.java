package fb;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class BinaryTreeVerticalOrderTraverse {
    // DFS seems not working because of the order to add into the same list of vertical level
    public List<List<Integer>> verticalOrder(TreeNode root) {
        Map<Integer, List<Integer>> mp = new TreeMap<>();
        traverse(root, 0, mp);
        return new ArrayList<>(mp.values());
    }

    private void traverse(TreeNode root, int idx, Map<Integer, List<Integer>> mp) {
        if (root == null) {
            return;
        }
        mp.putIfAbsent(idx, new ArrayList<Integer>());
        mp.get(idx).add(root.val);
        traverse(root.left, idx - 1, mp);
        traverse(root.right, idx + 1, mp);
    }

    class TreeNode {
        TreeNode left;
        TreeNode right;
        int val;
    }
}
