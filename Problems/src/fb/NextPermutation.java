package fb;

public class NextPermutation {

    /*
    从最后一个位置开始，找到一个上升点，上升点之前的无需改动。
    然后，翻转上升点之后的降序。
    在降序里，找到第一个比上升点大的，交换位置。
     */
    public void reverse(int[] num, int start, int end) {
        for (int i = start, j = end; i < j; i++, j--) {
            int temp = num[i];
            num[i] = num[j];
            num[j] = temp;
        }
    }

    public int[] nextPermutation(int[] num) {
        // find the last increase index
        int index = -1;
        for (int i = num.length - 2; i >= 0; i--) {
            if (num[i] < num[i + 1]) {
                index = i;
                break;
            }
        }
        // 纯降序直接reverse返回
        if (index == -1) {
            reverse(num, 0, num.length - 1);
            return num;
        }

        // find the first bigger one
        int biggerIndex = index + 1;
        for (int i = num.length - 1; i > index; i--) {
            if (num[i] > num[index]) {
                biggerIndex = i;
                break;
            }
        }

        // swap them to make the permutation bigger
        int temp = num[index];
        num[index] = num[biggerIndex];
        num[biggerIndex] = temp;

        // reverse the last part
        reverse(num, index + 1, num.length - 1);
        return num;
    }
}
