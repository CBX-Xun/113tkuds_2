public class lt_14_longestcommon {

    // 題目14: Longest Common Prefix
    // 給定一組字串，找出所有字串開頭共有的最長前綴。
    // 若無共同前綴，則回傳空字串 ""。
    public static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";

        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++) {
            while (!strs[i].startsWith(prefix)) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) return "";
            }
        }
        return prefix;
    }

    // ✅ 測試主程式
    public static void main(String[] args) {
        String[] input1 = {"flower", "flow", "flight"};
        String[] input2 = {"dog", "racecar", "car"};

        System.out.println(longestCommonPrefix(input1)); // 輸出: "fl"
        System.out.println(longestCommonPrefix(input2)); // 輸出: ""
    }
}
