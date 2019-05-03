package ggl;

import java.util.Map;
import java.util.TreeMap;

/*

使用Java中的TreeMap用于高效查找当前index之后的相对的最小值和最大值
定义两个boolean数组记录当前index是否可以奇数跳以及偶数眺到达末尾
逆推的思维找到有效的起始索引

 */

public class OddEvenJump {
    public int oddEvenJumps(int[] A) {
        int res = 1, lenA = A.length;

        // higher[i] = true代表从索引 i 奇数跳可以到达末尾
        boolean[] higher = new boolean[lenA];
        // lower[i] = true代表从索引 i 偶数跳可以到达末尾
        boolean[] lower = new boolean[lenA];
        // 初始化最后一个位置，无论哪种跳法都是已经在末尾了
        higher[lenA - 1] = lower[lenA - 1] = true;
        // 记录 value - index
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        treeMap.put(A[lenA - 1], lenA - 1);
        for (int i = lenA - 2; i >= 0; i--) {  // 逆推，分两种情况
            // 找到index = i 之后大于等于A[i]的最小的数
            Map.Entry high = treeMap.ceilingEntry(A[i]);
            // 找到index = i 之后小于等于A[i]的最大的数
            Map.Entry low = treeMap.floorEntry(A[i]);

            if (high != null) { // 当前位置进行奇数跳取决于目的地的偶数跳是否能到达终点
                higher[i] = lower[(int) high.getValue()];
            }
            if (low != null) {  // 同理
                lower[i] = higher[(int) low.getValue()];
            }
            // 起始点一定是奇数跳
            if (higher[i]) {
                res++;
            }
            treeMap.put(A[i], i);
        }
        return res;
    }
}
