package fb;

import java.util.ArrayList;
import java.util.List;

public class IntervalListIntersections {
    public int[][] intervalIntersection(int[][] A, int[][] B) {
        List<int[]> res = new ArrayList<>();
        int la = A.length;
        int lb = B.length;
        int i = 0;
        int j = 0;
        while (i < la && j < lb) {
            int[] a = A[i];
            int[] b = B[j];
            int astart = a[0];
            int aend = a[1];
            int bstart = b[0];
            int bend = b[1];
            int instart = Math.max(astart, bstart);
            int inend = Math.min(aend, bend);
            // System.out.println(i + "-" + j);
            // System.out.println(instart + " " + inend);
            if (instart <= inend) {
                res.add(new int[]{instart, inend});
            }
            if (aend < bend) {
                i++;
            } else {
                j++;
            }
        }
        // new to new the object with size
        return res.toArray(new int[res.size()][]);
    }
}
