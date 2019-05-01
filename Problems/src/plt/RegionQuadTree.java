package plt;

public class RegionQuadTree {
    // Definition for a RegionQuadTree node.
    class Node {
        public boolean val;
        public boolean isLeaf;
        public Node topLeft;
        public Node topRight;
        public Node bottomLeft;
        public Node bottomRight;

        public Node() {
        }

        public Node(boolean _val, boolean _isLeaf, Node _topLeft, Node _topRight, Node _bottomLeft, Node _bottomRight) {
            val = _val;
            isLeaf = _isLeaf;
            topLeft = _topLeft;
            topRight = _topRight;
            bottomLeft = _bottomLeft;
            bottomRight = _bottomRight;
        }
    }

    public Node construct(int[][] grid) {
        return helper(grid, 0, 0, grid.length);
    }

    private Node helper(int[][] grid, int r, int c, int len) {
        if (len == 1) {
            // end leaf node
            return new Node(grid[r][c] == 1, true, null, null, null, null);
        }
        Node result = new Node();
        Node topLeft = helper(grid, r, c, len / 2);
        Node topRight = helper(grid, r, c + len / 2, len / 2);
        Node bottomLeft = helper(grid, r + len / 2, c, len / 2);
        Node bottomRight = helper(grid, r + len / 2, c + len / 2, len / 2);
        // prune all same children，a leaf node too!
        if (topLeft.isLeaf && topRight.isLeaf && bottomLeft.isLeaf && bottomRight.isLeaf
                && topLeft.val == topRight.val && topRight.val == bottomLeft.val && bottomLeft.val == bottomRight.val) {
            result.isLeaf = true;
            result.val = topLeft.val;
        } else {
            result.topLeft = topLeft;
            result.topRight = topRight;
            result.bottomLeft = bottomLeft;
            result.bottomRight = bottomRight;
        }
        return result;
    }

    // quadTree intersection function
    public Node intersect(Node quadTree1, Node quadTree2) {
        // Terminal condition, when any of two is leaf.
        if (quadTree1.isLeaf) {
            return quadTree1.val ? quadTree1 : quadTree2;
        } else if (quadTree2.isLeaf) {
            return quadTree2.val ? quadTree2 : quadTree1;
        }
        // Recurse in all 4 directions.
        Node tl = intersect(quadTree1.topLeft, quadTree2.topLeft);
        Node tr = intersect(quadTree1.topRight, quadTree2.topRight);
        Node bl = intersect(quadTree1.bottomLeft, quadTree2.bottomLeft);
        Node br = intersect(quadTree1.bottomRight, quadTree2.bottomRight);
        // Detect a merger state when all 4 leaves have same value.
        if (tl.isLeaf && tr.isLeaf && bl.isLeaf && br.isLeaf && tl.val == tr.val && bl.val == br.val && tl.val == bl.val) {
            return new Node(tl.val, true, null, null, null, null);
        } else {
            return new Node(false, false, tl, tr, bl, br);
        }
    }
}
