package plt;

import java.util.*;

public class TheSkylineProblem {
    public List<int[]> getSkyline(int[][] buildings) {
        List<int[]> res = new ArrayList<>();
        if (buildings == null || buildings.length == 0) {
            return res;
        }
        List<int[]> posHei = new ArrayList<>();
        for (int[] bd : buildings) {
            posHei.add(new int[]{bd[0], bd[2]});
            posHei.add(new int[]{bd[1], -bd[2]});
        }
        Collections.sort(posHei, (a, b) -> a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]);
        TreeMap<Integer, Integer> heiNum = new TreeMap<>();
        heiNum.put(0, 1);
        int prev = 0;
        for (int[] ph : posHei) {
            if (ph[1] > 0) {
                heiNum.put(ph[1], heiNum.getOrDefault(ph[1], 0) + 1);
            } else {
                if (heiNum.get(-ph[1]) > 1) {
                    heiNum.put(-ph[1], heiNum.get(-ph[1]) - 1);
                } else {
                    heiNum.remove(-ph[1]);
                }
            }
            int curr = heiNum.lastKey();
            if (curr != prev) {
                res.add(new int[]{ph[0], curr});
                prev = curr;
            }
        }
        return res;
    }
}
