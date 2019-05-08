package amz;

import java.util.ArrayList;
import java.util.List;

public class PartitionLabels {
    public List<Integer> partitionLabels(String S) {
        // greedy O(n) space O(1)
        int[] rightMostIdx = new int[26];
        char[] chars = S.toCharArray();
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < chars.length; i++) {
            rightMostIdx[chars[i] - 'a'] = i;
        }
        int idxL = 0;
        int currRightMost = 0;
        // greedy, walk and record current rightmost idx, when curr is equal to curr rightmost, then add
        for (int i = 0; i < chars.length; i++) {
            currRightMost = Math.max(currRightMost, rightMostIdx[chars[i] - 'a']);
            if (currRightMost == i) {
                res.add(i - idxL + 1);
                idxL = i + 1;
            }
        }
        return res;
    }
}
