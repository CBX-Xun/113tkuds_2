import java.util.*;

public class M01_BuildHeap {

    // heapifyDown（遞迴實作）
    private static void heapify(int[] arr, int n, int i, boolean isMax) {
        int extreme = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (isMax) {
            if (left < n && arr[left] > arr[extreme]) extreme = left;
            if (right < n && arr[right] > arr[extreme]) extreme = right;
        } else {
            if (left < n && arr[left] < arr[extreme]) extreme = left;
            if (right < n && arr[right] < arr[extreme]) extreme = right;
        }

        if (extreme != i) {
            int tmp = arr[i];
            arr[i] = arr[extreme];
            arr[extreme] = tmp;
            heapify(arr, n, extreme, isMax);
        }
    }

    // bottom-up build heap
    private static void buildHeap(int[] arr, int n, boolean isMax) {
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i, isMax);
        }
    }

    // 檢查是否為合法 heap（debug 用）
    private static boolean isValidHeap(int[] arr, boolean isMax) {
        int n = arr.length;
        for (int i = 0; i <= (n / 2 - 1); i++) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;
            if (left < n) {
                if (isMax && arr[i] < arr[left]) return false;
                if (!isMax && arr[i] > arr[left]) return false;
            }
            if (right < n) {
                if (isMax && arr[i] < arr[right]) return false;
                if (!isMax && arr[i] > arr[right]) return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNext()) return;
        String type = sc.next();    // "max" 或 "min"
        if (!sc.hasNextInt()) return;
        int n = sc.nextInt();

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            if (sc.hasNextInt()) arr[i] = sc.nextInt();
            else arr[i] = 0;
        }

        boolean isMax = "max".equals(type);
        buildHeap(arr, n, isMax);

        // 只輸出建堆後的陣列（題目要求）
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i]);
            if (i != n - 1) System.out.print(" ");
        }
        System.out.println();

        // --- 以下為 debug 驗證區塊（平常可註解掉）
        // System.err.println("isValidHeap: " + isValidHeap(arr, isMax));
    }
}

/*
 * Time Complexity: O(n)
 * 說明：自底向上建堆（從 n/2 - 1 開始對每個節點做 heapifyDown），整體時間為線性 O(n)。
 */
