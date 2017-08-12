package jiuzhang.binarysearch;

/**
 * Binary Search (last mid <= result); key is int(2^31 - 1), long(2^64 - 1);
 * Implement int sqrt(int x).

 Compute and return the square root of x.
 */

public class SqrtX {
  public int mySqrt(int x) {
    long start = 0;
    long end = x;

    while (start + 1 < end) {
      long mid = start + (end - start) / 2;
      if (mid * mid == x) {
        return (int) mid;
      } else if ( mid * mid < x) {
        start = mid;
      } else {
        end = mid;
      }
    }

    if (end * end <= x) {
      return (int) end;
    }
    return (int) start;
  }
}
