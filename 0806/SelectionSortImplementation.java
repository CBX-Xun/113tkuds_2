public class SelectionSortImplementation {
    public static void main(String[] args) {
        int[] arr = {64, 25, 12, 22, 11};
        int comparisons = 0, swaps = 0;

        System.out.println("原始陣列：");
        printArray(arr);

        for (int i = 0; i < arr.length - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < arr.length; j++) {
                comparisons++;
                if (arr[j] < arr[minIdx]) minIdx = j;
            }
            if (minIdx != i) {
                int temp = arr[i];
                arr[i] = arr[minIdx];
                arr[minIdx] = temp;
                swaps++;
            }
            System.out.print("第 " + (i + 1) + " 輪排序結果：");
            printArray(arr);
        }

        System.out.println("比較次數：" + comparisons);
        System.out.println("交換次數：" + swaps);
    }

    static void printArray(int[] arr) {
        for (int num : arr)
            System.out.print(num + " ");
        System.out.println();
    }
}
