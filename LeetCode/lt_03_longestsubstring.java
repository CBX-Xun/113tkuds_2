import java.util.HashSet;
import java.util.Set;

public class lt_03_longestsubstring {

    // ✅ 主邏輯：找出最長無重複字元子字串長度
    public static int lengthOfLongestSubstring(String s) {
        Set<Character> set = new HashSet<>();
        int left = 0, right = 0, maxLen = 0;

        while (right < s.length()) {
            char c = s.charAt(right);
            if (!set.contains(c)) {
                set.add(c);
                maxLen = Math.max(maxLen, right - left + 1);
                right++;
            } else {
                set.remove(s.charAt(left));
                left++;
            }
        }

        return maxLen;
    }

    // ✅ 測試用 main 方法
    public static void main(String[] args) {
        String test1 = "abcabcbb";  // ➜ 3
        String test2 = "bbbbb";     // ➜ 1
        String test3 = "pwwkew";    // ➜ 3
        String test4 = " ";         // ➜ 1
        String test5 = "";          // ➜ 0
        String test6 = "dvdf";      // ➜ 3

        System.out.println("Test1: " + lengthOfLongestSubstring(test1));
        System.out.println("Test2: " + lengthOfLongestSubstring(test2));
        System.out.println("Test3: " + lengthOfLongestSubstring(test3));
        System.out.println("Test4: " + lengthOfLongestSubstring(test4));
        System.out.println("Test5: " + lengthOfLongestSubstring(test5));
        System.out.println("Test6: " + lengthOfLongestSubstring(test6));
    }
}
