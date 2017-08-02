package cc189;

import java.util.HashSet;
import java.util.Set;

/**
 * using HashSet
 Intersection: Given two (singly) linked lists, determine if the two lists intersect. Return the
 intersecting node. Note that the intersection is defined based on reference, not value. That is, if the
 kth node of the first linked list is the exact same node (by reference) as the jth node of the second
 linked list, then they are intersecting.
 */

public class Intersection {

  /**
   * HashSet
   * time O(m+n); space O(m+n)
   * @param h1
   * @param h2
   * @return
   */
  static Node intersectLinkedList(Node h1, Node h2) {
    Set<Node> set = new HashSet<>();
    while (h1 != null || h2 != null) {
      if (set.contains(h1)) {
        return h1;
      } else if (h1 != null) {
        set.add(h1);
        h1 = h1.next;
      }
      if (set.contains(h2)) {
        return h2;
      } else if (h2 != null) {
        set.add(h2);
        h2 = h2.next;
      }
    }
    return null;
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

    Node head2 = new Node(1);
    head2.next = new Node(2);
    head2.next.next = new Node(3);
    head2.next.next.next = new Node(4);
    head2.next.next.next.next = new Node(5);
    head2.next.next.next.next.next = head.next.next.next.next.next;

    Node newHead = intersectLinkedList(head, head2);

    System.out.println();
    while (newHead != null) {
      System.out.print(newHead.data + "  ");
      newHead = newHead.next;
    }

  }
}
