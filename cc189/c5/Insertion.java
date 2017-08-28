package cc189.c5;

public class Insertion {

  /**
   * 1. clear the bits j htrough i in N
   * 2. shift M so that it lines up with bits j through i
   * 3. Merge M and N.
   * @param n needs to be inserted by m
   * @param m needs to be inserted into n
   * @param i start index
   * @param j end index
   * @return
   */
  public int insetion(int n, int m, int i, int j) {
    /*
  Create a mask to clear bits i through j in n. EXAMP:E: i = 2, j = 4. Result6 should be 11100011.
  For simplicity, we'll use just 8 bits for the example.
   */
    int allOnes = ~0;    // will equal sequence of all 1s

    // 1s before position j, then 0s left = 11100000
    int left = allOnes << (j + 1);

    // 1s after position i, right = 00000011
    int right = (1 << i) - 1;

    // all 1s, except for 0s between i and j, mask = 11100011
    int mask = left | right;

    /*
    clear bits j through i then put m in there
     */
    int n_cleared = n & mask;    // clear bits j through i
    int m_shifted = m << i;    // move m into correct position

    return n_cleared | m_shifted;    // OR them
  }



}
