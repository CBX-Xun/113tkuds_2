public class RecursionVsIteration {

    // 遞迴版二項式係數
    public static int binomialRecursive(int n, int k) {
        if (k == 0 || k == n) return 1;
        return binomialRecursive(n - 1, k - 1) + binomialRecursive(n - 1, k);
    }

    // 迭代版
    public static int binomialIterative(int n, int k) {
        int[][] C = new int[n + 1][k + 1];
        for (int i = 0; i <= n; i++)
            for (int j = 0; j <= Math.min(i, k); j++)
                C[i][j] = (j == 0 || j == i) ? 1 : C[i - 1][j - 1] + C[i - 1][j];
        return C[n][k];
    }

    public static int productRecursive(int[] arr, int index) {
        if (index == arr.length) return 1;
        return arr[index] * productRecursive(arr, index + 1);
    }

    public static int productIterative(int[] arr) {
        int prod = 1;
        for (int num : arr) prod *= num;
        return prod;
    }

    public static int countVowelsRecursive(String s, int index) {
        if (index == s.length()) return 0;
        char c = Character.toLowerCase(s.charAt(index));
        return ("aeiou".indexOf(c) >= 0 ? 1 : 0) + countVowelsRecursive(s, index + 1);
    }

    public static int countVowelsIterative(String s) {
        int count = 0;
        for (char c : s.toLowerCase().toCharArray())
            if ("aeiou".indexOf(c) >= 0) count++;
        return count;
    }

    public static boolean isBalanced(String str) {
        int count = 0;
        for (char c : str.toCharArray()) {
            if (c == '(') count++;
            else if (c == ')') count--;
            if (count < 0) return false;
        }
        return count == 0;
    }

    public static void main(String[] args) {
        System.out.println("C(5,2) 遞迴：" + binomialRecursive(5, 2));
        System.out.println("C(5,2) 迭代：" + binomialIterative(5, 2));

        int[] arr = {1, 2, 3, 4};
        System.out.println("乘積 遞迴：" + productRecursive(arr, 0));
        System.out.println("乘積 迭代：" + productIterative(arr));

        String text = "Recursion";
        System.out.println("元音 遞迴：" + countVowelsRecursive(text, 0));
        System.out.println("元音 迭代：" + countVowelsIterative(text));

        System.out.println("括號是否平衡：" + isBalanced("(a(b)c)"));
    }
}
