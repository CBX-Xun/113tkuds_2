public class lt_29_dividetwointegers {

    // 題目 29: Divide Two Integers
    static class Solution {
        /**
         * 不使用 *, /, %，將 dividend 除以 divisor，回傳整數結果（向 0 取整）
         * 處理邊界與溢位狀況，使用位移來加速減法除法。
         */
        public int divide(int dividend, int divisor) {
            // 特殊邊界：最小值除以 -1 會 overflow
            if (dividend == Integer.MIN_VALUE && divisor == -1) {
                return Integer.MAX_VALUE;
            }

            // 是否為負數結果（異號為 true）
            boolean negative = (dividend < 0) ^ (divisor < 0);

            // 轉成 long 並取絕對值，避免溢位
            long lDividend = Math.abs((long) dividend);
            long lDivisor = Math.abs((long) divisor);
            int result = 0;

            // 不斷減去 lDivisor 的倍數直到無法繼續
            while (lDividend >= lDivisor) {
                long temp = lDivisor;
                long multiple = 1;

                // 使用位移方式加速除法過程（倍增）
                while (lDividend >= (temp << 1)) {
                    temp <<= 1;
                    multiple <<= 1;
                }

                lDividend -= temp;
                result += multiple;
            }

            return negative ? -result : result;
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        // ✅ 範例 1
        int dividend1 = 10, divisor1 = 3;
        int result1 = sol.divide(dividend1, divisor1);
        System.out.println("Input: dividend = 10, divisor = 3");
        System.out.println("Output: " + result1);  // 預期：3

        // ✅ 範例 2
        int dividend2 = 7, divisor2 = -3;
        int result2 = sol.divide(dividend2, divisor2);
        System.out.println("Input: dividend = 7, divisor = -3");
        System.out.println("Output: " + result2);  // 預期：-2
    }
}
