package amz;

import java.util.Stack;

class MinStack {
    int min;
    Stack<Integer> stack;

    /**
     * initialize your data structure here.
     */
    public MinStack() {
        min = Integer.MAX_VALUE;
        stack = new Stack<>();
    }

    public void push(int x) {
        // 仅当新的元素是最小，讲上一次的最小先放进栈，再更新最小
        if (x <= min) {
            stack.push(min);
            min = x;
        }
        // 无论如何，放进最新元素
        stack.push(x);
    }

    public void pop() {
        // 仅当pop出最小元素，才再pop一次更新到上次的最小元素
        if (min == stack.pop()) {
            min = stack.pop();
        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return min;
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
