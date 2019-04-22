package fb;

public class ProductOfArrayExceptSelf {
    /*
    prefixProduct: 01234N----
    SuffixProduct: ----N56789  // calculate on the fly for O(1) space
    so p[i] * s[i] = product[i] without i;
     */
    public int[] productExceptSelf(int[] nums) {
        if (nums == null) {
            return null;
        }
        int len = nums.length;
        int[] res = new int[len];  // prefixProdcut without itself
        res[0] = 1;
        for (int i = 1; i < len; i++) {
            res[i] = res[i - 1] * nums[i - 1];
        }

        int suffixProduct = nums[len - 1]; // surfixProdcut without itself
        for (int i = len - 2; i >= 0; i--) {
            res[i] *= suffixProduct;
            suffixProduct *= nums[i];
        }

        return res;
    }
}
