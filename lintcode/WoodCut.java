package lintcode;

/**
 * Given n pieces of wood with length L[i] (integer array).
 * Cut them into small pieces to guarantee you could have equal or more than k pieces with the same length.
 * What is the longest length you can get from the n pieces of wood? Given L & k,
 * return the maximum length of the small pieces.

 Notice:
 You couldn't cut wood into float length.
 If you couldn't get >= k pieces, return 0.
 */

public class WoodCut {
  /**
   * @param L: Given n pieces of wood with length L[i]
   * @param k: An integer
   * @return : The maximum length of the small pieces
   */
  public int woodCut(int[] L, int k) {
    // write your code here
    if (L == null || L.length == 0) {
      return 0;
    }
/**
 * in the method, int n; only declare, but no default val is init!!!!!!!!
 */
    int maxLen = 0;    // = 0
    for (int e : L) {
      if (e > maxLen) {
        maxLen = e;
      }
    }

//    // another way to get max val
//    for (int e : L) {
//      maxLen = Math.max(maxLen, e);
//    }

    int start = 1;
    int end = maxLen;

    while (start + 1 < end) {
      int mid = start + (end - start) / 2;
      if (getK(L, mid) >= k) {
        start = mid;
      } else {
        end = mid;
      }
    }

    if (getK(L, end) >= k) {
      return end;
    }
    if (getK(L, start) >= k) {
      return start;
    }

    return 0;
  }

  private int getK(int[] L, int len) {
    int k = 0;    // = 0
    for (int e : L) {
      int num = e / len;
      k += num;
    }
    return k;
  }
}
