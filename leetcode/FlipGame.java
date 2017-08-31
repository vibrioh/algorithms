package leetcode;

import java.util.ArrayList;
import java.util.List;

public class FlipGame {
  class Solution {
    public List<String> generatePossibleNextMoves(String s) {
      List<String> res = new ArrayList<>();
      if (s == null) {
        return res;
      }

      for (int i = 0; i < s.length() - 1; i++) {
        if (s.charAt(i) == '+' && s.charAt(i + 1) == '+') {
          res.add(s.substring(0, i) + "--" + s.substring(i + 2));
        }
      }

      return res;
    }
  }


}
