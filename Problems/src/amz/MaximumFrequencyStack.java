package amz;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class MaximumFrequencyStack {
    class FreqStack {
        // record frequency for each num
        // num : freq
        Map<Integer, Integer> numFreq;
        // keep the least order for the frequency
        // freq : nums
        Map<Integer, Stack<Integer>> freqNums;
        // current max frequency
        int maxF;

        public FreqStack() {
            numFreq = new HashMap<>();
            freqNums = new HashMap<>();
            int maxF = 0;
        }

        public void push(int x) {
            int f = numFreq.getOrDefault(x, 0) + 1;
            if (f > maxF) {
                maxF = f;
            }
            numFreq.put(x, f);
            freqNums.putIfAbsent(f, new Stack<>());
            freqNums.get(f).push(x);
        }

        public int pop() {
            // trace the maxF
            int x = freqNums.get(maxF).pop();
            numFreq.put(x, numFreq.get(x) - 1);
            if (freqNums.get(maxF).size() == 0) {
                maxF--;
            }
            return x;
        }
    }
}
