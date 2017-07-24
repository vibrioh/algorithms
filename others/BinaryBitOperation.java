package others;

/**
 * Binary code
 * Inverse code or one's complement
 * Complement code or ```two's complement
 */
public class BinaryBitOperation {

  /**
   * @param args test bitwise and bit shit operators
   * a & b -- and -- 1 if both bits are 1
   * a | b -- or	-- 1 if either bit is 1
   * a ^ b -- xor -- 1 if both bits are different
   * ~a    -- not -- inverts the bits
   * n << p  --- left shift  --- Shifts the bits of n left p positions. Zero bits are shifted into the low-order positions.
   * n >> p  --- right shift --- Shifts the bits of n right p positions. If n is a 2's complement signed number, the sign bit is shifted into the high-order positions.
   * n >>> p --- right shift --- 	Shifts the bits of n right p positions. Zeros are shifted into the high-order positions.
   */
  public static void main(String args[]) {
    int a = 60;	/* 60 = 0011 1100 */
    int b = 13;	/* 13 = 0000 1101 */
    int c; // default: 0

    c = a & b;        /* 12 = 0000 1100 */
    System.out.println("a & b = " + c );

    c = a | b;        /* 61 = 0011 1101 */
    System.out.println("a | b = " + c );

    c = a ^ b;        /* 49 = 0011 0001 */
    System.out.println("a ^ b = " + c );

    c = ~a;           /*-61 = 1100 0011 */
    System.out.println("~a = " + c );

    c = a << 2;       /* 240 = 1111 0000 */
    // equal to a * pow(2, x); here x = 2
    System.out.println("a << 2 = " + c );

    c = a >> 2;       /* 15 = 1111 */
    // equal to a / pow(2, x); here x = 2
    System.out.println("a >> 2  = " + c );

    c = a >>> 2;      /* 15 = 0000 1111 */
    System.out.println("a >>> 2 = " + c );

    // int a, b exchange, only for interview
    a = a ^ b;
    b = a ^ b; // a ^ b ^ b == a
    a = a ^ b; // a ^ b ^ a == b
    System.out.printf("a = %2d, b = %2d", a, b);
  }
}
