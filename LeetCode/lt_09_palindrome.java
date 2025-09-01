package LeetCode;

public class lt_09_palindrome {

    public static boolean isPalindrome(int x) {
        // 負數不是回文；數字尾數為 0 也不是（除非是 0 本身）
        if (x < 0 || (x % 10 == 0 && x != 0)) return false;

        int reversed = 0;

        // 只反轉一半就能判斷
        while (x > reversed) {
            int digit = x % 10;
            reversed = reversed * 10 + digit;
            x /= 10;
        }

        // 偶數位：x == reversed；奇數位：x == reversed/10（忽略中間數字）
        return x == reversed || x == reversed / 10;
    }

    // 🧪 測試用 main 方法
    public static void main(String[] args) {
        System.out.println(isPalindrome(121));      // true
        System.out.println(isPalindrome(-121));     // false
        System.out.println(isPalindrome(10));       // false
        System.out.println(isPalindrome(12321));    // true
        System.out.println(isPalindrome(0));        // true
    }
}
