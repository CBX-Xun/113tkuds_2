public class lt_13_roman {

    // 題目13: Roman to Integer
    public static int romanToInt(String s) {
        int total = 0;
        int prev = getValue(s.charAt(0));

        for (int i = 1; i < s.length(); i++) {
            int curr = getValue(s.charAt(i));
            if (prev < curr) {
                total -= prev;
            } else {
                total += prev;
            }
            prev = curr;
        }
        total += prev;
        return total;
    }

    private static int getValue(char c) {
        return switch (c) {
            case 'I' -> 1;
            case 'V' -> 5;
            case 'X' -> 10;
            case 'L' -> 50;
            case 'C' -> 100;
            case 'D' -> 500;
            case 'M' -> 1000;
            default -> 0;
        };
    }

    // ✅ 測試 main 方法（可選）
    public static void main(String[] args) {
        System.out.println(romanToInt("III"));     // 3
        System.out.println(romanToInt("LVIII"));   // 58
        System.out.println(romanToInt("MCMXCIV")); // 1994
    }
}
