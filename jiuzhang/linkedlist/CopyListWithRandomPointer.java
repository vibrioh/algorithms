package jiuzhang.linkedlist;

import java.util.HashMap;
import java.util.Map;

// Definition for singly-linked list with a random pointer.
      class RandomListNode {
      int label;
      RandomListNode next, random;
      RandomListNode(int x) { this.label = x; }
  }

public class CopyListWithRandomPointer {
  /**
   * @param head: The head of linked list with a random pointer.
   * @return A new head of a deep copy of the list.
   */
  public RandomListNode copyRandomList(RandomListNode head) {
    if (head == null) {
      return head;
    }

    RandomListNode curr = head;

    Map<RandomListNode, RandomListNode> map = new HashMap<>();
    while (curr != null) {
      map.put(curr, new RandomListNode(curr.label));
      curr = curr.next;
    }

    curr = head;
    while (curr != null) {
      if (curr.next != null) {
        map.get(curr).next = map.get(curr.next);
      }
      if (curr.random == null) {
        map.get(curr).random = null;
      } else {
        map.get(curr).random = map.get(curr.random);
      }
      curr = curr.next;
    }

    return map.get(head);
  }
}
