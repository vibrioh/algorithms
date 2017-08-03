package cc189.c1c2;

import java.util.Set;
import java.util.HashSet;

/**
 * https://www.youtube.com/watch?v=apIw0Opq5nk
 Loop Detection: Given a circular linked list, implement an algorithm that returns the node at the
 beginning of the loop.
 DEFINITION
 Circular linked list: A (corrupt) linked list in which a node's next pointer points to an earlier node, so
 as to make a loop in the linked list.
 EXAMPLE
 Input:
 Output:
 A - > B - > C - > D - > E - > C [the same C as earlier]
 C
 */
public class LoopDetection {

  static Node loopNode(Node head) {
    Set<Node> set = new HashSet<>();
    while (head != null) {
      if (set.contains(head)) {
        return head;
      } else {
        set.add(head);
      }
      head = head.next;
    }
    return null;
  }

  static Node floydNode(Node head) {
    Node s = head;
    Node f = head;
    while (f != null && f.next !=null) {    // be careful need to first check f != null, then you can call f.next to check;
      s = s.next;
      f = f.next.next;
      if (s == f) {
        s = head;
        while (s != f) {
          s = s.next;
          f = f.next;
        }
        return s;
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
    head.next.next.next.next.next = new Node(9);
    head.next.next.next.next.next.next = new Node(3);
    head.next.next.next.next.next.next.next = new Node(2);
    head.next.next.next.next.next.next.next.next = head.next.next.next.next;

    System.out.println("the loop start node is: " + floydNode(head).data);
  }
}