public class lt_25_reversenodesinkgroup {

    // ✅ ListNode 類別定義（照題目提供）
    static class ListNode {
        int val;
        ListNode next;

        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    // ✅ 主邏輯：每 k 個節點一組進行反轉
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode groupPrev = dummy;

        while (true) {
            // 找出第 k 個節點（不足 k 則結束）
            ListNode kth = getKthNode(groupPrev, k);
            if (kth == null) break;

            ListNode groupNext = kth.next;

            // 開始反轉區間
            ListNode prev = groupNext;
            ListNode current = groupPrev.next;

            while (current != groupNext) {
                ListNode tmp = current.next;
                current.next = prev;
                prev = current;
                current = tmp;
            }

            // 更新 groupPrev 指向下一組
            ListNode tmp = groupPrev.next;
            groupPrev.next = kth;
            groupPrev = tmp;
        }

        return dummy.next;
    }

    // ✅ 輔助函式：取得從 start 開始的第 k 個節點，若不足 k 個回傳 null
    private ListNode getKthNode(ListNode start, int k) {
        while (start != null && k > 0) {
            start = start.next;
            k--;
        }
        return start;
    }

    // ✅ 測試用 main 方法
    public static void main(String[] args) {
        lt_25_reversenodesinkgroup solution = new lt_25_reversenodesinkgroup();

        // 測試用例 head = [1,2,3,4,5], k = 3
        ListNode head = new ListNode(1,
                            new ListNode(2,
                            new ListNode(3,
                            new ListNode(4,
                            new ListNode(5)))));

        int k = 3;
        ListNode result = solution.reverseKGroup(head, k);

        // 預期輸出：3 → 2 → 1 → 4 → 5
        System.out.print("反轉後的結果：");
        while (result != null) {
            System.out.print(result.val + (result.next != null ? " → " : ""));
            result = result.next;
        }
    }

    /*
     🧠 解題說明：

     這題我們要把鏈結串列每 k 個節點為一組進行反轉，
     並且不能改變節點值，只能改指標。

     ✅ 使用 dummy 節點讓整個過程更簡潔。
     ✅ 每一組先找第 k 個節點，然後從 groupPrev.next 到 kth 進行反轉。
     ✅ 反轉後記得連接 groupPrev 與下一組的開頭。
     ✅ 每次反轉一組後，更新 groupPrev 指向新尾巴。

     時間複雜度：O(n)
     空間複雜度：O(1)
    */
}
