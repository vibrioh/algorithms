package cc189.c3c4;

import java.util.Stack;

/**
 * In this approach, stackNewest has the newest elements on top and stackOldest has the oldest
 elements on top. When we dequeue an element, we want to remove the oldest element first, and so we
 dequeue from stackOldest. If stackOldest is empty, then we want to transfer all elements from
 stackNewest into this stack in reverse order. To insert an element, we push onto stackNewest, since it
 has the newest elements on top.

 * Queue via Stacks: Implement a MyQueue class which implements a queue using two stacks
 */

public class MyQueue<T> {
  Stack<T> stackOldest;
  Stack<T> stackNewest;

  public MyQueue() {
    stackOldest = new Stack<>();
    stackOldest = new Stack<>();
  }

  public int size() {
    return stackOldest.size() + stackNewest.size();
  }

  public void add(T val) {
    /*
    Push onto stackNewest, which always has the newst elements on top.
     */
    stackNewest.push(val);
  }

  /*
  Move elements from stackNewest into stackOldest.
  This usually done so that we can do operations on stackOldest.
   */
  private void shiftStacks() {
    if (stackOldest.isEmpty()) {
      while (!stackNewest.isEmpty()) {
        stackOldest.push(stackNewest.pop());
      }
    }
  }

  public T peek() {
    shiftStacks();    // Ensure stackOldest has the current elements.
    return stackOldest.peek();    // Retrieve the oldest item.
  }

  public T remove() {
    shiftStacks();    // Ensure stackOldest has the current elements.
    return stackOldest.pop();    // Pop the oldest item.
  }
}
