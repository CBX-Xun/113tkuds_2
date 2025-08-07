import java.util.*;

public class AdvancedArrayRecursion {

    public static void quickSort(int[] arr, int low, int high) {
        if (low >= high) return;
        int pivot = arr[low + (high - low) / 2];
        int i = low, j = high;
        while (i <= j) {
            while (arr[i] < pivot) i++;
            while (arr[j] > pivot) j--;
            if (i <= j) {
                int t = arr[i]; arr[i] = arr[j]; arr[j] = t;
                i++; j--;
            }
        }
        if (low < j) quickSort(arr, low, j);
        if (high > i) quickSort(arr, i, high);
    }

    public static int[] merge(int[] a, int[] b, int i, int j, List<Integer> result) {
        if (i == a.length && j == b.length)
            return result.stream().mapToInt(Integer::intValue).toArray();
        if (i < a.length && (j == b.length || a[i] <= b[j])) {
            result.add(a[i]);
            return merge(a, b, i + 1, j, result);
        } else {
            result.add(b[j]);
            return merge(a, b, i, j + 1, result);
        }
    }

    public static int kthSmallest(int[] arr, int k) {
        Arrays.sort(arr);
        return kthHelper(arr, 0, arr.length - 1, k);
    }

    private static int kthHelper(int[] arr, int left, int right, int k) {
        if (left <= right) return arr[left + k - 1];
        return -1;
    }

    public static boolean hasSubsetSum(int[] arr, int target, int index) {
        if (target == 0) return true;
        if (index == arr.length || target < 0) return false;
        return hasSubsetSum(arr, target - arr[index], index + 1) || hasSubsetSum(arr, target, index + 1);
    }

    public static void main(String[] args) {
        int[] arr = {5, 3, 7, 1, 2};
        quickSort(arr, 0, arr.length - 1);
        System.out.println("✅ QuickSort: " + Arrays.toString(arr));

        int[] merged = merge(new int[]{1, 3, 5}, new int[]{2, 4, 6}, 0, 0, new ArrayList<>());
        System.out.println("✅ Merged: " + Arrays.toString(merged));

        System.out.println("✅ 第 3 小的元素：" + kthSmallest(arr, 3));
        System.out.println("✅ 是否存在子集合總和為 9：" + hasSubsetSum(arr, 9, 0));
    }
}
