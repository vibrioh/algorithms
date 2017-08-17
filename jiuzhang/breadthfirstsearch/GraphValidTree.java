package jiuzhang.breadthfirstsearch;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class GraphValidTree {

  /**
   * 1. V - 1 = E (if)
   * 2. all connected (bfs)
   *
   * @param n an integer
   * @param edges a list of undirected edges
   * @return true if it's a valid tree, or false
   */
  public boolean validTree(int n, int[][] edges) {
    // Write your code here
    if (n != edges.length + 1) {
      return false;
    }

    Map<Integer, Set<Integer>> graph = initializeGraph(n, edges);

    Queue<Integer> queue = new LinkedList<>();
    Set<Integer> visited = new HashSet<>();

    queue.offer(0);
    visited.add(0);

    while (!queue.isEmpty()) {
      int k = queue.poll();
      for (Integer v : graph.get(k)) {
        if (!visited.contains(v)) {
          queue.offer(v);
          visited.add(v);
        }
      }
    }

    return visited.size() == n;
  }

  private Map<Integer, Set<Integer>> initializeGraph(int n, int[][] edges) {
    Map<Integer, Set<Integer>> graph = new HashMap<>();

    for (int i = 0; i < n; i++) {
      graph.put(i, new HashSet<Integer>());
    }

    for (int[] edge : edges) {
      graph.get(edge[0]).add(edge[1]);
      graph.get(edge[1]).add(edge[0]);
    }

    return graph;
  }
}