package amz;

import java.util.HashMap;
import java.util.Map;

class LRUCache {

    class Node {
        int key;
        int val;
        Node prev;
        Node next;

        public Node(int key, int val) {
            this.key = key;
            this.val = val;
            this.prev = null;
            this.next = null;
        }
    }

    private int capacity;
    private Map<Integer, Node> mp = new HashMap<>();
    private Node head = new Node(-1, -1);
    private Node tail = new Node(-1, -1);

    // always add to head side as new
    private void addNode(Node curr) {
        curr.prev = head;
        curr.next = head.next;
        head.next.prev = curr;
        head.next = curr;
    }

    private void removeNode(Node curr) {
        curr.prev.next = curr.next;
        curr.next.prev = curr.prev;
    }

    public LRUCache(int capacity) {
        this.capacity = capacity;
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if (!mp.containsKey(key)) {
            return -1;
        }
        Node curr = mp.get(key);
        removeNode(curr);
        addNode(curr);
        return curr.val;
    }

    public void put(int key, int value) {
        if (get(key) != -1) {
            mp.get(key).val = value;
            return;
        }
        Node curr = new Node(key, value);
        mp.put(key, curr);
        addNode(curr);
        if (mp.size() > capacity) {
            Node last = tail.prev;
            mp.remove(last.key);
            removeNode(last);
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
