package cc189.c1c2;


/**
 *
 Sum Lists: You have two numbers represented by a linked list, where each node contains a single
 digit. The digits are stored in reverse order, such that the 1 's digit is at the head of the list. Write a
 function that adds the two numbers and returns the sum as a linked list.
 EXAMPLE
 Input: (7-> 1 -> 6) + (5 -> 9 -> 2).That is,617 + 295.
 Output: 2 -> 1 -> 9. That is, 912.
 FOLLOW UP
 Suppose the digits are stored in forward order. Repeat the above problem.
 Input: (6 -> 1 -> 7) + (2 -> 9 -> 5).That is,617 + 295.
 Output: 9 -> 1 -> 2. That is, 912.
 */

public class SumLists {

  public static Node sumTwoLinkedLists(Node h1, Node h2) {
    Node res = null;    // the head of resultant list
    Node prev = null;    // the iterate node of resultant list
    int carry = 0;
    /**
     * when need two conditions for looping, don't us two loops, but two check points in one loop
     */
    while (h1 != null || h2 != null) {    // any list's next node still contains digit, the loop keeps running
      int sum = carry + ((h1 != null) ? h1.data : 0) + ((h2 != null) ? h2.data : 0);    // calculate sum
      int digi = sum % 10;    // calculate 1st's digit
      carry = (sum < 10) ? 0 : 1;    // carry over next #th
      Node curr = new Node(digi);
      if (res == null) {    // create head for the loop run the first time
        res = curr;
      } else {
        prev.next = curr;    // link curr to the prev
      }
      prev = curr;    // make curr as the prev for the next round
      /**
       * because still don't know which would be null now, check again before iterate in case of null pointer
       */
      if (h1 != null) {
        h1 = h1.next;
      }
      if (h2 != null) {
        h2 = h2.next;
      }
    }
    if (carry == 1) {
      prev.next = new Node(1);    // may carry over to the non-existed position
    }
    return res;
  }





  public static void main(String[] args) {
    Node head1 = new Node(3);
    head1.next = new Node(5);
    head1.next.next = new Node(8);
    head1.next.next.next = new Node(5);
    head1.next.next.next.next = new Node(2);
    head1.next.next.next.next.next = new Node(4);
    head1.next.next.next.next.next.next = new Node(7);

    Node head2 = new Node(5);
    head2.next = new Node(6);
    head2.next.next = new Node(7);
    head2.next.next.next = new Node(8);
    head2.next.next.next.next = new Node(9);
    head2.next.next.next.next.next = new Node(9);
    head2.next.next.next.next.next.next = new Node(5);

    Node newHead = sumTwoLinkedLists(head1, head2);

    System.out.println();
    while (head1 != null) {
      System.out.print(head1.data + "  ");
      head1 = head1.next;
    }

    System.out.println();
    while (head2 != null) {
      System.out.print(head2.data + "  ");
      head2 = head2.next;
    }

    System.out.println();
    while (newHead != null) {
      System.out.print(newHead.data + "  ");
      newHead = newHead.next;
    }
  }
}

