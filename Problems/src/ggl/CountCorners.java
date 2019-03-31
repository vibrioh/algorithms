package ggl;

import java.util.HashMap;
import java.util.Map;

/*
思路：任选两行for for loop，若相对应列（第三个for）都有点，就count++，然后对这两行的count任取两个组合，加到result上

Intuition

We ask the question: for each additional row, how many more rectangles are added?

For each pair of 1s in the new row (say at new_row[i] and new_row[j]), we could create more rectangles where that pair forms the base. The number of new rectangles is the number of times some previous row had row[i] = row[j] = 1.

Algorithm

Let's maintain a count count[i, j], the number of times we saw row[i] = row[j] = 1. When we process a new row, for every pair new_row[i] = new_row[j] = 1, we add count[i, j] to the answer, then we increment count[i, j].


Complexity Analysis

Time Complexity: O(R*C^2)O(R∗C
2
 ) where R, CR,C is the number of rows and columns.

Space Complexity: O(C^2)O(C
2
 ) in additional space.
 */
public class CountCorners {
    public int countCornerRectangles(int[][] grid) {
        Map<Integer, Integer> count = new HashMap();
        int ans = 0;
        for (int[] row : grid) {
            for (int c1 = 0; c1 < row.length; ++c1)
                if (row[c1] == 1) {
                    for (int c2 = c1 + 1; c2 < row.length; ++c2)
                        if (row[c2] == 1) {
                            int pos = c1 * 200 + c2;
                            int c = count.getOrDefault(pos, 0);
                            ans += c;
                            count.put(pos, c + 1);
                        }
                }
        }
        return ans;
    }
}
