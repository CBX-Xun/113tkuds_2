import java.util.*;

/**
 * 兩堆 + 懶刪策略：
 * - maxHeap 裝較小的一半（頂為較小半的最大值）
 * - minHeap 裝較大的一半（頂為較大半的最小值）
 * 用 delayed 記錄待刪數量；必要時從堆頂清理。
 */
public class SlidingWindowMedian {
    private PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
    private PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    private Map<Integer,Integer> delayed = new HashMap<>();
    private int maxSize = 0, minSize = 0;

    private void prune(PriorityQueue<Integer> heap) {
        while (!heap.isEmpty()) {
            int x = heap.peek();
            int cnt = delayed.getOrDefault(x, 0);
            if (cnt == 0) break;
            delayed.put(x, cnt-1);
            if (cnt-1 == 0) delayed.remove(x);
            heap.poll();
        }
    }

    private void balance() {
        if (maxSize > minSize + 1) {
            minHeap.offer(maxHeap.poll()); maxSize--; minSize++;
            prune(maxHeap);
        } else if (maxSize < minSize) {
            maxHeap.offer(minHeap.poll()); minSize--; maxSize++;
            prune(minHeap);
        }
    }

    private void addNum(int num) {
        if (maxHeap.isEmpty() || num <= maxHeap.peek()) { maxHeap.offer(num); maxSize++; }
        else { minHeap.offer(num); minSize++; }
        balance();
    }

    private void removeNum(int num) {
        // 標記延遲刪除
        delayed.put(num, delayed.getOrDefault(num,0)+1);
        if (!maxHeap.isEmpty() && num <= maxHeap.peek()) { maxSize--; if (num == maxHeap.peek()) prune(maxHeap); }
        else { minSize--; if (!minHeap.isEmpty() && num == minHeap.peek()) prune(minHeap); }
        balance();
    }

    private double getMedian() {
        if (maxSize > minSize) return maxHeap.peek();
        return ((long)maxHeap.peek() + (long)minHeap.peek()) / 2.0;
    }

    public double[] medianSlidingWindow(int[] nums, int k) {
        if (k==0) return new double[0];
        double[] ans = new double[nums.length - k + 1];
        for (int i=0;i<nums.length;i++){
            addNum(nums[i]);
            if (i >= k) removeNum(nums[i-k]);
            if (i >= k-1) ans[i-k+1] = getMedian();
        }
        return ans;
    }

    public static void main(String[] args) {
        SlidingWindowMedian s = new SlidingWindowMedian();
        System.out.println(Arrays.toString(s.medianSlidingWindow(new int[]{1,3,-1,-3,5,3,6,7},3)));
        System.out.println(Arrays.toString(s.medianSlidingWindow(new int[]{1,2,3,4},2)));
    }
}
