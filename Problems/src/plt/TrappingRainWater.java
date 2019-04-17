package plt;

public class TrappingRainWater {
    public int trap(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int res = 0;
        int left = 0;
        int right = height.length - 1;
        int curr = 0;
        while (left < right) {
            if (height[left] <= height[right]) {
                if (curr < height[left]) {
                    curr = height[left];
                }
                if (curr > height[left]) {
                    res += curr - height[left];
                }
                left++;
            } else {
                if (curr < height[right]) {
                    curr = height[right];
                }
                if (curr > height[right]) {
                    res += curr - height[right];
                }
                right--;
            }
        }
        return res;
    }
}
