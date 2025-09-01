package LeetCode;

public class lt_08_string {

    public static int myAtoi(String s) {
        int i = 0, n = s.length();
        int sign = 1, result = 0;

        // 1. 忽略前導空白
        while (i < n && s.charAt(i) == ' ') {
            i++;
        }

        // 2. 處理符號
        if (i < n && (s.charAt(i) == '+' || s.charAt(i) == '-')) {
            sign = (s.charAt(i) == '-') ? -1 : 1;
            i++;
        }

        // 3. 轉換數字並防止溢位
        while (i < n && Character.isDigit(s.charAt(i))) {
            int digit = s.charAt(i) - '0';

            if (result > (Integer.MAX_VALUE - digit) / 10) {
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }

            result = result * 10 + digit;
            i++;
        }

        return result * sign;
    }

    // ✅ 測試用 main 方法
    public static void main(String[] args) {
        System.out.println(myAtoi("42"));             // 42
        System.out.println(myAtoi("   -42"));         // -42
        System.out.println(myAtoi("4193 with words"));// 4193
        System.out.println(myAtoi("words and 987"));  // 0
        System.out.println(myAtoi("-91283472332"));   // -2147483648
        System.out.println(myAtoi("0-1"));            // 0
    }
}
