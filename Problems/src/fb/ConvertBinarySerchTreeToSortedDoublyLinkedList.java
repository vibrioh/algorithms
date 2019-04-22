package fb;

import org.w3c.dom.Node;

public class ConvertBinarySerchTreeToSortedDoublyLinkedList {
    public Node treeToDoublyList(Node root) {
        if (root == null) {
            return root;
        }
        Node[] minMax = divideConquer(root);
        minMax[0].left = minMax[1];
        minMax[1].right = minMax[0];
        return minMax[0];
    }

    private Node[] divideConquer(Node root) {
        if (root == null) {
            return new Node[]{null, null};
        }
        Node[] left = divideConquer(root.left);
        Node[] right = divideConquer(root.right);
        if (left[1] != null) {
            left[1].right = root;
            root.left = left[1];
        } else {
            left[0] = root;
        }
        if (right[0] != null) {
            right[0].left = root;
            root.right = right[0];
        } else {
            right[1] = root;
        }
        return new Node[]{left[0], right[1]};
    }

    class Node {
        int val;
        Node left;
        Node right;
    }
}
