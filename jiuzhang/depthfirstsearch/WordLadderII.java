package jiuzhang.depthfirstsearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class WordLadderII {
  /**
   * @param start, a string
   * @param end, a string
   * @param dict, a set of string
   * @return a list of lists of string
   */
  public List<List<String>> findLadders(String start, String end, Set<String> dict) {
    List<List<String>> result = new ArrayList<>();

    dict.add(start);
    dict.add(end);

    Map<String, List<String>> graph = constructGraph(dict);

    Map<String, Integer> distance = bfsHelper(end, graph);

    dfsHelper(start, graph, distance, new ArrayList<String>(), result);

    return result;
  }

  private Map<String, List<String>> constructGraph(Set<String> dict) {
    Map<String, List<String>> graph = new HashMap<>();

    for (String s : dict) {
      ArrayList<String> neighbors = new ArrayList<>();
      for (int i = 0; i < s.length(); i++) {
        for (char c = 'a'; c <= 'z'; c++) {
          if (c != s.charAt(i)) {
            String neighbor = s.substring(0, i) + c + s.substring(i + 1);
            if (dict.contains(neighbor)) {
              neighbors.add(neighbor);
            }
          }
        }
      }
      graph.put(s, neighbors);
    }
    return graph;
  }

  private Map<String, Integer> bfsHelper(String end, Map<String, List<String>> graph) {
    Map<String, Integer> distance = new HashMap<>();

    Queue<String> queue = new LinkedList<>();
    Set<String> visited = new HashSet<>();

    int d = 0;

    queue.offer(end);
    visited.add(end);

    while(!queue.isEmpty()) {
      int level = queue.size();
      for (int i = 0; i < level; i++) {
        String str = queue.poll();
        distance.put(str, d);
        for (String neighbor : graph.get(str)) {
          if (visited.contains(neighbor)) {
            continue;
          }
          queue.offer(neighbor);
          visited.add(neighbor);
        }
      }
      d++;
    }
    return distance;
  }

  private void dfsHelper(String start, Map<String, List<String>> graph, Map<String, Integer> distance, List<String> path, List<List<String>> result) {
    path.add(start);

    if (distance.get(start) == 0) {
      result.add(new ArrayList<String>(path));
      return;
    }

    for (String neighbor : graph.get(start)) {
      if (distance.get(neighbor) < distance.get(start)) {

        dfsHelper(neighbor, graph, distance, path, result);
        path.remove(path.size() - 1);
      }
    }
  }

  public static void main(String[] args) {
    Set<String> input = new HashSet<>();
    String start = "hit";
    String end = "cog";
    input.add("hot");
    input.add("dot");
    input.add("lot");
    input.add("dog");
    input.add("log");

    WordLadderII t = new WordLadderII();
    List<List<String>> s = t.findLadders(start, end, input);

    System.out.println(s.toString());
  }
}
