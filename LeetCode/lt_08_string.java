public class lt_08_string {

    // 題目8: String to Integer (atoi)
    static class Solution {
        public int myAtoi(String s) {
            int i = 0, sign = 1, result = 0;
            int n = s.length();

            // Step 1: Skip leading whitespaces
            while (i < n && s.charAt(i) == ' ') i++;

            // Step 2: Check for sign
            if (i < n && (s.charAt(i) == '+' || s.charAt(i) == '-')) {
                sign = (s.charAt(i) == '-') ? -1 : 1;
                i++;
            }

            // Step 3: Convert digits to integer
            while (i < n && Character.isDigit(s.charAt(i))) {
                int digit = s.charAt(i) - '0';

                // Step 4: Check overflow
                if (result > (Integer.MAX_VALUE - digit) / 10)
                    return (sign == 1) ? Integer.MAX_VALUE : Integer.MIN_VALUE;

                result = result * 10 + digit;
                i++;
            }

            return result * sign;
        }
    }

    // ✅ 主程式測試
    public static void main(String[] args) {
        Solution sol = new Solution();

        System.out.println(sol.myAtoi("42"));           // ➜ 42
        System.out.println(sol.myAtoi("   -42"));        // ➜ -42
        System.out.println(sol.myAtoi("4193 with words"));// ➜ 4193
        System.out.println(sol.myAtoi("words and 987")); // ➜ 0
        System.out.println(sol.myAtoi("-91283472332"));  // ➜ -2147483648 (下限)
    }
}
