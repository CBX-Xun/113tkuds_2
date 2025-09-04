import java.util.*;

public class lt_20_validparentheses {

    // 題目邏輯：檢查括號是否合法
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {
            // 遇到左括號就放進 stack
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else {
                // 如果遇到右括號，但 stack 是空的 → 不合法
                if (stack.isEmpty()) return false;

                char top = stack.pop();

                // 判斷是否成對匹配
                if ((c == ')' && top != '(') ||
                    (c == ']' && top != '[') ||
                    (c == '}' && top != '{')) {
                    return false;
                }
            }
        }

        // 最後 stack 必須為空才是合法的括號組合
        return stack.isEmpty();
    }

    // ✅ 測試用 main 方法（可在本地執行）
    public static void main(String[] args) {
        lt_20_validparentheses solution = new lt_20_validparentheses();

        String[] tests = {
            "()",       // true
            "()[]{}",   // true
            "(]",       // false
            "([)]",     // false
            "{[]}",     // true
            "(",        // false
            "]",        // false
            ""          // true
        };

        for (String test : tests) {
            System.out.printf("輸入：%s，是否合法？→ %s%n", test, solution.isValid(test));
        }
    }

    /*
     🧠 解題思路：

     使用 Stack（堆疊）來處理括號成對匹配問題。
     1. 左括號就壓入 Stack。
     2. 遇到右括號就從 Stack 中彈出並檢查是否匹配。
     3. 若 Stack 提早為空，或匹配錯誤 → 回傳 false。
     4. 最終 Stack 為空才表示完全合法。

     ✅ 時間複雜度：O(n) — 每個字元最多被 push/pop 一次。
     ✅ 空間複雜度：O(n) — Stack 最壞情況放 n 個左括號。
    */
}
