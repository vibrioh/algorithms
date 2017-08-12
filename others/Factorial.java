package others;

public class Factorial {

  public int fact(int n) {
    if (n == 1) {
      return 1;
    } else {
      return fact(n - 1);
    }
  }

  public static void main(String[] args) {

  }
}
