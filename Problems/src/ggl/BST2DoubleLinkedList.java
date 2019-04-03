package ggl;

import org.w3c.dom.Node;

/*
step1: inorder tranversal by recursion to connect the original BST
step2: connect the head and tail to make it circular

Tips: Using dummy node to handle corner case

开始dummy当prev留住head，最后prev是tail。其中prev可当做class变量
 */


public class BST2DoubleLinkedList {
    class Node {
        public int val;
        public Node left;
        public Node right;

        public Node() {
        }

        public Node(int _val, Node _left, Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    }

    // actually the end of the List
    Node prev = null;
    public Node treeToDoublyList(Node root) {
        if (root == null) return null;
        // start of List
        Node dummy = new Node(0, null, null);
        // end of List
        prev = dummy;
        helper(root);
        //connect head and tail
        prev.right = dummy.right;
        dummy.right.left = prev;
        return dummy.right;
    }

    private void helper (Node cur) {
        if (cur == null) return;
        helper(cur.left);
        prev.right = cur;
        cur.left = prev;
        prev = cur;
        helper(cur.right);
    }
}
