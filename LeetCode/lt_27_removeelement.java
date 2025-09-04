public class lt_27_removeelement {
    static class Solution {
        /**
         * 移除陣列中等於 val 的元素，並返回剩下不等於 val 的元素數量。
         * 
         * @param nums 原始整數陣列（已排序 or 未排序皆可）
         * @param val 要移除的目標值
         * @return 移除後留下來元素的數量 k（前 k 個為保留值）
         */
        public int removeElement(int[] nums, int val) {
            int k = 0; // 用來放置保留值的位置索引
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] != val) {
                    nums[k] = nums[i]; // 將非 val 的元素前移
                    k++; // 更新保留區長度
                }
            }
            return k; // 返回剩下的元素個數
        }
    }

    /**
     * 範例測試：
     * Input: nums = [3,2,2,3], val = 3
     * Output: k = 2, nums => [2,2]
     */
    public static void main(String[] args) {
        int[] nums = {3, 2, 2, 3};
        int val = 3;

        Solution sol = new Solution();
        int k = sol.removeElement(nums, val);

        System.out.println("保留元素數量 k = " + k);
        System.out.print("保留的元素為：");
        for (int i = 0; i < k; i++) {
            System.out.print(nums[i] + " ");
        }
    }
}
