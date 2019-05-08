package amz;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class PrisonCellsAfterNDays {
    /*
    This is right solution, but it will get TLE when N is big.
    Note that cells.length = 8, and cells[0] and cells[7] will become 0.
    In fact, cells have only 2 ^ 6 = 64 different states.
    And there will be a loop.


    Solution 1
    We record all seen states.
    Be careful,
    we need transform array to string as the key,
    otherwise it use the reference.
     */
    public int[] prisonAfterNDays(int[] cells, int N) {
        Map<String, Integer> seen = new HashMap<>();
        while (N > 0) {
            int[] cells2 = new int[8];
            seen.put(Arrays.toString(cells), N--);
            for (int i = 1; i < 7; ++i)
                cells2[i] = cells[i - 1] == cells[i + 1] ? 1 : 0;
            cells = cells2;
            if (seen.containsKey(Arrays.toString(cells))) {
                N %= seen.get(Arrays.toString(cells)) - N;
            }
        }
        return cells;
    }
}
