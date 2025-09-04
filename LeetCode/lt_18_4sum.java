import java.util.*;

public class lt_18_4sum {

    // 題目主函式：LeetCode 呼叫這個
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums); // 先排序

        int n = nums.length;

        for (int i = 0; i < n - 3; i++) {
            // 跳過重複的 i
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            for (int j = i + 1; j < n - 2; j++) {
                // 跳過重複的 j
                if (j > i + 1 && nums[j] == nums[j - 1]) continue;

                int left = j + 1;
                int right = n - 1;

                while (left < right) {
                    long sum = (long) nums[i] + nums[j] + nums[left] + nums[right]; // 用 long 避免 overflow

                    if (sum == target) {
                        result.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));

                        // 移動並跳過重複值
                        while (left < right && nums[left] == nums[left + 1]) left++;
                        while (left < right && nums[right] == nums[right - 1]) right--;

                        left++;
                        right--;
                    } else if (sum < target) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }

        return result;
    }

    // ✅ 主程式 for 測試用
    public static void main(String[] args) {
        lt_18_4sum solution = new lt_18_4sum();

        int[] nums = {1, 0, -1, 0, -2, 2};
        int target = 0;

        List<List<Integer>> result = solution.fourSum(nums, target);

        System.out.println("四數總和為 target 的組合如下：");
        for (List<Integer> quad : result) {
            System.out.println(quad);
        }
    }

    /*
     🧠 解題說明：

     ✔ 將 nums 排序後，用兩層 for-loop 固定 i 和 j
     ✔ 剩下的兩個數字用雙指針 (left 和 right) 來找，總和符合 target 就加到結果
     ✔ 每次找到解時，跳過重複的值（避免重複組合）
     ✔ 時間複雜度 O(n^3)，空間 O(1)（不含輸出）

     🧪 範例：
     Input:  nums = [1, 0, -1, 0, -2, 2], target = 0
     Output: [[-2,-1,1,2], [-2,0,0,2], [-1,0,0,1]]
    */
}
