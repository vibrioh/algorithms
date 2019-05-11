package amz;

import java.util.ArrayList;
import java.util.List;
/*
divide the problem into small parts,  do before recursion is order, recursion before do is reverse order!!!!!
 */

public class BoundaryOfBinaryTree {
    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode(int x) { val = x; }
     * }
     */
    class Solution {
        List<Integer> res = new ArrayList<>();

        public List<Integer> boundaryOfBinaryTree(TreeNode root) {
            if (root == null) {
                return res;
            }
            res.add(root.val);
            leftBoundary(root.left);
            leaves(root.left);
            leaves(root.right);
            rightBoundary(root.right);
            return res;
        }

        private void leftBoundary(TreeNode root) {
            if (root == null || (root.left == null && root.right == null)) {
                return;
            }
            //  先做再递归，是顺着往下走，先遇到先做，最后的最后做
            res.add(root.val);
            if (root.left == null) {
                leftBoundary(root.right);
            } else {
                leftBoundary(root.left);
            }
        }

        private void rightBoundary(TreeNode root) {
            if (root == null || (root.left == null && root.right == null)) {
                return;
            }
            if (root.right == null) {
                rightBoundary(root.left);
            } else {
                rightBoundary(root.right);
            }
            // 先递归再做，是从底部往回加入，最后一个先做，先遇到后做
            res.add(root.val);
        }

        private void leaves(TreeNode root) {
            if (root == null) {
                return;
            }
            if (root.left == null && root.right == null) {
                res.add(root.val);
            }
            leaves(root.left);
            leaves(root.right);
        }

        class TreeNode {
            TreeNode left;
            TreeNode right;
            int val;
        }
    }
}
