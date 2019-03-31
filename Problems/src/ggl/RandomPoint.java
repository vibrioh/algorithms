package ggl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*

对于有多个矩形的情况，我们可以考虑先选出一个矩形，再在该矩形内选点
要求每个点选取的概率相同，那么选取一个矩形的概率就和该矩形的面积成正比
所以我们可以把所有矩形的面积的和sum算出来，然后在rand一个数%sum，再判断落在哪个矩形里
比如说有面积为3, 2, 1, 4的矩形，总和是10，randM = rand() % 10 ，如果randM==0,1,2就选面积为3的矩形，randM == 3,4就选面积为2的矩形，其他的同理
选出了矩形，然后在该矩形内选点即可

 */

public class RandomPoint {
    int[][] rects;
    List<Integer> psum = new ArrayList<>();
    int tot = 0;
    Random rand = new Random();

    public RandomPoint(int[][] rects) {
        this.rects = rects;
        for (int[] x : rects) {
            tot += (x[2] - x[0] + 1) * (x[3] - x[1] + 1);
            psum.add(tot);
        }
    }

    public int[] pick() {
        int targ = rand.nextInt(tot);

        int lo = 0;
        int hi = rects.length - 1;
        while (lo != hi) {
            int mid = (lo + hi) / 2;
            if (targ >= psum.get(mid)) lo = mid + 1;
            else hi = mid;
        }

        int[] x = rects[lo];
        int width = x[2] - x[0] + 1;
        int height = x[3] - x[1] + 1;
        int base = psum.get(lo) - width * height;
        return new int[]{x[0] + (targ - base) % width, x[1] + (targ - base) / width};
    }
}
