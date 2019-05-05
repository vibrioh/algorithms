package ggl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/*
Intuition:
Verify equality of string and change it if necessay.
The only thing we need take attention is that the string form sources and targets can be different.
Instead of record the length changement, I sort indexes and do it from right to left.
(Since indexes.length <= 100 is really small)
In this way, the different length won't bother us anymore.

Explanation:

Descending indexes[] with tracking its index.
Verify equality of subring in S source and source.
Replace S if necessay.
Time Complexity:
O(SN)

Comments from @CanDong:
Since there won't be any overlap in replacement, every character in S will be compared at most once.
If using StringBuilder, it should be O(NlogN + S).
 */
public class FindAndReplaceInString {
    public String findReplaceString(String S, int[] indexes, String[] sources, String[] targets) {
        List<int[]> sorted = new ArrayList<>();
        for (int i = 0; i < indexes.length; i++) sorted.add(new int[]{indexes[i], i});
        Collections.sort(sorted, Comparator.comparing(i -> -i[0]));
        for (int[] ind : sorted) {
            int i = ind[0], j = ind[1];
            String s = sources[j], t = targets[j];
            if (S.substring(i, i + s.length()).equals(s)) S = S.substring(0, i) + t + S.substring(i + s.length());
        }
        return S;
    }


}
