package leetcode;

import jiuzhang.binarytree.TreeNode;

/**
 * Created by vibrioh on 10/28/17.
 */
public class MaximumBinaryTree {
    public TreeNode constructMaximumBinaryTree(int[] nums) {

        if (nums.length == 0) {
            return null;
        }

        int max_num = -Integer.MAX_VALUE;
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > max_num) {
                max_num = nums[i];
                index = i;
            }
        }

        int[] nums_left = new int[index];
        int[] nums_right = new int[nums.length - index - 1];

        for (int i = 0; i < index; i++) {
            nums_left[i] = nums[i];
        }

        for (int i = 0; i < nums.length - index - 1; i++) {
            nums_right[i] = nums[index + 1 + i];
        }

        TreeNode root = new TreeNode(max_num);

        TreeNode left_node = constructMaximumBinaryTree(nums_left);
        TreeNode right_node = constructMaximumBinaryTree(nums_right);

        root.left = left_node;
        root.right = right_node;

        return root;
    }
}
