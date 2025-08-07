public class MatrixCalculator {
    public static void main(String[] args) {
        int[][] A = {
            {1, 2},
            {3, 4}
        };
        int[][] B = {
            {5, 6},
            {7, 8}
        };

        System.out.println("🔢 矩陣加法：");
        printMatrix(add(A, B));

        System.out.println("🔢 矩陣乘法：");
        printMatrix(multiply(A, B));

        System.out.println("🔁 矩陣 A 轉置：");
        printMatrix(transpose(A));

        System.out.println("📈 A 的最大值與最小值：");
        int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
        for (int[] row : A) {
            for (int val : row) {
                max = Math.max(max, val);
                min = Math.min(min, val);
            }
        }
        System.out.println("最大值：" + max + "，最小值：" + min);
    }

    static int[][] add(int[][] a, int[][] b) {
        int[][] result = new int[a.length][a[0].length];
        for (int i = 0; i < a.length; i++)
            for (int j = 0; j < a[0].length; j++)
                result[i][j] = a[i][j] + b[i][j];
        return result;
    }

    static int[][] multiply(int[][] a, int[][] b) {
        int[][] result = new int[a.length][b[0].length];
        for (int i = 0; i < a.length; i++)
            for (int j = 0; j < b[0].length; j++)
                for (int k = 0; k < b.length; k++)
                    result[i][j] += a[i][k] * b[k][j];
        return result;
    }

    static int[][] transpose(int[][] m) {
        int[][] t = new int[m[0].length][m.length];
        for (int i = 0; i < m.length; i++)
            for (int j = 0; j < m[0].length; j++)
                t[j][i] = m[i][j];
        return t;
    }

    static void printMatrix(int[][] m) {
        for (int[] row : m) {
            for (int val : row)
                System.out.print(val + "\t");
            System.out.println();
        }
    }
}
