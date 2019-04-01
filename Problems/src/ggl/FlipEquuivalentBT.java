package ggl;


class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int val) {
        this.val = val;
    }
}

/*
Intuition

If root1 and root2 have the same root value, then we only need to check if their children are equal (up to ordering.)

Algorithm

There are 3 cases:

If root1 or root2 is null, then they are equivalent if and only if they are both null.

Else, if root1 and root2 have different values, they aren't equivalent.

Else, let's check whether the children of root1 are equivalent to the children of root2. There are two different ways to pair these children.


 */

public class FlipEquuivalentBT {
    class Solution {
        public boolean flipEquiv(TreeNode root1, TreeNode root2) {
            if (root1 == root2)
                return true;
            if (root1 == null || root2 == null || root1.val != root2.val)
                return false;

            return (flipEquiv(root1.left, root2.left) && flipEquiv(root1.right, root2.right) ||
                    flipEquiv(root1.left, root2.right) && flipEquiv(root1.right, root2.left));
        }
    }

}
