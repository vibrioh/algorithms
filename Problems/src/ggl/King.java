package ggl;

import java.util.*;

/*
void birth(String parent, String name) 父亲名字和孩子名字，生个娃
void death(String name) 此人要死
List<String> getOrder() 返回当前的继承顺序，string array/list

讨论得知，每个人的名字是唯一的，继承顺序符合如下规律:
假设王有大皇子二皇子三皇子，大皇子有长子次子三子，那么继承顺序是王->大皇子->大皇子长子->大皇子次子->大皇子三子->二皇子->三皇子
死掉的人不能出现在继承顺序里，但是如果上面例子中大皇子死了，只需把大皇子移除，原始继承顺序保持不变：王->大皇子长子->大皇子次子->大皇子三子->二皇子->三皇子

三个function会被反复调用，实现function细节。
思路：看起来不难的设计题，DFS只查最左枝


 */
public class King {

    Map<String, List<String>> tree = new HashMap<>();
    Set<String> dead = new HashSet<>();
    String root = "king";

    {
        tree.put("king", new ArrayList<>());
    }

    public void birth(String parent, String name) {
        if (!tree.containsKey(parent)) {
            // throw exception
        } else {
            tree.get(parent).add(name);
            tree.put(name, new ArrayList<>());
        }
    }

    public void death(String name) {
        dead.add(name);
    }

    /*
    Zelong Qiu: 这个方程只是lazy delete会不会导致dead越存越大？面试官会不会问如果需要实际删除发方法
根据看到的面经，还没有人被遇到过这个问题，如果有人遇到需要实际删除的问题，欢迎提供思路和代码

Provider: Yang Qi
对于动态删，提供一个思路，用一个map记录子节点到父节点的映射，如果当前节点挂掉，把自己的子节点插入的父节点自身对应的位置之后

     */

    public List<String> getOrder() {
        List<String> res = new ArrayList<>();
        dfs(root, res);
        return res;
    }

    private void dfs(String curr, List<String> res) {
        if (!dead.contains(curr)) {
            res.add(curr);
        }
        for (String child : tree.get(curr)) {
            dfs(child, res);
        }
    }


}
