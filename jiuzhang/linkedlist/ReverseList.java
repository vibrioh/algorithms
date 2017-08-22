package jiuzhang.linkedlist;

class ListNode {

  int val;
  ListNode next;

  ListNode(int val) {
    this.val = val;
    this.next = null;
  }
}

public class ReverseList {

  /**
   * 1. which is a pointer, and which is a node?
   * 2. How is changing pointing, and how is changing linking?
   * 3. Which pointer is pointing which node?
   *
   * @param head first node
   * @return new head node
   */

  public ListNode reverseList(ListNode head) {
    ListNode prev = null;
    ListNode curr = head;
    while (curr != null) {
      ListNode temp = curr.next;
      curr.next = prev;
      prev = curr;
      curr = temp;
    }
    return prev;
  }
}
