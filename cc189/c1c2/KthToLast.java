package cc189.c1c2;

public class KthToLast {

//  public static Node kthArrayList(Node head, int k) {
//    List<Node> arrlist = new ArrayList<>();
//    while (head != null) {
//      arrlist.add(head);
//      head = head.next;
//    }
//    return (k < arrlist.size()) ? arrlist.get(arrlist.size() - 1 -k) : null;
//  }

  /**
   * think of K-length ruler moving along the linked list, the distance from two end points is K!!!!!
   * time complexity O(N)
   * space complexity O(1)
   * @param head = head of the linked list
   * @param k = the kth from the last, the last node should be 0.
   * @return the kth Node or null(out of bound)
   */
  public static Node kthByDistance(Node head, int k) {
    Node kth = head;
    Node last = head;
    for (int i = 0; i < k; i++) {
      if (last == null) {
        return null;
      }
      last = last.next;
    }
    while (last.next != null) {    // the check point is last.next in my definition(len - 1 - k)!!!!!!!!!!!!
      kth = kth.next;
      last = last.next;
    }
    return kth;
  }



  public static void main(String[] args) {
    Node head = new Node(0);
    head.next = new Node(1);
    head.next.next = new Node(2);
    head.next.next.next = new Node(3);
    head.next.next.next.next = new Node(4);
    head.next.next.next.next.next = new Node(5);
    head.next.next.next.next.next.next = new Node(6);
    System.out.println(kthByDistance(head, 6).data);
  }
}
