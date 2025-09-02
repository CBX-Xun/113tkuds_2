import java.util.HashMap;
import java.util.Map;

public class lt_01_twosum {

    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>(); // 用來儲存數字與對應 index
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i]; // 計算還差多少
            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i}; // 找到符合的就回傳兩個 index
            }
            map.put(nums[i], i); // 否則先記錄下這個數字的位置
        }
        return new int[]{}; // 根據題目保證一定有解，這行實際不會執行
    }

    // ✅ 測試主方法
    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        int[] result = twoSum(nums, target);

        for (int i : result) {
            System.out.print(i + " ");
        }
        // 預期輸出：0 1
    }
}
