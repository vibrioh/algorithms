package plt;

public class LongestIncresingPathInAMatrix {
    int[] dr = new int[]{0, 0, -1, 1};
    int[] dc = new int[]{-1, 1, 0, 0};

    public int longFromOrigin(int[][] matrix) {
        return dfs(matrix, 0, 0, 0);
    }

    private int dfs(int[][] matrix, int r, int c, int depth) {
        System.out.println("r: " + r + "| c: " + c + " val: " + matrix[r][c]);
        int currdepth = depth;
        int m = matrix.length;
        int n = matrix[0].length;
        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if (nr < 0 || nr >= m || nc < 0 || nc >= n || matrix[nr][nc] <= matrix[r][c]) {
                continue;
            }
            currdepth = Math.max(currdepth, dfs(matrix, nr, nc, depth));
        }
        return currdepth + 1;
    }

    public static void main(String[] args) {
        LongestIncresingPathInAMatrix s = new LongestIncresingPathInAMatrix();
        System.out.println(s.longFromOrigin(new int[][]{{3, 4, 5}, {2, 6, 9}, {1, 7, 8}}));
        System.out.println(s.longFromOrigin(new int[][]{{2, 4, 1}, {6, 7, 3}, {8, 5, 4}}));
        System.out.println(s.longFromOrigin(new int[][]{{2, 1, 1}, {2, 7, 3}, {8, 5, 4}}));
    }
}
