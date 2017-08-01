package cc189;

import static cc189.ReversedLinkedList.newLinkedListReversed;

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

  public static void main(String[] args) {
    Node head = new Node(1);
    head.next = new Node(2);
    head.next.next = new Node(3);
    head.next.next.next = new Node(4);
    head.next.next.next.next = new Node(3);
    head.next.next.next.next.next = new Node(2);
    head.next.next.next.next.next.next = new Node(1);

    Node iter = head;
    while (iter != null) {
      System.out.print(iter.data + "  ");
      iter = iter.next;
    }
    boolean newHead = isLinkedListPalinedrome(head);
    System.out.println();

    System.out.print(newHead);
  }
}
