import java.util.*;

public class lt_17_lettercombinations {
    // 主函式，LeetCode 會呼叫這個
    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();

        // 如果輸入是空字串，直接回傳空 list
        if (digits == null || digits.length() == 0) return result;

        // 數字對應字母表（就像手機按鍵）
        String[] phoneMap = {
            "",     // 0
            "",     // 1
            "abc",  // 2
            "def",  // 3
            "ghi",  // 4
            "jkl",  // 5
            "mno",  // 6
            "pqrs", // 7
            "tuv",  // 8
            "wxyz"  // 9
        };

        // 遞迴回溯（Backtracking）
        backtrack(digits, 0, new StringBuilder(), phoneMap, result);

        return result;
    }

    // 遞迴輔助函式
    private void backtrack(String digits, int index, StringBuilder current, String[] phoneMap, List<String> result) {
        if (index == digits.length()) {
            result.add(current.toString());
            return;
        }

        char digit = digits.charAt(index);
        String letters = phoneMap[digit - '0'];

        for (char c : letters.toCharArray()) {
            current.append(c);
            backtrack(digits, index + 1, current, phoneMap, result);
            current.deleteCharAt(current.length() - 1);
        }
    }

    // ✅ 本機測試用 main 方法
    public static void main(String[] args) {
        lt_17_lettercombinations sol = new lt_17_lettercombinations();

        String input = "23";
        List<String> result = sol.letterCombinations(input);

        System.out.println("輸入: " + input);
        System.out.println("所有字母組合: " + result);
    }
}
