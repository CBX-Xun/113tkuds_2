import java.util.*;

public class KthSmallestElement {
    // 方法1：大小為 K 的 Max Heap（保留目前最小的 K 個）
    public static int kthSmallestMaxHeap(int[] arr, int k) {
        PriorityQueue<Integer> max = new PriorityQueue<>(Collections.reverseOrder());
        for (int v : arr) {
            if (max.size() < k) max.offer(v);
            else if (v < max.peek()) { max.poll(); max.offer(v); }
        }
        if (max.size() < k) throw new IllegalArgumentException("k > n");
        return max.peek();
    }

    // 方法2：Min Heap 取 K 次
    public static int kthSmallestMinHeap(int[] arr, int k) {
        PriorityQueue<Integer> min = new PriorityQueue<>();
        for (int v : arr) min.offer(v);
        if (k<1 || k>arr.length) throw new IllegalArgumentException("k out of range");
        int ans = -1;
        for (int i=0;i<k;i++) ans = min.poll();
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(kthSmallestMaxHeap(new int[]{7,10,4,3,20,15},3)); // 7
        System.out.println(kthSmallestMinHeap(new int[]{1},1)); // 1
        System.out.println(kthSmallestMaxHeap(new int[]{3,1,4,1,5,9,2,6},4)); // 3
    }
}
