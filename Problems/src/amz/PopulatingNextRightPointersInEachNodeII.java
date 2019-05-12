package amz;

public class PopulatingNextRightPointersInEachNodeII {

    // Definition for a Node.
    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

    ;

    class Solution {
        public Node connect(Node root) {
            // 一层一层的连
            Node prev = null; // next level node iterative
            Node head = null; // head of the next level
            Node curr = root; // current level
            while (curr != null) {
                // iterate curr for connect the next level
                while (curr != null) {
                    if (curr.left != null) {
                        if (prev != null) {
                            prev.next = curr.left;
                        } else {
                            head = curr.left;
                        }
                        prev = curr.left;
                    }
                    if (curr.right != null) {
                        if (prev != null) {
                            prev.next = curr.right;
                        } else {
                            head = curr.right;
                        }
                        prev = curr.right;
                    }
                    curr = curr.next;
                }
                curr = head;
                prev = null;
                head = null;
            }
            return root;
        }
    }
}
