

public class lt_07_reverse {
    public static int reverse(int x) {
        int result = 0;

        while (x != 0) {
            int digit = x % 10;
            x /= 10;

            // 👉 檢查是否溢位（32-bit 整數）
            if (result > Integer.MAX_VALUE / 10 || (result == Integer.MAX_VALUE / 10 && digit > 7)) {
                return 0;
            }
            if (result < Integer.MIN_VALUE / 10 || (result == Integer.MIN_VALUE / 10 && digit < -8)) {
                return 0;
            }

            result = result * 10 + digit;
        }

        return result;
    }

    // 🧪 測試用 main 方法
    public static void main(String[] args) {
        System.out.println(reverse(123));       // ➜ 321
        System.out.println(reverse(-123));      // ➜ -321
        System.out.println(reverse(120));       // ➜ 21
        System.out.println(reverse(0));         // ➜ 0
        System.out.println(reverse(1534236469));// ➜ 0（因為溢位）
    }
}
