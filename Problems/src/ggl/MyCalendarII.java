package ggl;

import java.util.TreeMap;

public class MyCalendarII {
    /*
    When booking a new event [start, end), count delta[start]++ and delta[end]--. When processing the values of delta in sorted order of their keys, the running sum active is the number of events open at that time. If the sum is 3 or more, that time is (at least) triple booked.
     */
    class MyCalendarTwo {
        TreeMap<Integer, Integer> delta;

        public MyCalendarTwo() {
            delta = new TreeMap();
        }

        public boolean book(int start, int end) {
            delta.put(start, delta.getOrDefault(start, 0) + 1);
            delta.put(end, delta.getOrDefault(end, 0) - 1);

            int active = 0;
            for (int d : delta.values()) {
                active += d;
                if (active >= 3) {
                    delta.put(start, delta.get(start) - 1);
                    delta.put(end, delta.get(end) + 1);
                    return false;
                }
            }
            return true;
        }

    }
}
