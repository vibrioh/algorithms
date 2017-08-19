package jiuzhang.breadthfirstsearch;

import java.util.LinkedList;
import java.util.Queue;

public class NumberIslands {


  class Coordinate{
    public int m;
    public int n;
    public Coordinate(int m, int n) {
      this.m = m;
      this.n = n;
    }
  }

  /**
   * @param grid a boolean 2D matrix
   * @return an integer
   */
  public int numIslands(boolean[][] grid) {
    if (grid == null || grid.length == 0 || grid[0].length == 0) {
      return 0;
    }

    int count = 0;

    for (int m = 0; m < grid.length; m++) {
      for (int n = 0; n < grid[m].length; n++) {
        if (grid[m][n]) {
          count++;
          bfsMark(m, n, grid);    // grid needed to be passed
        }
      }
    }

    return count;
  }

  public void bfsMark(int m, int n, boolean[][] grid) {
    Coordinate bank = new Coordinate(m, n);
    Queue<Coordinate> queue = new LinkedList<>();
    queue.offer(bank);
    grid[m][n] = false;

    int[] deltaM = {0, 1, -1, 0};    // four directions, four pairs
    int[] deltaN = {1, 0, 0, -1};

    while (!queue.isEmpty()) {
      Coordinate coor = queue.poll();
      for (int i = 0; i < 4; i++) {    // four directions, one loop for 4 indices
        int newM = coor.m + deltaM[i];
        int newN = coor.n + deltaN[i];
        Coordinate newCoor = new Coordinate(newM, newN);
        if (inBound(newCoor, grid) && grid[newM][newN]) {    // grid needed to be passed
          queue.offer(newCoor);
          grid[newM][newN] = false;
        }

      }
    }
  }

  public boolean inBound(Coordinate coor, boolean[][] grid) {
    // index can be 0, so [[[[[ >=0 ]]]]]
    return (coor.m >= 0 && coor.m < grid.length && coor.n >= 0 && coor.n < grid[0].length);
  }
}
