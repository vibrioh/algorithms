package cc189;

/**
 * using quick-sort partition method, switch node.data
 Partition: Write code to partition a linked list around a value x, such that all nodes less than x come
 before all nodes greater than or equal to x. If x is contained within the list the values of x only need
 to be after the elements less than x (see below). The partition element x can appear anywhere in the
 "right partition"; it does not need to appear between the left and right partitions.
 EXAMPLE
 Input: 3 -> 5 -> 8 -> 5 -> 10 -> 2 -> 1 [partition= 5]
 Output: 3 -> 1 -> 2 -> 10 -> 5 -> 5 -> 8
 */

public class LinkedListPartition {

  /**
   * chose the FIRST as pivot, if next is smaller than p, switch value, move to next.
   * @param head = head of linked list
   * @param p = partition value
   * @return
   */
  public static Node pivotPartition(Node head, int p) {
    Node dummy = new Node(0);
    dummy.next = head;
    Node pivot = head;
    while (head != null) {
      if (head.data < p) {
        int temp = pivot.data;
        pivot.data = head.data;
        head.data = temp;
        pivot = pivot.next;
      }
      head = head.next;
    }
    return dummy.next;
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
    Node newHead = pivotPartition(head, 5);
    System.out.println();
    while (newHead != null) {
      System.out.print(newHead.data + "  ");
      newHead = newHead.next;
    }
  }
}
