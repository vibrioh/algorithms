package cc189.c1c2;

/**
 * Rotate Matrix: Given an image represented by an NxN matrix, where each pixel in the image is 4
 bytes, write a method to rotate the image by 90 degrees. Can you do this in place?
 * the key is layer by layer, pixel by pixel.
 */
public class RotateMatrix {

  public static int[][] rotateMatrixInplace(int[][] mtx) {
    int n = mtx.length;
    if (n <= 1) {
      return mtx;
    } else {
      for (int j = 1; j <= n/2; j++) {
        for (int i = j; i <= n - j; i++) {
          int temp = mtx[i - 1][j - 1];
//          System.out.printf("temp  <--  [%2d]\n", temp);
          mtx[i - 1][j - 1] = mtx[j - 1][n - i];
//          System.out.printf("mtx[%d][%d]: [%2d]  <--  [%2d]\n", i - 1, j - 1, temp, mtx[j - 1][n - i]);
          mtx[j - 1][n - i] = mtx[n - i][n - j];
//          System.out.printf("mtx[%d][%d]: [%2d]  <--  [%2d]\n", j - 1, n - i, mtx[i - 1][j - 1], mtx[n - i][n - j]);
          mtx[n - i][n - j] = mtx[n - j][i - 1];
//          System.out.printf("mtx[%d][%d]: [%2d]  <--  [%2d]\n", n - i, n - j, mtx[j - 1][n - i], mtx[n - j][i - 1]);
          mtx[n - j][i - 1] = temp;
//          System.out.printf("mtx[%d][%d]: [%2d]  <--  [%2d]\n", n - j, i - 1, mtx[n - j][n - j], temp);
        }
//        System.out.printf("j = [%2d]\n\n", j);
      }
    }
    return mtx;
  }

  public static boolean rotate(int[][] matrix) {
    if (matrix.length == 0 || matrix.length != matrix[0].length) return false; // Not a square
    int n = matrix.length;

    for (int layer = 0; layer < n / 2; layer++) {
      int first = layer;
      int last = n - 1 - layer;
      for(int i = first; i < last; i++) {
        int offset = i - first;
        int top = matrix[first][i]; // save top

        // left -> top
        matrix[first][i] = matrix[last-offset][first];

        // bottom -> left
        matrix[last-offset][first] = matrix[last][last - offset];

        // right -> bottom
        matrix[last][last - offset] = matrix[i][last];

        // top -> right
        matrix[i][last] = top; // right <- saved top
      }
    }
    return true;
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

    rotateMatrixInplace(mtx);

    int[][] mtxOrigin = new int[N][N];
    int count2 = 0;
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        mtxOrigin[i][j] = ++count2;
      }
    }

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

    rotate(mtx);

    System.out.println("--------------------------------------------");

    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        System.out.printf("[%2d]", mtx[i][j]);
      }
      System.out.println();
    }


  }

}
