public class lt_09_palindrome {

    // 題目 9: Palindrome Number
    static class Solution {
        public boolean isPalindrome(int x) {
            if (x < 0) return false;

            int original = x;
            int reversed = 0;

            while (x != 0) {
                int digit = x % 10;

                // 防止溢位（雖然本題數值範圍安全）
                if (reversed > (Integer.MAX_VALUE - digit) / 10) return false;

                reversed = reversed * 10 + digit;
                x /= 10;
            }

            return original == reversed;
        }
    }

    // ✅ 主程式測試
    public static void main(String[] args) {
        Solution sol = new Solution();

        int[] testCases = {121, -121, 10, 0, 12321};
        for (int x : testCases) {
            boolean result = sol.isPalindrome(x);
            System.out.println("Input: " + x + " → Is Palindrome? " + result);
        }
    }
}
