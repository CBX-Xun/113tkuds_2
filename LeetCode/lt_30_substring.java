import java.util.*;

/*
# 030. Substring with Concatenation of All Words

## 🧠 題目說明
給定一個字串 s 和一個字串陣列 words，words 裡的每個字都長度相同，
請找出 s 中所有起始 index，使得從該 index 開始的子字串是 words 所有單字的排列組合（順序不限）連接而成。

---

## 💡 解題思路
1. 所有單字長度相同 → 可用 sliding window。
2. 利用 HashMap 記錄目標單字的頻率。
3. 用滑動視窗，每次抓一段 `wordLen * wordCount` 長度的子字串進行判斷。
4. 從每個 offset (`0 ~ wordLen-1`) 開始做，避免切字誤差。

---

## 🧪 測資範例
Input: s = "barfoofoobarthefoobarman"
       words = ["bar","foo","the"]
Output: [6, 9, 12]
*/

public class lt_30_substring {

    // ✅ 主程式入口點：用來執行範例測資
    public static void main(String[] args) {
        lt_30_substring solution = new lt_30_substring();

        // 範例輸入
        String s = "barfoofoobarthefoobarman";
        String[] words = {"bar", "foo", "the"};

        // 執行函數並印出結果
        List<Integer> result = solution.findSubstring(s, words);
        System.out.println("✔️ 結果輸出 = " + result);  // 預期輸出：[6, 9, 12]
    }

    /**
     * 解法函式：找出 s 中所有 words 連接起來的起始 index
     * @param s 原始字串
     * @param words 字串陣列（每個字長度相同）
     * @return 所有符合條件的起始索引集合
     */
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        if (s == null || words == null || words.length == 0) return result;

        int wordLen = words[0].length();          // 每個單字長度
        int wordCount = words.length;             // 單字數量
        int totalLen = wordLen * wordCount;       // 總長度 = 所有單字串接起來

        if (s.length() < totalLen) return result;

        // ✅ Step1: 計算每個單字應該出現幾次
        Map<String, Integer> wordFreq = new HashMap<>();
        for (String word : words) {
            wordFreq.put(word, wordFreq.getOrDefault(word, 0) + 1);
        }

        // ✅ Step2: Sliding Window - 每個 offset 都要試一次（避免錯過）
        for (int i = 0; i < wordLen; i++) {
            int left = i;
            int right = i;
            int count = 0;
            Map<String, Integer> currentCount = new HashMap<>();

            while (right + wordLen <= s.length()) {
                String word = s.substring(right, right + wordLen);
                right += wordLen;

                // 如果是有效單字
                if (wordFreq.containsKey(word)) {
                    currentCount.put(word, currentCount.getOrDefault(word, 0) + 1);
                    count++;

                    // 若出現次數太多，縮小左邊界
                    while (currentCount.get(word) > wordFreq.get(word)) {
                        String leftWord = s.substring(left, left + wordLen);
                        currentCount.put(leftWord, currentCount.get(leftWord) - 1);
                        left += wordLen;
                        count--;
                    }

                    // 如果剛好符合長度，加入結果
                    if (count == wordCount) {
                        result.add(left);
                    }

                } else {
                    // 如果這個字不在 words 中，整個視窗 reset
                    currentCount.clear();
                    count = 0;
                    left = right;
                }
            }
        }

        return result;
    }
}
