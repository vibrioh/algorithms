package fb;

import java.util.LinkedList;
import java.util.Queue;

public class ShortestDistanceFromAllBuildings {
    public int shortestDistance(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return -1;
        }
        int m = grid.length;
        int n = grid[0].length;
        int[][] distances = new int[m][n];
        // need to know if land can reach all buildings
        int[][] buildingsReached = new int[m][n];
        int numBuildings = 0;
        int[] dr = new int[]{0, 0, -1, 1};
        int[] dc = new int[]{-1, 1, 0, 0};
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (grid[r][c] != 1) {
                    continue;
                }
                numBuildings++;
                Queue<int[]> q = new LinkedList<>();
                q.offer(new int[]{r, c});
                // always remember the visited or you will loop for all
                boolean[][] visited = new boolean[m][n];
                visited[r][c] = true;
                int level = 0;
                while (!q.isEmpty()) {
                    int size = q.size();
                    while (size > 0) {
                        size--;
                        int[] cor = q.poll();
                        int qr = cor[0];
                        int qc = cor[1];
                        distances[qr][qc] += level;
                        // System.out.println(qr + " " + qc + " : " + distances[qr][qc]);
                        for (int i = 0; i < 4; i++) {
                            int nr = qr + dr[i];
                            int nc = qc + dc[i];
                            if (nr < 0 || nr >= m || nc < 0 || nc >= n || grid[nr][nc] != 0 || visited[nr][nc]) {
                                continue;
                            }
                            q.offer(new int[]{nr, nc});
                            visited[nr][nc] = true;
                            buildingsReached[nr][nc] += 1;
                        }
                    }
                    level++;
                }
            }
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int d = distances[i][j];
                if (d == 0 || buildingsReached[i][j] < numBuildings) {
                    continue;
                }
                ans = Math.min(ans, d);
            }
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}
