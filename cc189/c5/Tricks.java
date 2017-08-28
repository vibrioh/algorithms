package cc189.c5;

public class Tricks {

  /**
   * how to get ith bit of an int
   * @param num an int
   * @param i ith position of the int
   * @return bit 1 or 0
   */
  int getBit(int num, int i) {
    return num & (1 << i);
  }    // so last bit = num & 1

  /**
   * how to set ith bit to 1
   * @param num an int
   * @param i ith position of th int
   * @return new num
   */
  int setBit(int num, int i) {
    return num | (1 << i);
  }

  /**
   * reverse operation of setBit
   * @param num an int
   * @param i ith position
   * @return new num
   */
  int clearBit(int num, int i) {
    return num & ~ (1 << i);
  }


  int clearLastBitOne(int n) {
    return n & (n - 1);
  }

  /**
   * n is power of 2 when binary contains only one 1, so (n & (n - 1)) should clear that 1.
   * @param n int
   * @return if or not power of 2
   */
  boolean isPowTwo(int n) {
    return n > 0 && ((n & (n - 1)) == 0);
  }

}
