package plt;

import java.util.LinkedList;
import java.util.Queue;

// Time M X N  Space M X N
public class NumberOfIslands {
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        int[] dr = new int[]{0, 0, -1, 1};
        int[] dc = new int[]{-1, 1, 0, 0};
        int res = 0;
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (!visited[r][c] && grid[r][c] == '1') {
                    // System.out.println(r + " " + c);
                    res++;
                    Queue<int[]> q = new LinkedList<>();
                    q.offer(new int[]{r, c});
                    while (!q.isEmpty()) {
                        int[] curr = q.poll();
                        for (int i = 0; i < 4; i++) {
                            int nr = curr[0] + dr[i];
                            int nc = curr[1] + dc[i];
                            if (nr >= 0 && nr < m && nc >= 0 && nc < n && !visited[nr][nc] && grid[nr][nc] == '1') {
                                q.offer(new int[]{nr, nc});
                                visited[nr][nc] = true;
                            }
                        }
                    }
                }
            }
        }
        return res;
    }
}
