package jiuzhang.binarysearch;

/**
 * 关键在于利用倍增（exponential increase）找到BinarySearch的end
 * Given a big sorted array with positive integers sorted by ascending order.
 * The array is so big so that you can not get the length of the whole array directly,
 * and you can only access the kth number by ArrayReader.get(k) (or ArrayReader->get(k) for C++).
 * Find the first index of a target number. Your algorithm should be in O(log k),
 * where k is the first index of the target number.
 * Return -1, if the number doesn't exist in the array.
 Example
 Given [1, 3, 6, 9, 21, ...], and target = 3, return 1.
 Given [1, 3, 6, 9, 21, ...], and target = 4, return -1.
 */

public class SearchInBigSortedArray {

  class ArrayReader {
    int get(int n) {
      return n;
    }
  }

  public int searchBigSortedArray(ArrayReader reader, int target) {
    // 1. get the index that ArrayReader.get() >= target in O(log k)
    int index = 1;
    while (reader.get(index - 1) < target) {
      index = index * 2;
    }

    // 2. binary search the target between 0 and index
    int start = 0;
    int end = index -1;
    while (start +1 < end) {
      int mid = start + (end - start) / 2;
      if (reader.get(mid) == target) {
        end = mid;
      } else if (reader.get(mid) < target) {
        start = mid;
      } else {
        end = mid;
      }
    }

    if (reader.get(start) == target) {
      return start;
    }
    if (reader.get(end) == target) {
      return end;
    }

    return -1;
  }
}
