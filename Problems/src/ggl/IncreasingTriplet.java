package ggl;

/*
只有同时大于前面两个数才是连续三个升序，最前面的keep最小，中间的keep当前最大

从左到右过array，用两个变量存第一小和第二小的数（初始最大值），更新尽量小的数，若同时遇到第三小的数，则为true
 */
public class IncreasingTriplet {
    public boolean increasingTriplet(int[] nums) {
        // start with two largest values, as soon as we find a number bigger than both, while both have been updated, return true.
        int small = Integer.MAX_VALUE, big = Integer.MAX_VALUE;
        for (int n : nums) {
            if (n <= small) {
                small = n;
            } // update small if n is smaller than both
            else if (n <= big) {
                big = n;
            } // update big only if greater than small but smaller than big
            else return true; // return if you find a number bigger than both
        }
        return false;
    }
}
