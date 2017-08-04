package cc189.c3c4;

import java.util.Stack;

/**
 * push the min when it is a min at push(), pop the min when it is a min at pop().
 * using another class @param stackmin tracking @method min()
 Stack Min: How would you design a stack which, in addition to push and pop, has a function min
 which returns the minimum element? Push, pop and min should all operate in 0(1) time.
 */
public class StackMin extends Stack<Integer> {
  Stack<Integer> stackmin;    // this initiate a class param, not in the constructor

  public StackMin() {
    stackmin = new Stack<>();    // when a class initiate, this param is created
  }

  public int min() {    // class method return min from class param
    if (stackmin.isEmpty()) {
      return Integer.MAX_VALUE;
    }
    return stackmin.peek();
  }

  public void push(int val) {
    super.push(val);
    if (val <= min()) {
      stackmin.push(val);
    }
  }

  public Integer pop() {
    int val = super.pop();
    if (val == min()) {
      stackmin.pop();
    }
    return val;
  }



  public static void main(String[] args) {
    StackMin stack = new StackMin();
    int[] array = {7, 4, 3, 6, 8, 9, 3, 2, 5, 6, 3, 8};
    for (int value : array) {
      stack.push(value);
      System.out.print(value + ", ");
    }
    System.out.println('\n');
    for (int i = 0; i < array.length; i++) {
      System.out.println("Popped " + stack.pop());
      System.out.println("New min is " + stack.min());
    }
  }
}
