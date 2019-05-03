package ggl;

import java.util.Map;

public class FruitIntoBackets {
    //In Java you can simplify if (count.get(tree[i]) == 0) count.remove(tree[i]); -> count.remove(tree[i], 0);
    public int totalFruit(int[] tree) {
        if (tree == null || tree.length == 0) {
            return 0;
        }

        int count = 0;
        Map<Integer, Integer> fruit = new HashMap<>();

        for (int start = 0, end = 0; end < tree.length; end++) {
            fruit.put(tree[end], fruit.getOrDefault(tree[end], 0) + 1);

            // maintain a sliding window
            while (fruit.size() > 2) {
                fruit.put(tree[start], fruit.get(tree[start]) - 1);
                if (fruit.get(tree[start]) == 0) {
                    fruit.remove(tree[start]);
                }
                start++;
            }

            count = Math.max(count, end - start + 1);
        }

        return count;
    }
}
