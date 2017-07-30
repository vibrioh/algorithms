package cc189;

import java.util.HashSet;
import java.util.Set;

/**
 * remove Node duplications of singly unsorted linked list
 */
public class RemoveDups {

  /**
   * using a HashSet to buffer unique node data
   * time complexity O(N)
   * @param head = head of a linked list
   */
  public static void removeDupsHashSet(Node head){
    Set<Integer> set = new HashSet<>();
    Node curr = head;    // set head is OK, it will be head.next after one round
    Node prev = null;    // set head is OK, it will be head after one round
    while (curr != null) {
      if (set.contains(curr.data)) {
        prev.next = curr.next;
      } else {
        set.add(curr.data);
        prev = curr;
      }
      curr = curr.next;
    }
  }

  /**
   * two pointers move along the list
   * time complexity O(N^2)
   * space O(1)
   */
  public static void removeDupsNoBuff(Node head) {
    Node shift = head;
    while (shift != null) {
      Node curr = shift.next;
      Node prev = shift;
      while (curr != null) {
        if (curr.data == shift.data) {
          prev.next = curr.next;
        } else {
          prev = curr;
        }
        curr = curr.next;
      }
      shift = shift.next;
    }
  }










  /* Function to print Nodes in a given linked list */
  static void printList(Node head)
  {
    while (head != null)
    {
      System.out.print(head.data + " ");
      head = head.next;
    }
  }

  public static void main(String[] args)
  {
        /* The constructed linked list is:
         10->12->11->11->12->11->10*/
    Node start = new Node(10);
    start.next = new Node(12);
    start.next.next = new Node(11);
    start.next.next.next = new Node(11);
    start.next.next.next.next = new Node(12);
    start.next.next.next.next.next = new Node(11);
    start.next.next.next.next.next.next = new Node(10);

    System.out.println("Linked list before removing duplicates :");
    printList(start);

    removeDupsNoBuff(start);

    System.out.println("\nLinked list after removing duplicates :");
    printList(start);
  }
}
 

