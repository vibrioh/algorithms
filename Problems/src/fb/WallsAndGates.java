package fb;

import java.util.LinkedList;
import java.util.Queue;

public class WallsAndGates {
    /*
    The key is start from 0, all 0 in q, then all 1 in q, then 2 in q, then all INF become distances, because it's form 0 to n, level by level, so it is the shortest
     */
    public void wallsAndGates(int[][] rooms) {
        if (rooms == null || rooms.length == 0) {
            return;
        }
        int m = rooms.length;
        int n = rooms[0].length;
        int[] dr = new int[]{-1, 1, 0, 0};
        int[] dc = new int[]{0, 0, -1, 1};
        Queue<int[]> q = new LinkedList<>();
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (rooms[r][c] == 0) {
                    q.offer(new int[]{r, c});
                }
            }
        }
        while (!q.isEmpty()) {
            int[] cor = q.poll();
            for (int i = 0; i < 4; i++) {
                int nr = cor[0] + dr[i];
                int nc = cor[1] + dc[i];
                if (nr < 0 || nr >= m || nc < 0 || nc >= n || rooms[nr][nc] != Integer.MAX_VALUE) {
                    continue;
                }
                rooms[nr][nc] = rooms[cor[0]][cor[1]] + 1;
                q.offer(new int[]{nr, nc});
            }
        }
        return;
    }
}
