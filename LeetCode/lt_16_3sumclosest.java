import java.util.Arrays;

public class lt_16_3sumclosest {

    // 題目解法主程式
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int closestSum = nums[0] + nums[1] + nums[2];

        for (int i = 0; i < nums.length - 2; i++) {
            int left = i + 1;
            int right = nums.length - 1;

            while (left < right) {
                int currentSum = nums[i] + nums[left] + nums[right];

                if (Math.abs(currentSum - target) < Math.abs(closestSum - target)) {
                    closestSum = currentSum;
                }

                if (currentSum < target) {
                    left++;
                } else if (currentSum > target) {
                    right--;
                } else {
                    return currentSum;
                }
            }
        }

        return closestSum;
    }

    // ✅ 主程式（main method）用來執行與測試
    public static void main(String[] args) {
        lt_16_3sumclosest solution = new lt_16_3sumclosest();

        int[] nums = {-1, 2, 1, -4};
        int target = 1;

        int result = solution.threeSumClosest(nums, target);
        System.out.println("最接近 target 的三數和為: " + result);
    }

    /*
     🧠 解題思路：
     這題要求找出「最接近 target 的三數和」，我們可以透過排序 + 雙指針解法來降低時間複雜度。

     1️⃣ 先對陣列排序，讓我們能有效控制總和變化。
     2️⃣ 固定一個數（index i），接著在右邊用雙指針（left, right）掃描剩下兩個數。
     3️⃣ 如果目前的三數和更接近 target，就更新答案。
     4️⃣ 若總和太小，代表需要更大的數（left++）；若總和太大，則需要更小的數（right--）。
     5️⃣ 若剛好等於 target，就可以直接回傳了。

     ✅ 時間複雜度：O(n^2)，排序 O(n log n) + 雙指針 O(n²)
     ✅ 空間複雜度：O(1)，使用常數額外空間
    */
}
