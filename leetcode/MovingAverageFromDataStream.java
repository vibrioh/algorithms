package leetcode;

public class MovingAverageFromDataStream {
  class MovingAverage {
    private int[] array;
    private int times;
    private long sum;

    /** Initialize your data structure here. */
    public MovingAverage(int size) {
      array = new int[size];
      times = 0;
      sum = 0;
    }

    /**
     * always update the "next position but old" data in the array, recursively from [0]~[last]
     * @param val the new val input
     * @return average in the window
     */

    public double next(int val) {
      if (array.length == 0) {
        return 0;
      }

      sum -= array[times % array.length];
      sum += val;
      array[times % array.length] = val;
      times++;

      return array.length > times ? ((double) sum / times) : ((double) sum / array.length);
    }
  }

/**
 * Your MovingAverage object will be instantiated and called as such:
 * MovingAverage obj = new MovingAverage(size);
 * double param_1 = obj.next(val);
 */
}
