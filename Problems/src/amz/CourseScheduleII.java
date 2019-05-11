package amz;

import java.util.*;

public class CourseScheduleII {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<Integer> res = new ArrayList<>();
        int[] indegrees = new int[numCourses];
        // boolean[] visited = new boolean[numCourses];
        Map<Integer, List<Integer>> edges = new HashMap<>();
        for (int[] edge : prerequisites) {
            int to = edge[0];
            int from = edge[1];
            indegrees[to]++;
            edges.putIfAbsent(from, new ArrayList<>());
            edges.get(from).add(to);
        }
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegrees[i] == 0) {
                q.offer(i);
                // visited[i] = true;
            }
        }
        while (!q.isEmpty()) {
            int curr = q.poll();
            res.add(curr);
            if (!edges.containsKey(curr)) {
                continue;
            }
            for (int to : edges.get(curr)) {
                indegrees[to]--;
                if (indegrees[to] == 0) {
                    q.offer(to);
                }
            }
        }
        if (res.size() == numCourses) {
            int[] ans = new int[numCourses];
            for (int i = 0; i < numCourses; i++) {
                ans[i] = res.get(i);
            }
            return ans;
        }
        return new int[0];
    }
}
