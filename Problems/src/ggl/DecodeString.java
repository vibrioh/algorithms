package ggl;

import java.util.Stack;

public class DecodeString {
    /*
      1.使用两个栈，countStack存子串的重复次数，resStack存中间结果
      2.读取完一个数字，把数量入countStack栈
      3.读取到[时，把n[前面的结果计算出来并入resStack栈
      4.读取到]时，取出最近一次n[res]前面的中间计算结果，也就是resStack的栈顶，再append n次res，n为countStack的栈顶，res为最近一次[]中间的子串
      5.其他情况，将字符追加到res末尾

      比如:  3[a]2[b3[d]c]
     下面是读完各字符时的情况：
     *        读完:  3    [     a     ]     2    [    b      3       [      d       ]      c       ]
     * countStack: (3)  (3)   (3)    ()   (2)  (2)  (2)   (2 3)   (2 3)   (2 3)   (2)    (2)      ()
     *   resStack: ()   ("")  ("")   ()   ()  (aaa) (aaa) (aaa)  (aaa b) (aaa b)  (aaa) (aaa)     ()
     *        res: ""    ""    a     aaa  aaa   ""   b      b       ""     d      bddd  bdddc  aaabdddcbdddc
     */
    public String decodeString(String s) {
        String res = "";
        Stack<Integer> countStack = new Stack<>();
        Stack<String> resStack = new Stack<>();
        int idx = 0;
        while (idx < s.length()) {
            if (Character.isDigit(s.charAt(idx))) {
                int count = 0;
                while (Character.isDigit(s.charAt(idx))) {
                    count = 10 * count + (s.charAt(idx) - '0');
                    idx++;
                }
                countStack.push(count);
            } else if (s.charAt(idx) == '[') {
                resStack.push(res);
                res = "";
                idx++;
            } else if (s.charAt(idx) == ']') {
                StringBuilder temp = new StringBuilder(resStack.pop());
                int repeatTimes = countStack.pop();
                for (int i = 0; i < repeatTimes; i++) {
                    temp.append(res);
                }
                res = temp.toString();
                idx++;
            } else {
                res += s.charAt(idx++);
            }
        }
        return res;
    }
}
