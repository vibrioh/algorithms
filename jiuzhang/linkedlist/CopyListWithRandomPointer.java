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

  /*第一遍扫的时候巧妙运用next指针， 开始数组是1->2->3->4  。 然后扫描过程中 先建立copy节点 1->1`->2->2`->3->3`->4->4`, 然后第二遍copy的时候去建立边的copy， 拆分节点, 一边扫描一边拆成两个链表，这里用到两个dummy node。第一个链表变回  1->2->3 , 然后第二变成 1`->2`->3`  */
//No HashMap version
  public class Solution {

    private void copyNext(RandomListNode head) {
      while (head != null) {
        RandomListNode newNode = new RandomListNode(head.label);
        newNode.random = head.random;
        newNode.next = head.next;
        head.next = newNode;
        head = head.next.next;
      }
    }

    private void copyRandom(RandomListNode head) {
      while (head != null) {
        if (head.next.random != null) {
          head.next.random = head.random.next;
        }
        head = head.next.next;
      }
    }

    private RandomListNode splitList(RandomListNode head) {
      RandomListNode newHead = head.next;
      while (head != null) {
        RandomListNode temp = head.next;
        head.next = temp.next;
        head = head.next;
        if (temp.next != null) {
          temp.next = temp.next.next;
        }
      }
      return newHead;
    }

    public RandomListNode copyRandomList(RandomListNode head) {
      if (head == null) {
        return null;
      }
      copyNext(head);
      copyRandom(head);
      return splitList(head);
    }

  }
}
