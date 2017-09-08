package leetcode;

public class SumOfTwoIntegers {

    public int sumOfTwoIntegers(int a, int b) {
        return b == 0 ? a : sumOfTwoIntegers(a ^ b, (a & b) << 1);
    }
}
