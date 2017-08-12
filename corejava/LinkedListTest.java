package corejava;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**This program demonstrates operations on linked lists.
 * @author vibrioh
 */
public class LinkedListTest {

  public static void main(String[] args) {
    List<String> a = new LinkedList<>();
    a.add("Amy");
    a.add("Carl");
    a.add("Erica");

    List<String> b = new LinkedList<>();
    b.add("Bob");
    b.add("Doug");
    b.add("Frances");
    b.add("Gloria");

    //merge the words for b into a

    ListIterator<String> aIter = a.listIterator();
    Iterator<String> bIter = b.iterator();

    while (bIter.hasNext()) {
      if (aIter.hasNext()) {
        aIter.next();
      }
      aIter.add(bIter.next());
    }

    System.out.println(a);

    // remove every second word form b

    bIter = b.iterator();
    while (bIter.hasNext()) {
      bIter.next(); // skip one element
      if (bIter.hasNext()) {
        bIter.next(); // skp next element
        bIter.remove();
      }
    }

    System.out.println(b);

    // bulk operation: remove all words in b from a

    a.removeAll(b);

    System.out.println(a);
  }
}
