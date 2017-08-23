package jiuzhang.Array;

public class MergeTwoSortedArrays {
  /**
   * @param A: sorted integer array A
   * @param B: sorted integer array B
   * @return A new sorted integer array
   */
  public int[] mergeSortedArray(int[] A, int[] B) {
    int lenA = A.length;
    int lenB = B.length;
    int[] result = new int[lenA + lenB];

    int i = 0;
    int j = 0;
    while (i < lenA && j < lenB) {
      if (A[i] < B[j]) {
        result[i + j] = A[i];
        i++;
      } else {
        result[i + j] = B[j];
        j++;
      }
    }

    while (i < lenA) {
      result[i + j] = A[i];
      i++;
    }

    while (j < lenB) {
      result[i + j] = B[j];
      j++;
    }

    return result;
  }
}
