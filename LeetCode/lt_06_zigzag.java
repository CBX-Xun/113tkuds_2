public class lt_06_zigzag {

    // 題目6: Zigzag Conversion
    static class Solution {
        public String convert(String s, int numRows) {
            // ✅ 特殊情況：直接回傳原字串
            if (numRows == 1 || s.length() <= numRows) return s;

            // ✅ 每一行建立一個 StringBuilder
            StringBuilder[] rows = new StringBuilder[numRows];
            for (int i = 0; i < numRows; i++) {
                rows[i] = new StringBuilder();
            }

            int currentRow = 0;
            boolean goingDown = false;

            for (char c : s.toCharArray()) {
                rows[currentRow].append(c);
                if (currentRow == 0 || currentRow == numRows - 1) {
                    goingDown = !goingDown;
                }
                currentRow += goingDown ? 1 : -1;
            }

            // ✅ 組合所有列的內容
            StringBuilder result = new StringBuilder();
            for (StringBuilder row : rows) {
                result.append(row);
            }

            return result.toString();
        }
    }

    // 🧪 主程式測試（可選）
    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.convert("PAYPALISHIRING", 3)); // 輸出: PAHNAPLSIIGYIR
        System.out.println(sol.convert("PAYPALISHIRING", 4)); // 輸出: PINALSIGYAHRPI
        System.out.println(sol.convert("A", 1));               // 輸出: A
    }
}
