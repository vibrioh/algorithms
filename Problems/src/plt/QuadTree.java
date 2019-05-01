package plt;

import java.util.ArrayList;
import java.util.List;

public class QuadTree {
    final int CAPACITY = 4;
    int level = 0;
    Bound bound;
    List<Node> nodes;
    QuadTree NW;
    QuadTree NE;
    QuadTree SW;
    QuadTree SE;

    QuadTree(int _level, Bound _bound) {
        level = _level;
        bound = _bound;
        nodes = new ArrayList<Node>();
    }

    public boolean split() {
        double xc = (bound.x1 - bound.x0) / 2.0 + bound.x0;
        double yc = (bound.y1 - bound.y0) / 2.0 + bound.y0;
        if (xc == bound.x0 || yc == bound.y0) {
            return false;
        }
        NW = new QuadTree(level + 1, new Bound(bound.x0, xc, yc, bound.y1));
        NE = new QuadTree(level + 1, new Bound(xc, bound.x1, yc, bound.y1));
        SW = new QuadTree(level + 1, new Bound(bound.x0, xc, bound.y0, yc));
        SE = new QuadTree(level + 1, new Bound(xc, bound.x1, bound.y0, yc));
        return true;
    }

    public boolean insert(Node node) {
        if (!bound.isInbound(node.x, node.y)) {
            return false;
        }
        if (nodes.size() < CAPACITY) {
            nodes.add(node);
            return true;
        }
        if (!split()) {
            return false;
        }
        return NW.insert(node) || NE.insert(node) || SW.insert(node) || SE.insert(node);
    }

    public List<Node> query(Bound _bound) {
        List<Node> res = new ArrayList<>();
        if (!bound.isOverlap(_bound)) {
            return res;
        }
        for (Node node : nodes) {
            if (_bound.isInbound(node.x, node.y)) {
                res.add(node);
            }
        }
        if (NW == null) {
            return res;
        }
        res.addAll(NW.query(_bound));
        res.addAll(NE.query(_bound));
        res.addAll(SW.query(_bound));
        res.addAll(SE.query(_bound));
        return res;
    }

}

class Node {
    double x;
    double y;
    int val;

    public Node(double _x, double _y, int _val) {
        x = _x;
        y = _y;
        val = _val;
    }
}

class Bound {
    double x0;
    double x1;
    double y0;
    double y1;

    public Bound(double _x0, double _x1, double _y0, double _y1) {
        x0 = _x0;
        x1 = _x1;
        y0 = _y0;
        y1 = _y1;
    }

    public boolean isInbound(double x, double y) {
        return x >= x0 && y >= y0 && x <= x1 && y <= y1;
    }

    public boolean isOverlap(Bound bound) {
        // left or right of this, up or down of this
        /*
        ____
        |   |  ___
        |___|  |  |
               |__|
         */
        return !(x0 > bound.x1 || x1 < bound.x0 || y0 > bound.y0 || y1 < bound.y1);
    }

//    public Bound overlap(Bound bound) {
//        return new Bound(Math.max(x0, bound.x0), Math.min(x1, bound.x1), Math.max(y0, bound.y0), Math.min(y1, bound.y1));
//    }
}