import java.util.*;

public class lt_23_mergeksortedlists {

    // ✅ ListNode 類別定義（題目預設）
    static class ListNode {
        int val;
        ListNode next;

        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    // ✅ 題目主解法：合併 k 條排序的 LinkedList
    public ListNode mergeKLists(ListNode[] lists) {
        // 使用 PriorityQueue 最小堆，根據節點值排序
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>(
            (a, b) -> Integer.compare(a.val, b.val)
        );

        // 將所有鏈結串列的頭節點加入堆中（非 null）
        for (ListNode node : lists) {
            if (node != null) {
                minHeap.offer(node);
            }
        }

        // 使用 dummy node 組合最終結果
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;

        while (!minHeap.isEmpty()) {
            ListNode node = minHeap.poll(); // 取出最小節點
            current.next = node;            // 加入結果串列
            current = current.next;

            if (node.next != null) {
                minHeap.offer(node.next);   // 下一個節點加入堆中
            }
        }

        return dummy.next;
    }

    // ✅ 測試用 main 方法
    public static void main(String[] args) {
        lt_23_mergeksortedlists solution = new lt_23_mergeksortedlists();

        // 建立測試資料：[[1,4,5],[1,3,4],[2,6]]
        ListNode l1 = new ListNode(1, new ListNode(4, new ListNode(5)));
        ListNode l2 = new ListNode(1, new ListNode(3, new ListNode(4)));
        ListNode l3 = new ListNode(2, new ListNode(6));
        ListNode[] lists = new ListNode[] { l1, l2, l3 };

        ListNode result = solution.mergeKLists(lists);

        // 印出合併後的結果：1 → 1 → 2 → 3 → 4 → 4 → 5 → 6
        System.out.print("合併後的結果：");
        while (result != null) {
            System.out.print(result.val + (result.next != null ? " → " : ""));
            result = result.next;
        }
    }

    /*
     🧠 解題說明：

     使用最小堆（PriorityQueue）將 k 條已排序鏈結串列合併。
     每次從堆中取出當前最小節點，接到結果串列後，再將該節點的下一個加入堆中。

     ✅ 時間複雜度：O(N log k)，N 為總節點數，k 為串列數。
     ✅ 空間複雜度：O(k)，PriorityQueue 最多存放 k 個節點。
    */
}
