package LeetCode;
import java.util.HashMap;
import java.util.Map;
public class lt_01_twosum {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>(); // 用來儲存數字與對應 index
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i]; // 計算還差多少
            if (map.containsKey(complement)) {
                return new int[] { map.get(complement), i }; // 找到符合的就回傳兩個 index
            }
            map.put(nums[i], i); // 否則先記錄下這個數字的位置
        }
        return new int[] {}; // 根據題目保證一定有解，這行實際不會執行
    }
}
