
public class lt_11_container {

    /**
     * 題目：LeetCode 11. Container With Most Water
     * 解題邏輯：
     * - 使用「雙指標」從兩側往內掃描，尋找最大容積。
     * - 每次面積 = min(height[left], height[right]) * (right - left)
     * - 為了嘗試更大的容積，要移動「比較短」的那一邊，因為短板會限制裝水量。
     * 
     * 時間複雜度：O(n)
     * 空間複雜度：O(1)
     */
    public static int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int maxArea = 0;

        while (left < right) {
            int h = Math.min(height[left], height[right]);
            int w = right - left;
            int area = h * w;
            maxArea = Math.max(maxArea, area);

            if (height[left] < height[right]) {
                left++; // 嘗試換更高的左邊牆
            } else {
                right--; // 嘗試換更高的右邊牆
            }
        }

        return maxArea;
    }

    // 🧪 測試 main 方法
    public static void main(String[] args) {
        int[] height1 = {1,8,6,2,5,4,8,3,7};
        int[] height2 = {1,1};
        int[] height3 = {4,3,2,1,4};
        int[] height4 = {1,2,1};

        System.out.println(maxArea(height1)); // ➜ 49
        System.out.println(maxArea(height2)); // ➜ 1
        System.out.println(maxArea(height3)); // ➜ 16
        System.out.println(maxArea(height4)); // ➜ 2
    }
}
