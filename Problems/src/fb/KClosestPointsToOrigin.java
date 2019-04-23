package fb;

import java.util.Arrays;
import java.util.Random;

public class KClosestPointsToOrigin {

    // O(n)
    public int[][] kClosest(int[][] points, int K) {
        quickselect(points, 0, points.length - 1, K);
        return Arrays.copyOfRange(points, 0, K);
    }

    private int getDistance(int[][] points, int idx) {
        return points[idx][0] * points[idx][0] + points[idx][1] * points[idx][1];
    }

    private void swap(int[][] points, int i, int j) {
        int[] tmp = points[i];
        points[i] = points[j];
        points[j] = tmp;
    }

    private int partition(int[][] points, int l, int r, int pivot_idx) {
        // need to get the pivot first, because if you swap, the idx for pivot may change!!!!
        int pivot = getDistance(points, pivot_idx);
        swap(points, r, pivot_idx);
        // for (int[] i : points) {
        //     System.out.print("[" + i[0] + " " + i[1] + "] ");
        // }
        int idx = l;
        for (int i = l; i <= r; i++) {
            if (getDistance(points, i) < pivot) {
                // System.out.println(getDistance(points, i)  + "<->" + getDistance(points, pivot_idx) + " pivot: " + pivot);
                // System.out.println(idx + "<->" + i);
                swap(points, i, idx);
                idx++;
            }
        }
        // System.out.println(idx + " " + r);
        swap(points, idx, r);
        return idx;
    }

    private int quickselect(int[][] points, int l, int r, int K) {
        if (l == r) {
            return l;
        }
        Random random_num = new Random();
        int pivot_idx = l + random_num.nextInt(r - l);
        // System.out.println(pivot_idx);
        int idx = partition(points, l, r, pivot_idx);
        if (idx == K) {
            return idx;
        } else if (idx < K) {
            return quickselect(points, idx + 1, r, K);
        }
        return quickselect(points, l, idx - 1, K);
    }



    // nlogn
    public int[][] kClosestSort(int[][] points, int K) {
        Arrays.sort(points, (a, b) -> a[0]*a[0] + a[1]*a[1] - b[0]*b[0]-b[1]*b[1]);
        return Arrays.copyOfRange(points, 0, K);
    }

}
