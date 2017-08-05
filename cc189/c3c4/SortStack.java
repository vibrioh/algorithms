package cc189.c3c4;

import java.util.Stack;

/**
 * This algorithm is O ( N 2 ) time and O ( N) space.
 * Sort Stack: Write a program to sort a stack such that the smallest items are on the top. You can use
 an additional temporary stack, but you may not copy the elements into any other data structure
 (such as an array). The stack supports the following operations: push, pop, peek, and is Empty.

 If we were allowed to use unlimited stacks, we could implement a modified quicksort or mergesort.
 With the mergesort solution, we would create two extra stacks and divide the stack into two parts. We
 would recursively sort each stack, and then merge them back together in sorted order into the original
 stack. Note that this would require the creation of two additional stacks per level of recursion.
 With the quicksort solution, we would create two additional stacks and divide the stack into the two stacks
 based on a pivot element. The two stacks would be recursively sorted, and then merged back together
 into the original stack. Like the earlier solution, this one involves creating two additional stacks per level of
 recursion.
 */

public class SortStack {

  void sort(Stack<Integer> s) {
    Stack<Integer> r = new Stack<>();
    while (!s.isEmpty()) {
      int temp = s.pop();
      while (!r.isEmpty() && temp < r.peek()) {
        s.push(r.pop());
      }
      r.push(temp);
    }
    while (!r.isEmpty()) {
      s.push(r.pop());
    }
  }
}
