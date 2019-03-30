package ggl;

import java.util.*;

public class RedundantEdge {
//    Set<Integer> seen = new HashSet();
    int MAX_EDGE_VAL = 1000;

//    public int[] findRedundantEdgeUndirect(int[][] edges) {
//        ArrayList[] graph = new ArrayList[MAX_EDGE_VAL + 1];
//        for (int i = 0; i <= MAX_EDGE_VAL; i++) {
//            graph[i] = new ArrayList();
//        }
//
//        for (int[] edge : edges) {
//            seen.clear();
//            if (!graph[edge[0]].isEmpty() && !graph[edge[1]].isEmpty() &&
//                    dfs(graph, edge[0], edge[1])) {
//                return edge;
//            }
//            graph[edge[0]].add(edge[1]);
//            graph[edge[1]].add(edge[0]);
//        }
//        throw new AssertionError();
//    }
//
//    public boolean dfs(ArrayList<Integer>[] graph, int source, int target) {
//        if (!seen.contains(source)) {
//            seen.add(source);
//            if (source == target) return true;
//            for (int nei : graph[source]) {
//                if (dfs(graph, nei, target)) return true;
//            }
//        }
//        return false;
//    }

    public int[] findRedundantEdgeUndirect2(int[][] edges) {
        DSU dsu = new DSU(MAX_EDGE_VAL + 1);
        for (int[] edge : edges) {
            if (!dsu.union(edge[0], edge[1])) return edge;
        }
        throw new AssertionError();
    }

    public static void main(String[] args) {

    }

    /*
This problem is very similar to "Redundant Connection". But the description on the parent/child relationships is much better clarified.

There are two cases for the tree structure to be invalid.
1) A node having two parents;
   including corner case: e.g. [[4,2],[1,5],[5,2],[5,3],[2,4]]
2) A circle exists
If we can remove exactly 1 edge to achieve the tree structure, a single node can have at most two parents. So my solution works in two steps.

1) Check whether there is a node having two parents.
    If so, store them as candidates A and B, and set the second edge invalid.
2) Perform normal union find.
    If the tree is now valid
           simply return candidate B
    else if candidates not existing
           we find a circle, return current edge;
    else
           remove candidate A instead of B.
     */

    public int[] findRedundantDirectedConnection(int[][] edges) {
        int[] can1 = {-1, -1};
        int[] can2 = {-1, -1};
        int[] parent = new int[edges.length + 1];
        // step 1, check whether there is a node with two parents
        for (int[] edge : edges) {
            if (parent[edge[1]] == 0) {
                parent[edge[1]] = edge[0];
            } else {
                can2 = new int[]{edge[0], edge[1]};
                can1 = new int[]{parent[edge[1]], edge[1]};
                edge[1] = 0;
            }
        }

        // step 2, union find
        DSU dsu = new DSU(MAX_EDGE_VAL + 1);
        for (int[] edge : edges) {
            if (edge[1] == 0) {
                continue;
            }
            if ((!dsu.union(edge[0], edge[1]))) {
                if (can1[0] == -1) {
                    return edge;
                }
                return can1;
            }
        }
        return can2;
    }

}

class DSU {
    int[] parent;
    int[] rank;

    public DSU(int size) {
        parent = new int[size];
        for (int i = 0; i < size; i++) parent[i] = i;
        rank = new int[size];
    }

    public int find(int x) {
        if (parent[x] != x) parent[x] = find(parent[x]);
        return parent[x];
    }

    public boolean union(int x, int y) {
        int xr = find(x), yr = find(y);
        if (xr == yr) {
            return false;
        } else if (rank[xr] < rank[yr]) {
            parent[xr] = yr;
        } else if (rank[xr] > rank[yr]) {
            parent[yr] = xr;
        } else {
            parent[yr] = xr;
            rank[xr]++;
        }
        return true;
    }
}

class OrbitResult {
    int node;
    Set<Integer> seen;

    OrbitResult(int n, Set<Integer> s) {
        node = n;
        seen = s;
    }
}
