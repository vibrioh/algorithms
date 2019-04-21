package fb;

import java.util.Arrays;

public class TaskScheduler {
    // (c[25] - 1) * (n + 1) + 25 - i  is frame size
    // when inserting chars, the frame might be "burst", then tasks.length takes precedence
    // when 25 - i > n, the frame is already full at construction, the following is still valid.

    public class Solution {
        public int leastInterval(char[] tasks, int n) {
            int[] c = new int[26];
            for(char t : tasks){
                c[t - 'A']++;
            }
            Arrays.sort(c);
            int i = 25;
            while(i >= 0 && c[i] == c[25]) i--;
            // if frame filled to be full, then tasks.length will be the answer, AbAcA + defghijklmn
            return Math.max(tasks.length, (c[25] - 1) * (n + 1) + 25 - i); // ABXXXABXXXABXXX + AB
        }
    }
}

/*
The final function is not easy to understand if written in the author's way. I suggest chaing it to "(c[25] - 1) * n + c[25] + (25 - i - 1)".
In this formula, "(c[25] - 1) * n" is the number of X in the Frame: "AXXXAXXXAXXXA"
c[25] is the number of A in the Frame "AXXXAXXXAXXXA"
"25 - i - 1" is the number that has to be added behind the Frame "AXXXAXXXAXXXA". For example : in Frame "ABXXABXXABXXAB", we have to add 1 B behind the Fram, which is 25 - 23 - 1 = 1
 */
