
public class lt_12_integer {
    /**
     * 題目：LeetCode 12. Integer to Roman
     * 解題邏輯：
     * - 建立一組對照表（從大到小排列），將對應的數字與羅馬字串成對放入陣列中。
     * - 逐一比對 num 是否 >= 對照表中的數字。
     * - 如果是，就減掉該值並加上對應 Roman 符號（可重複使用）。
     * 
     * 時間複雜度：O(1)（因為數值最大為 3999，對照表固定）
     * 空間複雜度：O(1)
     */
    public static String intToRoman(int num) {
        int[] values =    {1000, 900, 500, 400, 100, 90,  50,  40,  10,  9,   5,   4,   1};
        String[] symbols ={"M",  "CM","D", "CD","C", "XC","L", "XL","X", "IX","V", "IV","I"};

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < values.length && num > 0; i++) {
            while (num >= values[i]) {
                sb.append(symbols[i]);
                num -= values[i];
            }
        }

        return sb.toString();
    }

    // ✅ 測試用 main 方法
    public static void main(String[] args) {
        System.out.println(intToRoman(3));     // III
        System.out.println(intToRoman(4));     // IV
        System.out.println(intToRoman(9));     // IX
        System.out.println(intToRoman(58));    // LVIII
        System.out.println(intToRoman(1994));  // MCMXCIV
        System.out.println(intToRoman(3749));  // MMMDCCXLIX
    }
}