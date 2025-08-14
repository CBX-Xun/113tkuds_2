import java.util.*;

public class AVLBasicExercise {

    static class Node {
        int key, height;
        Node left, right;
        Node(int k){ key=k; height=1; }
    }

    private Node root;

    // === 基本工具 ===
    private int h(Node n){ return n==null?0:n.height; }
    private int bf(Node n){ return n==null?0:h(n.left)-h(n.right); }
    private void upd(Node n){ if(n!=null) n.height = 1 + Math.max(h(n.left), h(n.right)); }

    // 右旋
    private Node rotateRight(Node y){
        Node x = y.left, T2 = x.right;
        x.right = y; y.left = T2;
        upd(y); upd(x);
        return x;
    }

    // 左旋
    private Node rotateLeft(Node x){
        Node y = x.right, T2 = y.left;
        y.left = x; x.right = T2;
        upd(x); upd(y);
        return y;
    }

    // === 插入（AVL） ===
    public void insert(int key){ root = insert(root, key); }
    private Node insert(Node n, int key){
        if(n==null) return new Node(key);
        if(key < n.key) n.left = insert(n.left, key);
        else if(key > n.key) n.right = insert(n.right, key);
        else return n; // 不插重複

        upd(n);
        int b = bf(n);

        // LL
        if(b > 1 && key < n.left.key) return rotateRight(n);
        // RR
        if(b < -1 && key > n.right.key) return rotateLeft(n);
        // LR
        if(b > 1 && key > n.left.key){
            n.left = rotateLeft(n.left);
            return rotateRight(n);
        }
        // RL
        if(b < -1 && key < n.right.key){
            n.right = rotateRight(n.right);
            return rotateLeft(n);
        }
        return n;
    }

    // === 搜尋 ===
    public boolean search(int key){
        Node cur = root;
        while(cur!=null){
            if(key==cur.key) return true;
            cur = key<cur.key ? cur.left : cur.right;
        }
        return false;
    }

    // === 高度 ===
    public int height(){ return h(root); }

    // === 驗證 AVL ===
    public boolean isValidAVL(){ return isValidAVL(root)!=-2; }
    private int isValidAVL(Node n){
        if(n==null) return 0;
        int lh = isValidAVL(n.left);
        if(lh==-2) return -2;
        int rh = isValidAVL(n.right);
        if(rh==-2) return -2;
        int bal = lh - rh;
        if(bal<-1 || bal>1) return -2;
        if(n.height != 1 + Math.max(lh, rh)) return -2;
        return 1 + Math.max(lh, rh);
    }

    // 中序輸出（檢查方便）
    public List<Integer> inorder(){
        List<Integer> out = new ArrayList<>();
        inorder(root,out); return out;
    }
    private void inorder(Node n, List<Integer> out){
        if(n==null) return;
        inorder(n.left,out); out.add(n.key); inorder(n.right,out);
    }

    // === Demo ===
    public static void main(String[] args){
        AVLBasicExercise avl = new AVLBasicExercise();
        int[] arr = {10,20,30,40,50,25};
        for(int x: arr) avl.insert(x);

        System.out.println("中序: " + avl.inorder()); // [10,20,25,30,40,50]
        System.out.println("高度: " + avl.height());
        System.out.println("搜尋 25: " + avl.search(25));
        System.out.println("搜尋 99: " + avl.search(99));
        System.out.println("有效 AVL: " + avl.isValidAVL());
    }
}
