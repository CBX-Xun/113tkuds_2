public class lt_07_reverse {

    // 題目7: Reverse Integer
    static class Solution {
        public int reverse(int x) {
            int reversed = 0;

            while (x != 0) {
                int digit = x % 10;
                x /= 10;

                // ✅ 檢查是否超出 32-bit 整數範圍
                if (reversed > Integer.MAX_VALUE / 10 ||
                   (reversed == Integer.MAX_VALUE / 10 && digit > 7)) {
                    return 0; // 溢位
                }
                if (reversed < Integer.MIN_VALUE / 10 ||
                   (reversed == Integer.MIN_VALUE / 10 && digit < -8)) {
                    return 0; // 溢位
                }

                reversed = reversed * 10 + digit;
            }

            return reversed;
        }
    }

    // ✅ 主程式入口點
    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.reverse(123));       // ➜ 321
        System.out.println(sol.reverse(-123));      // ➜ -321
        System.out.println(sol.reverse(120));       // ➜ 21
        System.out.println(sol.reverse(1534236469)); // ➜ 0 (溢位)
    }
}
