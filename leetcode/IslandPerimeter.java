package leetcode;

public class IslandPerimeter {
  class Solution {
    public int islandPerimeter(int[][] grid) {
      int res = 0;
      if (grid == null) {
        return res;
      }

      int[][] adjacent = new int[grid.length + 1][grid[0].length + 1];

      for (int r = 0; r < grid.length; r++) {
        for (int c = 0; c < grid[r].length; c++) {
          if (grid[r][c] == 1) {
            res += (4 + adjacent[r][c]);
            adjacent[r][c + 1] -= 2;
            adjacent[r + 1][c] -= 2;
          }
        }
      }

      return res;
    }
  }

  public static void main(String[] args) {
    int[][] test1 = {{1, 0}};
//    IslandPerimeter island = new IslandPerimeter();
//    int res1 = island.islandPerimeter(test1);
    System.out.println(test1[0][0] + " " + test1[1][0]);
//    System.out.println(res1);
  }
}
