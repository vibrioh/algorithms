package fb;


import java.util.*;

public class CloneGraph {
    // key is the HashMap of old node to new node

    //DFS seems better
    public Node cloneGraph(Node node) {
        if (node == null) {
            return node;
        }
        Map<Node, Node> mp = new HashMap<>();
        return dfsClone(node, mp);
    }

    private Node dfsClone(Node node, Map<Node, Node> mp) {
        if (mp.containsKey(node)) {
            return mp.get(node);
        }
        mp.put(node, new Node(node.val, new ArrayList<>()));
        for (Node nei : node.neighbors) {
            Node neiCopy = dfsClone(nei, mp);
            mp.get(node).neighbors.add(neiCopy);
        }
        return mp.get(node);
    }


    class Node {
        int val;
        List<Node> neighbors;

        Node(int val, List<Node> neighbors) {
            this.val = val;
            this.neighbors = neighbors;
        }
    }

    // BFS
    public Node cloneGraphBFS(Node node) {
        if (node == null) {
            return node;
        }
        Map<Node, Node> mp = new HashMap<>();
        Queue<Node> q = new LinkedList<>();
        mp.put(node, new Node(node.val, new ArrayList<Node>()));
        q.offer(node);
        while (!q.isEmpty()) {
            Node curr = q.poll();
            for (Node nei : curr.neighbors) {
                if (!mp.containsKey(nei)) {
                    mp.put(nei, new Node(nei.val, new ArrayList<Node>()));
                    q.offer(nei);
                }
                mp.get(curr).neighbors.add(mp.get(nei));
            }
        }
        return mp.get(node);
    }
}
