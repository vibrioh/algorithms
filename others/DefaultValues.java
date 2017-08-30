package others;

import java.util.ArrayList;
import java.util.Arrays;

public class DefaultValues {
  static boolean bool;
  static byte by;
  static char ch;
  static double d;
  static float f;
  static int i;
  static long l;
  static short sh;
  static String str;
  static ArrayList<Integer> arraylist;
  static ArrayList<ArrayList<Integer>> arraylistofarraylist;
  static ArrayList<Integer> NewArraylist = new ArrayList<>();
  static int[] array = new int[5];

  public static void main(String[] args) {
    System.out.println("Bool :" + bool);
    System.out.println("Byte :" + by);
    System.out.println("Character:" + ch);
    System.out.println("Double :" + d);
    System.out.println("Float :" + f);
    System.out.println("Integer :" + i);
    System.out.println("Long :" + l);
    System.out.println("Short :" + sh);
    System.out.println("String :" + str);
    System.out.println("ArrayList :" + arraylist);
    System.out.println("ArrayListArrayList :" + arraylistofarraylist);
    System.out.println("NewArraylist :" + NewArraylist);
    System.out.println("array :" + Arrays.toString(array));
    System.out.println("0 % 23 :" + 0 % 23);
  }
}
