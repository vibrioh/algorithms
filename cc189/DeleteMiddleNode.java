package cc189;

/**
 * "given", read conditions carefully
 Delete Middle Node: Implement an algorithm to delete a node in the middle (i.e., any node but
 the first and last node, not necessarily the exact middle) of a singly linked list, given only access to
 that node.
 EXAMPLE
 lnput:the node c from the linked list a->b->c->d->e->f
 Result: nothing is returned, but the new linked list looks like a ->b->d->e->f
 */
public class DeleteMiddleNode {

  public boolean delMid(Node mid) {
    if (mid == null || mid.next == null) {
      return false;
    } else {
      mid.data = mid.next.data;
      mid.next = mid.next.next;
      return true;
    }
  }
}
