public class AVLNode {
    // === 資料欄位 ===
    int data;
    AVLNode left, right, parent; // 新增 parent，旋轉/刪除更方便
    int height;

    // === 建構子 ===
    public AVLNode(int data) {
        this.data = data;
        this.height = 1; // 葉節點高度定義為 1
    }

    // === Null-safe 工具：給樹其他地方也能呼叫 ===
    /** 取得節點高度，null 視為 0（方便外部直接用） */
    public static int h(AVLNode n) { return (n == null) ? 0 : n.height; }

    /** 計算平衡因子（左高-右高），null 節點視為 0 */
    public static int bf(AVLNode n) { return (n == null) ? 0 : h(n.left) - h(n.right); }

    /** 依子節點高度回填當前節點高度（null 安全） */
    public static void update(AVLNode n) {
        if (n != null) n.height = Math.max(h(n.left), h(n.right)) + 1;
    }

    // === 你原本的方法（保留介面） ===
    public int getBalance() { return bf(this); }
    public void updateHeight() { update(this); }

    // === 便利方法：設定左右子樹會自動維護 parent 與高度 ===
    public void setLeft(AVLNode child) {
        this.left = child;
        if (child != null) child.parent = this;
        update(this);
    }
    public void setRight(AVLNode child) {
        this.right = child;
        if (child != null) child.parent = this;
        update(this);
    }

    // （選用）簡易除鏈
    public void detachFromParent() {
        if (parent != null) {
            if (parent.left == this) parent.left = null;
            else if (parent.right == this) parent.right = null;
            AVLNode p = parent;
            parent = null;
            update(p);
        }
    }

    @Override
    public String toString() {
        return "AVLNode{data=" + data + ", h=" + height + ", bf=" + getBalance() + "}";
    }

    // === 加入最小測試 main，讓你可以直接執行這個檔案 ===
    public static void main(String[] args) {
        // 建一棵小樹：   10
        //              /  \
        //             5    15
        AVLNode root = new AVLNode(10);
        root.setLeft(new AVLNode(5));
        root.setRight(new AVLNode(15));

        // 更新高度（setLeft/setRight 已會更新，但示範再叫一次）
        root.left.updateHeight();
        root.right.updateHeight();
        root.updateHeight();

        System.out.println(root);       // 觀察高度與平衡因子
        System.out.println(root.left);
        System.out.println(root.right);
        System.out.println("balance(root) = " + root.getBalance()); // 0
    }
}
