package leetcode;

public class NumberComplement {

  /**
   * num = Integer.MAX_VALUE
   0111 1111 1111 1111 1111 1111 1111 1111 // num
   1000 0000 0000 0000 0000 0000 0000 0000 // ~num
   0100 0000 0000 0000 0000 0000 0000 0000 // Integer.highestOneBit(num)
   1000 0000 0000 0000 0000 0000 0000 0000 // Integer.highestOneBit(num) << 1
   return 0

   * num = 5 (101)
   1000 0000 0000 0000 0000 0000 0000 0101 // num
   1111 1111 1111 1111 1111 1111 1111 1010 // ~num
   0000 0000 0000 0000 0000 0000 0000 0100 // Integer.highestOneBit(num)
   1000 0000 0000 0000 0000 0000 0000 1000 // Integer.highestOneBit(num) << 1
   return 2 (010)

   * @param num an integer
   * @return complement without zero leading change
   */

  public static int findComplement(int num) {
    return ~num + (Integer.highestOneBit(num) << 1);
  }

  public static void main(String[] args) {
    System.out.println(Integer.highestOneBit(1));
    System.out.println(Integer.highestOneBit(2));
    System.out.println(Integer.highestOneBit(3));
    System.out.println(Integer.highestOneBit(4));
    System.out.println(Integer.highestOneBit(5));
    System.out.println(Integer.highestOneBit(6));
    System.out.println(Integer.highestOneBit(7));
    System.out.println(Integer.highestOneBit(0));
    System.out.println(findComplement(5));
  }

}
