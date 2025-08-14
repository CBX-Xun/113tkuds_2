import java.util.*;

public class AVLRotationExercise {

    static class Node {
        int key, height;
        Node left, right;
        Node(int k){ key=k; height=1; }
    }

    private static int h(Node n){ return n==null?0:n.height; }
    private static void upd(Node n){ if(n!=null) n.height = 1 + Math.max(h(n.left), h(n.right)); }

    // 右旋
    public static Node rotateRight(Node y){
        Node x = y.left, T2 = x.right;
        x.right = y; y.left = T2;
        upd(y); upd(x);
        return x;
    }
    // 左旋
    public static Node rotateLeft(Node x){
        Node y = x.right, T2 = y.left;
        y.left = x; x.right = T2;
        upd(x); upd(y);
        return y;
    }

    // LR: 對 left 左旋，再對自己右旋
    public static Node rotateLeftRight(Node z){
        z.left = rotateLeft(z.left);
        return rotateRight(z);
    }

    // RL: 對 right 右旋，再對自己左旋
    public static Node rotateRightLeft(Node z){
        z.right = rotateRight(z.right);
        return rotateLeft(z);
    }

    // 小測試：構造不平衡並套用旋轉
    public static void main(String[] args){
        // 測 RR -> 左旋
        Node a = new Node(10);
        a.right = new Node(20); a.right.right = new Node(30);
        upd(a.right); upd(a);
        a = rotateLeft(a);
        System.out.println("RR後根: " + a.key); // 20

        // 測 LL -> 右旋
        Node b = new Node(30);
        b.left = new Node(20); b.left.left = new Node(10);
        upd(b.left); upd(b);
        b = rotateRight(b);
        System.out.println("LL後根: " + b.key); // 20

        // 測 LR
        Node c = new Node(30);
        c.left = new Node(10); c.left.right = new Node(20);
        upd(c.left); upd(c);
        c = rotateLeftRight(c);
        System.out.println("LR後根: " + c.key); // 20

        // 測 RL
        Node d = new Node(10);
        d.right = new Node(30); d.right.left = new Node(20);
        upd(d.right); upd(d);
        d = rotateRightLeft(d);
        System.out.println("RL後根: " + d.key); // 20
    }
}
