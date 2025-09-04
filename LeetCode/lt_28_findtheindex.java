public class lt_28_findtheindex {

    public static void main(String[] args) {
        Solution sol = new Solution();
        String haystack = "sadbutsad";
        String needle = "sad";
        int index = sol.strStr(haystack, needle);
        System.out.println("第一次出現位置: " + index); // Output: 0
    }
}

/**
 * 解題類別（需放在最外層供 LeetCode 使用）
 * 功能：回傳 needle 在 haystack 中第一次出現的起始 index，
 * 若沒出現則回傳 -1。
 */
class Solution {
    public int strStr(String haystack, String needle) {
        return haystack.indexOf(needle); // 使用內建方法解決
    }
}
