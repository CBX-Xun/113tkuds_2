import java.util.*;

public class AdvancedStringRecursion {

    public static void permute(String str, String result) {
        if (str.isEmpty()) {
            System.out.println(result);
            return;
        }
        for (int i = 0; i < str.length(); i++) {
            permute(str.substring(0, i) + str.substring(i + 1), result + str.charAt(i));
        }
    }

    public static boolean match(String text, String pattern) {
        if (pattern.isEmpty()) return text.isEmpty();
        boolean firstMatch = !text.isEmpty() && (pattern.charAt(0) == text.charAt(0));
        return firstMatch && match(text.substring(1), pattern.substring(1));
    }

    public static String removeDuplicates(String str, Set<Character> seen) {
        if (str.isEmpty()) return "";
        char first = str.charAt(0);
        if (seen.contains(first)) return removeDuplicates(str.substring(1), seen);
        seen.add(first);
        return first + removeDuplicates(str.substring(1), seen);
    }

    public static void substrings(String str, String prefix) {
        if (str.isEmpty()) {
            if (!prefix.isEmpty()) System.out.println(prefix);
            return;
        }
        substrings(str.substring(1), prefix + str.charAt(0));
        substrings(str.substring(1), prefix);
    }

    public static void main(String[] args) {
        System.out.println("✅ 字串排列:");
        permute("abc", "");

        System.out.println("✅ 字串匹配: abc vs abc -> " + match("abc", "abc"));
        System.out.println("✅ 移除重複字元: " + removeDuplicates("aabccbd", new HashSet<>()));
        System.out.println("✅ 所有子字串:");
        substrings("abc", "");
    }
}
