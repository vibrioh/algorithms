package ggl;

import java.util.Map;
import java.util.TreeMap;

/*
TreeMap
The map is sorted according to the natural ordering of its keys

用treeMap<position, time>去存，从接近target的地方往后遍历，local variable存最大撞线时间。若currTime > maxTime，则车次++,

Calculate time needed to arrive the target, sort by the start position.
Loop on each car from the end to the beginning. cur recorde the current biggest time (the slowest).
If another car needs less or equal time than cur, it can catch up this car.
Otherwise it will become the new slowest car, that is new lead of a car fleet.

Time Complexity:
O(NlogN)
 */

public class CarFleet {
    public int carFleet(int target, int[] pos, int[] speed) {
        Map<Integer, Double> m = new TreeMap<>();
        for (int i = 0; i < pos.length; ++i) {
            m.put(-pos[i], (double) (target - pos[i]) / speed[i]);
        }
        int res = 0;
        double curMax = 0;
        for (double time : m.values()) {
            if (time > curMax) {
                curMax = time;
                res++;
            }
        }
        return res;
    }
}
