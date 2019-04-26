package fb;

import java.util.LinkedList;
import java.util.Queue;

public class WallsAndGates {
    public void wallsAndGates(int[][] rooms) {
        if (rooms == null || rooms.length == 0) {
            return;
        }
        int m = rooms.length;
        int n = rooms[0].length;
        int[] dr = new int[]{-1, 1, 0, 0};
        int[] dc = new int[]{0, 0, -1, 1};
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (rooms[r][c] != 0) {
                    continue;
                }
                Queue<int[]> q = new LinkedList<>();
                boolean[][] visited = new boolean[m][n];
                q.offer(new int[]{r, c});
                visited[r][c] = true;
                int level = 0;
                while(!q.isEmpty()) {
                    level++;
                    int s = q.size();
                    while (s > 0) {
                        int[] cor = q.poll();
                        s--;
                        int rcurr = cor[0];
                        int ccurr = cor[1];
                        // System.out.println(rcurr + " " +  ccurr);
                        for (int i = 0; i < 4; i++) {
                            int rn = rcurr + dr[i];
                            int cn = ccurr + dc[i];
                            if (rn < 0 || rn >= m || cn < 0 || cn >= n || visited[rn][cn] || rooms[rn][cn] == -1 || rooms[rn][cn] == 0) {
                                continue;
                            }
                            q.offer(new int[]{rn, cn});
                            visited[rn][cn] = true;
                            if (rooms[rn][cn] == Integer.MAX_VALUE) {
                                // System.out.println(rn + " " + cn + "rooms[rn][cn]" + " " + level);
                                rooms[rn][cn] = level;
                                continue;
                            }
                            // System.out.println("old" + rooms[rn][cn]);
                            rooms[rn][cn] = Math.min(level, rooms[rn][cn]);
                            // System.out.println(rooms[rn][cn]);
                        }
                    }

                }
            }
        }
        return;
    }
}
