package cc189.c1c2;

import static cc189.c1c2.ReversedLinkedList.newLinkedListReversed;

import java.util.Stack;

public class LinkedListPalindrome {

  static boolean isLinkedListPalinedrome(Node head) {

    Node rhead = newLinkedListReversed(head);

    while (head != null) {
      if (head.data != rhead.data) {
        return false;
      }
      head = head.next;
      rhead = rhead.next;
    }
    return true;
  }

  static boolean isLinkedListPalinedromeStack(Node head) {
    if (head == null) {
      return false;
    } else if (head.next == null) {
      return true;
    }
    Node p1 = head;
    Node p2 = head;
    Stack<Integer> stack = new Stack<>();
    stack.push(head.data);
    while (p2 != null && p2.next != null) {    // need to check p2.next != null ,if you are using p2.next.next, or null pointer
      stack.push(p1.data);
//      System.out.println("\n" +p1.data);
      p1 = p1.next;
      p2 = p2.next.next;
    }
    if (p2.next == null) {    // has odd number of elements, so skip the middle element
      p1 = p1.next;
    }
    while (p1 != null) {
      if (stack.pop() != p1.data) {    // why do not need intValue()?
        return false;
      }
      p1 = p1.next;
    }
    return true;
  }










  public static void main(String[] args) {
    Node head = new Node(1);
    head.next = new Node(2);
    head.next.next = new Node(3);
    head.next.next.next = new Node(4);
    head.next.next.next.next = new Node(5);
    head.next.next.next.next.next = new Node(4);
    head.next.next.next.next.next.next = new Node(3);
    head.next.next.next.next.next.next.next = new Node(2);
    head.next.next.next.next.next.next.next.next = new Node(1);

    Node iter = head;
    while (iter != null) {
      System.out.print(iter.data + "  ");
      iter = iter.next;
    }
    boolean newHead = isLinkedListPalinedromeStack(head);
    System.out.println();

    System.out.print(newHead);
  }
}
