

import java.util.HashSet;
import java.util.Set;

public class lt_03_longestsubstring {

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
        String test1 = "abcabcbb";
        String test2 = "bbbbb";
        String test3 = "pwwkew";

        System.out.println(lengthOfLongestSubstring(test1)); // 3
        System.out.println(lengthOfLongestSubstring(test2)); // 1
        System.out.println(lengthOfLongestSubstring(test3)); // 3
    }
}
