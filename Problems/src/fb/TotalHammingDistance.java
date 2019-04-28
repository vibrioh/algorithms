package fb;

/*
记录数组二进制中每一位0和1的个数，个数相乘即该位的汉明距离，将所有位的距离相加即总距离。
For each bit position 1-32 in a 32-bit integer, we count the number of integers in the array which have that bit set. Then, if there are n integers in the array and k of them have a particular bit set and (n-k) do not, then that bit contributes k*(n-k) hamming distance to the total.

 */
public class TotalHammingDistance {
    public int totalHammingDistance(int[] nums) {
        int total = 0, n = nums.length;
        for (int j = 0; j < 32; j++) {
            int bitCount = 0;
            for (int num : nums) {
                bitCount += (num >> j) & 1;
            }
            total += bitCount * (n - bitCount);
        }
        return total;
    }
}
