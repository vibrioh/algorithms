package jiuzhang.breadthfirstsearch;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class TopologicalSorting {

  class DirectedGraphNode {
    int label;
    ArrayList<DirectedGraphNode> neighbors;
    DirectedGraphNode(int x) { label = x; neighbors = new ArrayList<DirectedGraphNode>(); }
  }

  /**
   * @param graph: A list of Directed graph node
   * @return Any topological order for the given graph.
   */
  public ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
    ArrayList<DirectedGraphNode> sorted = new ArrayList<>();

    if (graph == null) {
      return sorted;
    }

    Map<DirectedGraphNode, Integer> indegree = getIndegree(graph);

    Queue<DirectedGraphNode> queue = new LinkedList<>();

    for (DirectedGraphNode node : graph) {
      if (indegree.get(node) == 0) {
        queue.offer(node);
        sorted.add(node);
      }
    }

    while (!queue.isEmpty()) {
      DirectedGraphNode node = queue.poll();
      for (DirectedGraphNode neighbor : node.neighbors) {
        indegree.put(neighbor, indegree.get(neighbor) - 1);    // indegree.get(neighbor) is an Integer, an object, not a variable, can not --
        if (indegree.get(neighbor) == 0) {
          queue.offer(neighbor);
          sorted.add(neighbor);
        }
      }
    }

    return sorted;
  }

  private Map<DirectedGraphNode, Integer> getIndegree(ArrayList<DirectedGraphNode> graph) {
    Map<DirectedGraphNode, Integer> indegree = new HashMap<>();

    for (DirectedGraphNode node : graph) {
      indegree.put(node, 0);
    }

    for (DirectedGraphNode node : graph) {
      for (DirectedGraphNode neighbor : node.neighbors) {
        indegree.put(neighbor, indegree.get(neighbor) + 1);
      }
    }

    return indegree;
  }
}
