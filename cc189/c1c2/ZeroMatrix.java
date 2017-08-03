package cc189.c1c2;

import java.util.Set;
import java.util.HashSet;

/**
 * Zero Matrix: Write an algorithm such that if an element in an MxN matrix is 0, its entire row and
 column are set to 0.
 * my own HashSet solution
 * the key is we only need to get all rows and columns to be zeroed out
 */

public class ZeroMatrix {
  public static void zeroMatrixHashSet(int[][] mtx) {
    Set<Integer> row = new HashSet<>();
    Set<Integer> col = new HashSet<>();
    for (int r = 0; r < mtx.length; r++) {
      for (int c = 0; c < mtx[r].length; c++) {
        if (mtx[r][c] == 0) {
          row.add(r);
          col.add(c);
        }
      }
    }
    for (int r : row) {
      for (int c = 0; c < mtx[r].length; c++) {
        mtx[r][c] = 0;
      }
    }
    for (int c : col) {
      for (int r = 0; r < mtx.length; r++) {
        mtx[r][c] = 0;
      }
    }
  }

  public static void main(String[] args) {
    final int N = 9;
    int count = 0;
    int[][] mtx = new int[N][N];
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        mtx[i][j] = ++count;
      }
    }

    mtx[3][4] = 0;
    mtx[8][5] = 0;
    mtx[7][4] = 0;

    zeroMatrixHashSet(mtx);

    int[][] mtxOrigin = new int[N][N];
    int count2 = 0;
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        mtxOrigin[i][j] = ++count2;
      }
    }

    mtxOrigin[3][4] = 0;
    mtxOrigin[8][5] = 0;
    mtxOrigin[7][4] = 0;

    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        System.out.printf("[%2d]", mtxOrigin[i][j]);
      }
      System.out.println();
    }

    System.out.println("--------------------------------------------");

    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        System.out.printf("[%2d]", mtx[i][j]);
      }
      System.out.println();
    }
  }
}
