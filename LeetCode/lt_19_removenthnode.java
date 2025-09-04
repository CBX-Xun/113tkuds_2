public class lt_19_removenthnode {

    // ✅ 題目定義的 ListNode 結構
    static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    // ✅ 題目解法主邏輯：刪除倒數第 n 個節點
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 建立 dummy 節點方便處理邊界情況（刪除 head）
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode fast = dummy;
        ListNode slow = dummy;

        // fast 先走 n + 1 步
        for (int i = 0; i < n + 1; i++) {
            fast = fast.next;
        }

        // fast 與 slow 一起走，直到 fast 到尾巴
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        // 此時 slow 停在要刪除節點的前一個
        slow.next = slow.next.next;

        return dummy.next;
    }

    // ✅ 本地測試用 main 方法
    public static void main(String[] args) {
        lt_19_removenthnode solution = new lt_19_removenthnode();

        // 建立測試鏈結串列：1 → 2 → 3 → 4 → 5
        ListNode head = new ListNode(1,
                            new ListNode(2,
                            new ListNode(3,
                            new ListNode(4,
                            new ListNode(5)))));

        int n = 2; // 刪除倒數第 2 個節點（4）

        ListNode result = solution.removeNthFromEnd(head, n);

        // 印出結果：應為 1 → 2 → 3 → 5
        System.out.print("刪除後的鏈結串列：");
        while (result != null) {
            System.out.print(result.val + " ");
            result = result.next;
        }
    }

    /*
     🧠 解題說明：

     題目要我們刪除倒數第 n 個節點，最直覺的方法是先算長度，再刪除第 (L - n) 個。
     但這樣會走兩次，我們可以使用「雙指針」優化，只走一次就找出該節點。

     ✅ 建立 dummy 節點 → 解決刪除 head 的特例問題。
     ✅ fast 指針先走 n+1 步，讓 fast 與 slow 間距是 n。
     ✅ fast 到尾巴時，slow 剛好在要刪除的前一個位置。
     ✅ 做 slow.next = slow.next.next 即可刪除目標節點。

     時間複雜度：O(L)
     空間複雜度：O(1)
    */
}
