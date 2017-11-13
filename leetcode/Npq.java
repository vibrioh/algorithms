package leetcode;

import java.util.Scanner;

public class Npq {


  private static boolean isDivisible(int N, int p, int q) {
    return (N % p == 0 || N % q == 0);
  }

  private static boolean isContains(int N, int p, int q) {
    while (N > 0) {
      int n = N % 10;
      if (n == p || n == q) {
        return true;
      }
      N = N / 10;
    }
    return false;
  }

  public static void npq(int N, int p, int q) {
    while (N > 0) {
      if (isDivisible(N, p, q) && isContains(N, p, q)) {
        System.out.print("OUTTHINK");
      } else if (isDivisible(N, p, q)) {
        System.out.print("OUT");
      } else if (isContains(N, p, q)) {
        System.out.print("THINK");
      } else {
        System.out.print(N);
      }
      if (N > 1) {
        System.out.print(",");
      }
      N--;
    }
  }

  public static void main(String[] args) {
    Scanner scanner =new Scanner(System.in);
    String line =scanner.nextLine();
    String[] params = line.split(" ");
    int N = Integer.parseInt(params[0]);
    int p= Integer.parseInt(params[1]);
    int q=Integer.parseInt(params[2]);

    npq(N, p, q);
  }
}
