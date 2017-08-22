package jiuzhang.linkedlist;

public class ReverseLinkedListII {
  /*
   * @param head: ListNode head is the head of the linked list
   * @param m: An integer
   * @param n: An integer
   * @return: The head of the reversed ListNode
   */
  public ListNode reverseBetween(ListNode head, int m, int n) {
    ListNode dummy = new ListNode(0);

    dummy.next = head;
    head = dummy;

    ListNode left = dummy;
    ListNode right = dummy.next;
    ListNode prev = null;


    for (int i = 0; i < n + 1; i++) {
      if (i < m - 1) {
        head = head.next;
      }
      else if (i == m - 1) {
        left = head;
        right = head.next;
        head = head.next;
      } else {
        ListNode temp = head.next;
        head.next = prev;
        prev = head;
        head = temp;
      }
    }

    left.next = prev;
    right.next = head;


    return dummy.next;
  }
}
