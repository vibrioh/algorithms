package cc189;

import java.util.Set;
import java.util.HashSet;

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


  public static void main(String[] args) {
    Node head = new Node(1);
    head.next = new Node(2);
    head.next.next = new Node(3);
    head.next.next.next = new Node(4);
    head.next.next.next.next = new Node(5);
    head.next.next.next.next.next = new Node(9);
    head.next.next.next.next.next.next = new Node(3);
    head.next.next.next.next.next.next.next = new Node(2);
    head.next.next.next.next.next.next.next.next = head.next.next.next.next.next;

    System.out.println("the loop start node is: " + loopNode(head).data);
  }
}