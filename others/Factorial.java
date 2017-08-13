package others;

import java.util.Scanner;

public class Factorial {

  public int fact(int n) {

    if (n == 1) {
      System.out.println(n);
      return 1;
    } else {
      System.out.print(n + "*");
      return n * fact(n - 1);
    }
  }

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);

    System.out.println("Enter Digit For factorial:");
    int n = in.nextInt();

    Factorial f = new Factorial();
    System.out.println(f.fact(n));
  }
}
