package amz;

public class Test {
    public static void main(String[] args) {
        Solutions s = new Solutions();
        int[][] tests = new int[][]{{6, 4, 3, 4, 6}, {3, 8, 3}, {3, 4, 5, 23, 5, 87, 32, 5, 2, 4, 3, 2, 5, 76, 3}};
        for (int[] test : tests) {
            System.out.println(s.flyPathRoute(test));
        }
    }
}
