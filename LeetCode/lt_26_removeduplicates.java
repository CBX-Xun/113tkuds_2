public class lt_26_removeduplicates {

    // ✅ 題目主函式：移除排序陣列中重複的元素
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 0;

        int i = 0; // i 為唯一元素區的尾端

        for (int j = 1; j < nums.length; j++) {
            if (nums[j] != nums[i]) {
                i++;
                nums[i] = nums[j]; // 將新的唯一值放到前面區域
            }
        }

        return i + 1; // 回傳唯一元素的數量
    }

    // ✅ 測試用 main 方法
    public static void main(String[] args) {
        lt_26_removeduplicates solution = new lt_26_removeduplicates();

        int[] nums = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};

        int k = solution.removeDuplicates(nums);

        System.out.println("唯一元素個數 k = " + k);
        System.out.print("處理後的陣列內容（前 k 個）：");
        for (int i = 0; i < k; i++) {
            System.out.print(nums[i] + " ");
        }
    }

    /*
     🧠 解題說明：

     題目要求就地移除排序陣列中的重複元素，只保留唯一值。
     利用雙指針技巧：
       - i 指向已確認唯一的最後一個位置。
       - j 向後掃描，若遇到新值就移到 i + 1 位置。

     ✅ nums[0...i] 是唯一值序列。
     ✅ 回傳 i + 1 即為唯一元素個數。

     時間複雜度：O(n)
     空間複雜度：O(1)
    */
}
