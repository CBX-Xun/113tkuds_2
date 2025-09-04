public class lt_24_swapnodesinpairs {

    // ✅ ListNode 類別定義（照題目給的）
    static class ListNode {
        int val;
        ListNode next;

        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    // ✅ 題目主邏輯：每兩個節點交換一次（不能改值，只能改指標）
    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0);  // 虛擬頭節點
        dummy.next = head;

        ListNode prev = dummy;

        while (prev.next != null && prev.next.next != null) {
            ListNode first = prev.next;
            ListNode second = first.next;

            // ✅ 交換指標順序
            first.next = second.next;
            second.next = first;
            prev.next = second;

            // 前進兩個節點
            prev = first;
        }

        return dummy.next;
    }

    // ✅ 測試用 main 方法
    public static void main(String[] args) {
        lt_24_swapnodesinpairs solution = new lt_24_swapnodesinpairs();

        // 建立鏈結串列：1 → 2 → 3 → 4
        ListNode head = new ListNode(1,
                            new ListNode(2,
                            new ListNode(3,
                            new ListNode(4))));

        ListNode result = solution.swapPairs(head);

        // 預期輸出：2 → 1 → 4 → 3
        System.out.print("交換後的結果：");
        while (result != null) {
            System.out.print(result.val + (result.next != null ? " → " : ""));
            result = result.next;
        }
    }

    /*
     🧠 解題說明：

     本題要求交換相鄰節點的位置（不能改值），使用 dummy 節點統一處理。
     每次處理一對節點：
       - first → second → nextPair
       - 要交換成 second → first → nextPair

     ✅ dummy.next 方便處理頭部交換的情況
     ✅ prev 追蹤前一個已處理節點，確保鏈結完整

     時間複雜度：O(n)
     空間複雜度：O(1)
    */
}
