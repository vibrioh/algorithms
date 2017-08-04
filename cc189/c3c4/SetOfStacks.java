package cc189.c3c4;

import java.util.ArrayList;
import java.util.EmptyStackException;

public class SetOfStacks {
  ArrayList<Stackc> stacks = new ArrayList<Stackc>();
  public int capacity;
  public SetOfStacks(int capacity) {
    this.capacity = capacity;
  }

  public Stackc getLastStack() {
    if (stacks.size() == 0) {
      return null;
    }
    return stacks.get(stacks.size() -1);
  }

  public void push(int v) {
    Stackc last = getLastStack();
    if (last != null && !last.isFull()) {    // add to last stack
      last.push(v);
    } else {    // must create new stack
      Stackc stack = new Stackc(capacity);
      stack.push(v);
      stacks.add(stack);
    }
  }

  public int pop() {
    Stackc last = getLastStack();
    if (last == null) throw new EmptyStackException();
    int v = last.pop();
    if (last.size == 0) {
      stacks.remove(stacks.size() - 1);
    }
    return v;
  }

  public boolean isEmpty() {
    Stackc last = getLastStack();
    return last == null || last.isEmpty();
  }

  public int popAt(int index) {
    return leftShift(index, true);
  }

  public int leftShift(int index, boolean removeTop) {
    Stackc stack = stacks.get(index);
    int removed_item;
    if (removeTop) removed_item = stack.pop();
    else removed_item = stack.removeBottom();
    if (stack.isEmpty()) {
      stacks.remove(index);
    } else if (stacks.size() > index + 1) {
      int v = leftShift(index + 1, false);
      stack.push(v);
    }
    return removed_item;
  }
}
