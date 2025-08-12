import java.util.*;

/**
 * 使用 heap 挑出最多 K 筆獲利交易：
 * 將所有「單調上升區段」計算成一筆交易利潤，放入 max-heap，取前 K 大相加。
 * （對單調上升序列，拆分/合併不會優於整段一次買賣）
 */
public class StockMaximizer {
    public static int maxProfitWithKTransactions(int[] prices, int K) {
        if (prices==null || prices.length<2 || K<=0) return 0;
        PriorityQueue<Integer> max = new PriorityQueue<>(Collections.reverseOrder());
        int n = prices.length;
        int i = 0;
        while (i < n-1) {
            while (i < n-1 && prices[i+1] <= prices[i]) i++; // 找谷
            int buy = prices[i];
            while (i < n-1 && prices[i+1] >= prices[i]) i++; // 找峰
            int sell = prices[i];
            if (sell > buy) max.offer(sell - buy);
            i++;
        }
        int profit = 0;
        while (K-- > 0 && !max.isEmpty()) profit += max.poll();
        return profit;
    }

    public static void main(String[] args) {
        System.out.println(maxProfitWithKTransactions(new int[]{2,4,1}, 2)); // 2
        System.out.println(maxProfitWithKTransactions(new int[]{3,2,6,5,0,3}, 2)); // 7
        System.out.println(maxProfitWithKTransactions(new int[]{1,2,3,4,5}, 2)); // 4
    }
}
