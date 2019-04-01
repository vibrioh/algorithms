package ggl;

import org.w3c.dom.Node;

import java.util.Iterator;
import java.util.List;

/*
More Detail : 给一个tree有红的node有蓝的node，把红的去掉后剩下一堆零零散散的tree， 返回这些tree的node，只要node，不要children，也就是说把这个node的children设置成null然后加到list里。
参数是这个树的root。找到所有的红点然后delete掉，去掉这些红点之后就会把一个tree变成散落的几个tree，然后返回这几个tree的root。直接一个recursive判断一下，如果这个node是红点的话就ignore 掉再去判断这个node的children，如果这个node是蓝点的话，要看这个蓝点的parent是不是个红点，是的话，这个蓝点就是散落的tree中其中一个tree的root。

 */
public class NTreeDelete {
//    private void dfs(Node root, Node parent, List<Node> res) {
//        if (root == null) {
//            return;
//        }
//        if ((parent == null || parent.color == red) && root.color != red) {
//            res.add(root);
//        }
//        for (Node children : root.children) {
//            dfs(child, root, res);
//        }
//    }
//    public void dfsHelp (Node root, List<Node> res) {
//        if (root == null) {
//            return;
//        }
//        if (root.isRed) {
//            for (Node child:root.childrens) {
//                if (!child.isRed) {
//                    res.add(child);
//                }
//                dfsHelp(child, res);
//            }
//        } else {
//            res.add(root);
//            Iterator<Node> it = root.childrens.iterator();
//            while (it.hasNext()) {
//                Node child = it.next();
//                if (child.isRed) {
//                    root.childrens.remove(child);
//                }
//                dfsHelp(child, res);
//            }
//        }
//    }

}
