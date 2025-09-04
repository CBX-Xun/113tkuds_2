public class lt_21_mergetwosortedlists {

    // ✅ 題目定義的 ListNode 結構
    static class ListNode {
        int val;
        ListNode next;

        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    // ✅ 合併兩個排序好的鏈結串列
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(0);  // 虛擬頭節點
        ListNode current = dummy;

        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                current.next = list1;
                list1 = list1.next;
            } else {
                current.next = list2;
                list2 = list2.next;
            }
            current = current.next;
        }

        // 剩下的直接接上
        if (list1 != null) {
            current.next = list1;
        } else {
            current.next = list2;
        }

        return dummy.next;
    }

    // ✅ 本地測試用 main 方法
    public static void main(String[] args) {
        lt_21_mergetwosortedlists solution = new lt_21_mergetwosortedlists();

        // 建立 list1: 1 → 2 → 4
        ListNode list1 = new ListNode(1, new ListNode(2, new ListNode(4)));

        // 建立 list2: 1 → 3 → 4
        ListNode list2 = new ListNode(1, new ListNode(3, new ListNode(4)));

        ListNode result = solution.mergeTwoLists(list1, list2);

        // 輸出結果：1 → 1 → 2 → 3 → 4 → 4
        System.out.print("合併後的鏈結串列：");
        while (result != null) {
            System.out.print(result.val + " ");
            result = result.next;
        }
    }

    /*
     🧠 解題說明：

     題目給定兩個排序好的鏈結串列，要求合併成一個新的排序好的鏈結串列。
     用 dummy node 作為新的串列開頭，每次挑出較小的節點接到 current 後方。
     最後把剩下的 list1 或 list2 接上即可完成。

     ✅ 時間複雜度：O(m + n)
     ✅ 空間複雜度：O(1)（重接鏈結，不新建節點）
    */
}
