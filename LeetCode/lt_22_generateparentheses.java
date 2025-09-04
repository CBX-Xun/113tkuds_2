import java.util.*;

public class lt_22_generateparentheses {

    // ✅ 題目主函式：產生所有合法括號組合
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        backtrack(result, "", 0, 0, n);
        return result;
    }

    // ✅ 回溯函式：用來遞迴產生所有合法組合
    private void backtrack(List<String> result, String current, int open, int close, int max) {
        // 當長度等於 2*n，表示是一組完成的括號組合
        if (current.length() == max * 2) {
            result.add(current);
            return;
        }

        // 可以加入左括號
        if (open < max) {
            backtrack(result, current + "(", open + 1, close, max);
        }

        // 可以加入右括號（前提是右括號數量 < 左括號）
        if (close < open) {
            backtrack(result, current + ")", open, close + 1, max);
        }
    }

    // ✅ 本地測試用 main 方法
    public static void main(String[] args) {
        lt_22_generateparentheses solution = new lt_22_generateparentheses();

        int n = 3; // 測試用 n = 3
        List<String> results = solution.generateParenthesis(n);

        System.out.println("輸入 n = " + n);
        System.out.println("合法括號組合：");
        for (String s : results) {
            System.out.println(s);
        }
    }

    /*
     🧠 解題說明：

     題目要我們產生所有 n 對合法括號的組合，我們用回溯法（Backtracking）來遞迴構造所有可能解：
     
     ✅ 當 open < n 時，我們可以加入左括號 '('
     ✅ 當 close < open 時，我們可以加入右括號 ')'
     ✅ 當 current 長度達到 2*n 時，就表示構造完成一組合法括號，加入結果

     時間複雜度：O(2^n)，實際為第 n 個卡特蘭數 Catalan(n)
     空間複雜度：O(n)（遞迴深度與目前組合長度）
    */
}
