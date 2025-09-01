package LeetCode;

public class lt_05_longestpalindromic {

    public static String longestPalindrome(String s) {
        if (s == null || s.length() < 1) return "";

        int start = 0, end = 0;

        for (int i = 0; i < s.length(); i++) {
            int len1 = expandFromCenter(s, i, i);       // 奇數中心
            int len2 = expandFromCenter(s, i, i + 1);   // 偶數中心
            int len = Math.max(len1, len2);

            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }

        return s.substring(start, end + 1);
    }

    private static int expandFromCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
    }

    // 🧪 測試用 main 方法
    public static void main(String[] args) {
        String input1 = "babad";
        String input2 = "cbbd";
        String input3 = "a";

        System.out.println("Longest palindrome in \"" + input1 + "\": " + longestPalindrome(input1)); // bab or aba
        System.out.println("Longest palindrome in \"" + input2 + "\": " + longestPalindrome(input2)); // bb
        System.out.println("Longest palindrome in \"" + input3 + "\": " + longestPalindrome(input3)); // a
    }
}
