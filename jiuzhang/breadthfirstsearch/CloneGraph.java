package jiuzhang.breadthfirstsearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

class UndirectedGraphNode {
   int label;
   ArrayList<UndirectedGraphNode> neighbors;
   UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
}


public class CloneGraph {
  /**
   * @param node: A undirected graph node
   * @return A undirected graph node
   */
  public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
    // write your code here
    if (node == null) {
      return node;
    }

    // use bfs algorithm to traverse the graph and get all nodes.
    Set<UndirectedGraphNode> nodes = getNodes(node);

    // copy nodes, store the old->new mapping information in a hash map
    Map<UndirectedGraphNode, UndirectedGraphNode> clone = new HashMap<>();

    for (UndirectedGraphNode v : nodes) {
      clone.put(v, new UndirectedGraphNode(v.label));
    }

    // copy neighbors(edges)
    for (Map.Entry<UndirectedGraphNode,UndirectedGraphNode> entry : clone.entrySet()) {
      UndirectedGraphNode origin = entry.getKey();
      UndirectedGraphNode copy = entry.getValue();

      for (UndirectedGraphNode neighbor : origin.neighbors) {
        copy.neighbors.add(clone.get(neighbor));
      }
    }

    return clone.get(node);
  }

  private Set<UndirectedGraphNode> getNodes(UndirectedGraphNode node) {
    Set<UndirectedGraphNode> nodes = new HashSet<>();
    Queue<UndirectedGraphNode> queue = new LinkedList<>();

    queue.offer(node);
    nodes.add(node);

    while (!queue.isEmpty()) {
      UndirectedGraphNode v = queue.poll();

      for (UndirectedGraphNode neighbor : v.neighbors) {

        if (!nodes.contains(neighbor)) {
          queue.offer(neighbor);
          nodes.add(neighbor);
        }
      }
    }

    return nodes;
  }
}
