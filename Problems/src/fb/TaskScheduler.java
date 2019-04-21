package fb;

import java.util.Arrays;

public class TaskScheduler {
    // (c[25] - 1) * (n + 1) + 25 - i  is frame size
    // when inserting chars, the frame might be "burst", then tasks.length takes precedence
    // when 25 - i > n, the frame is already full at construction, the following is still valid.

    /*
    Examples:

    AAAABBBEEFFGG 3

    here X represents a space gap:

    Frame: "AXXXAXXXAXXXA"
    insert 'B': "ABXXABXXABXXA" <--- 'B' has higher frequency than the other characters, insert it first.
    insert 'E': "ABEXABEXABXXA"
    insert 'F': "ABEFABEXABFXA" <--- each time try to fill the k-1 gaps as full or evenly as possible.
    insert 'G': "ABEFABEGABFGA"
    AACCCBEEE 2

    3 identical chunks "CE", "CE CE CE" <-- this is a frame
    insert 'A' among the gaps of chunks since it has higher frequency than 'B' ---> "CEACEACE"
    insert 'B' ---> "CEABCEACE" <----- result is tasks.length;
    AACCCDDEEE 3

    3 identical chunks "CE", "CE CE CE" <--- this is a frame.
    Begin to insert 'A'->"CEA CEA CE"
    Begin to insert 'B'->"CEABCEABCE" <---- result is tasks.length;
    ACCCEEE 2

    3 identical chunks "CE", "CE CE CE" <-- this is a frame
    Begin to insert 'A' --> "CEACE CE" <-- result is (c[25] - 1) * (n + 1) + 25 -i = 2 * 3 + 2 = 8
     */

    public class Solution {
        public int leastIntervalORI(char[] tasks, int n) {
            int[] c = new int[26];  // counts
            for (char t : tasks) {
                c[t - 'A']++;
            }
            Arrays.sort(c);
            int i = 25;
            while (i >= 0 && c[i] == c[25]) i--;
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
