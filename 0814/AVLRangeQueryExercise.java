public class AVLRotations {

    // 右旋：以 y 為根、x=y.left，旋轉後 x 成為新的子樹根
    // 時間 O(1)，空間 O(1)
    public static AVLNode rightRotate(AVLNode y) {
        if (y == null || y.left == null) return y;
        AVLNode x  = y.left;
        AVLNode T2 = x.right;

        // 保留原父節點，待會把新根掛回去
        AVLNode p = y.parent;

        // 執行旋轉（使用 setLeft/setRight 會自動維護 parent 與高度）
        x.setRight(y);      // x.right = y
        y.setLeft(T2);      // y.left  = T2

        // 把新根 x 接回原父節點
        x.parent = p;
        if (p != null) {
            if (p.left == y) p.left = x;
            else if (p.right == y) p.right = x;
        }

        // 旋轉後高度再保險更新一次
        y.updateHeight();
        x.updateHeight();
        return x;
    }

    // 左旋：以 x 為根、y=x.right，旋轉後 y 成為新的子樹根
    // 時間 O(1)，空間 O(1)
    public static AVLNode leftRotate(AVLNode x) {
        if (x == null || x.right == null) return x;
        AVLNode y  = x.right;
        AVLNode T2 = y.left;

        AVLNode p = x.parent;

        y.setLeft(x);       // y.left  = x
        x.setRight(T2);     // x.right = T2

        y.parent = p;
        if (p != null) {
            if (p.left == x) p.left = y;
            else if (p.right == x) p.right = y;
        }

        x.updateHeight();
        y.updateHeight();
        return y;
    }

    // --- 最小 Demo：可以直接 java AVLRotations 執行 ---
    public static void main(String[] args) {
        // 建一棵 LL 失衡樹：      30
        //                       /
        //                     20
        //                    /
        //                  10
        AVLNode root = new AVLNode(30);
        root.setLeft(new AVLNode(20));
        root.left.setLeft(new AVLNode(10));
        root.updateHeight();

        System.out.println("Before rightRotate: root=" + root);
        AVLNode newRoot = rightRotate(root);   // 旋轉後 newRoot 應為 20

        System.out.println("After  rightRotate: newRoot=" + newRoot);
        System.out.println("newRoot.left  = " + newRoot.left);
        System.out.println("newRoot.right = " + newRoot.right);

        // 再造 RR 失衡：      20
        //                        \
        //                         30
        //                           \
        //                            40
        newRoot.right.setRight(new AVLNode(40));
        newRoot.right.updateHeight();
        newRoot.updateHeight();

        AVLNode newRoot2 = leftRotate(newRoot); // 旋轉後 newRoot2 應為 30
        System.out.println("After  leftRotate : newRoot2=" + newRoot2);
        System.out.println("newRoot2.left  = " + newRoot2.left);
        System.out.println("newRoot2.right = " + newRoot2.right);
    }
}
