package leetcode;

public class ConstructTheRectangle {

  /**
   * The W is always less than or equal to the square root of area
   so we start searching at sqrt(area) till we find the result
   * @param area
   * @return
   */
  public int[] constructRectangle(int area) {
    int w = (int)Math.sqrt(area);
    while (area%w!=0) w--;
    return new int[]{area/w, w};
  }
}
