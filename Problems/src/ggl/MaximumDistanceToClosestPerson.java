package ggl;

public class MaximumDistanceToClosestPerson {
    /*
    Count the numbers of continuous zeros in the prefix, res = max(res, zeros)
    Count the numbers of continuous zeros in middle, res = max(res, (zeros + 1) / 2)
    Count the numbers of continuous zeros in the suffix, res = max(res, zeros)
     */

    public int maxDistToClosest(int[] seats) {
        int i, j, res = 0, n = seats.length;
        for (i = j = 0; j < n; ++j)
            if (seats[j] == 1) {
                if (i == 0)
                    res = j;
                else
                    res = Math.max(res, (j - i + 1) / 2);
                i = j + 1;
            }
        res = Math.max(res, n - i);
        return res;
    }
}
