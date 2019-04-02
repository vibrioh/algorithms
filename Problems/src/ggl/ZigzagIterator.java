package ggl;

import java.util.*;

public class ZigzagIterator {
    LinkedList<Iterator> list;

    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
        list = new LinkedList<Iterator>();
        if (!v1.isEmpty()) list.add(v1.iterator());
        if (!v2.isEmpty()) list.add(v2.iterator());
    }

    public int next() {
        Iterator poll = list.remove();
        int result = (Integer) poll.next();
        if (poll.hasNext()) list.add(poll);
        return result;
    }

    public boolean hasNext() {
        return !list.isEmpty();
    }
}


class ZigzagIteratorNoIt {

    List<List<Integer>> list;
    Queue<Integer> buffer;
    int i;

    // 2 vectors
    public ZigzagIteratorNoIt(List<Integer> v1, List<Integer> v2) {
        this(new ArrayList<>());
        this.list.add(v1);
        this.list.add(v2);

    }

    // K vectors
    public ZigzagIteratorNoIt(List<List<Integer>> list) {
        this.list = list;
        this.buffer = new LinkedList<>();
        this.i = 0;
    }

    public int next() {
        if (!hasNext()) return -1;
        if (!buffer.isEmpty()) return buffer.poll();

        // if buffer is empty, add i-th item from each list
        for (List<Integer> l : list) {
            if (i < l.size()) buffer.add(l.get(i));
        }
        i++;

        return next();
    }

    public boolean hasNext() {
        if (!buffer.isEmpty()) return true;

        for (List<Integer> l : list) {
            if (i < l.size()) return true;
        }

        return false;
    }
}
