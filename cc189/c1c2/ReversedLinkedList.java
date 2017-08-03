package cc189.c1c2;

public class ReversedLinkedList {
  static Node newLinkedListReversed(Node head) {
    Node rehead = null;
    Node curr = null;
    while (head != null) {
      rehead = new Node(head.data);    // this is new created.
      rehead.next =curr;
      curr = rehead;
      head = head.next;
    }
    return rehead;
  }

  static Node linkedListReversed(Node head) {
    Node curr = null;
    Node prev = null;
    while (head != null) {
      prev = head.next;    // reference switch
      head.next = curr;
      curr = head;
      head = prev;
    }
    head = curr;
    return head;
  }


  public static void main(String[] args) {
    Node head = new Node(3);
    head.next = new Node(5);
    head.next.next = new Node(8);
    head.next.next.next = new Node(5);
    head.next.next.next.next = new Node(10);
    head.next.next.next.next.next = new Node(2);
    head.next.next.next.next.next.next = new Node(1);

    Node iter = head;
    while (iter != null) {
      System.out.print(iter.data + "  ");
      iter = iter.next;
    }
    Node newHead = linkedListReversed(head);
    System.out.println();
    while (newHead != null) {
      System.out.print(newHead.data + "  ");
      newHead = newHead.next;
    }
  }
}
