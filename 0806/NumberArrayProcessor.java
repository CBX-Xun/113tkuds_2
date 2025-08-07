import java.util.*;

public class NumberArrayProcessor {
    public static void main(String[] args) {
        int[] arr = {1, 2, 2, 3, 4, 4, 4, 5};
        int[] sorted1 = {1, 3, 5};
        int[] sorted2 = {2, 4, 6};

        System.out.println("✅ 移除重複元素：" + Arrays.toString(removeDuplicates(arr)));
        System.out.println("✅ 合併排序陣列：" + Arrays.toString(mergeSorted(sorted1, sorted2)));
        System.out.println("✅ 出現最頻繁的元素：" + findMostFrequent(arr));
        System.out.println("✅ 分割成兩半：" + Arrays.toString(splitArray(arr)));
    }

    static int[] removeDuplicates(int[] arr) {
        return Arrays.stream(arr).distinct().toArray();
    }

    static int[] mergeSorted(int[] a, int[] b) {
        int[] result = new int[a.length + b.length];
        int i = 0, j = 0, k = 0;
        while (i < a.length && j < b.length)
            result[k++] = a[i] < b[j] ? a[i++] : b[j++];
        while (i < a.length) result[k++] = a[i++];
        while (j < b.length) result[k++] = b[j++];
        return result;
    }

    static int findMostFrequent(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : arr)
            map.put(num, map.getOrDefault(num, 0) + 1);
        return Collections.max(map.entrySet(), Map.Entry.comparingByValue()).getKey();
    }

    static int[] splitArray(int[] arr) {
        int mid = arr.length / 2;
        int[] left = Arrays.copyOfRange(arr, 0, mid);
        int[] right = Arrays.copyOfRange(arr, mid, arr.length);
        System.out.println("Left: " + Arrays.toString(left));
        System.out.println("Right: " + Arrays.toString(right));
        return new int[] { left.length, right.length };
    }
}
