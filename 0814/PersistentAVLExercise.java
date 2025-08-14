import java.util.*;

/**
 * 持久化 AVL（不可變節點 + 路徑複製）
 * 這裡做「插入版本」：每次 insert 產生新 root，舊版本可查詢不受影響。
 */
public class PersistentAVLExercise {

    static final class Node {
        final int key, height;
        final Node left, right;
        Node(int key, Node left, Node right){
            this.key = key; this.left = left; this.right = right;
            this.height = 1 + Math.max(h(left), h(right));
        }
    }

    static int h(Node n){ return n==null?0:n.height; }
    static int bf(Node n){ return n==null?0:h(n.left)-h(n.right); }

    static Node rotL(Node x){
        Node y = x.right;
        Node T2 = y.left;
        Node nx = new Node(x.key, x.left, T2);
        Node ny = new Node(y.key, nx, y.right);
        return ny;
    }
    static Node rotR(Node y){
        Node x = y.left;
        Node T2 = x.right;
        Node ny = new Node(y.key, T2, y.right);
        Node nx = new Node(x.key, x.left, ny);
        return nx;
    }
    static Node rebalance(Node n){
        int b = bf(n);
        if(b>1){
            if(bf(n.left)<0){
                Node nl = rotL(n.left);
                n = new Node(n.key, nl, n.right);
            }
            return rotR(n);
        }
        if(b<-1){
            if(bf(n.right)>0){
                Node nr = rotR(n.right);
                n = new Node(n.key, n.left, nr);
            }
            return rotL(n);
        }
        return n;
    }

    // 持久化插入：回傳新 root，不改舊樹
    static Node insert(Node n, int key){
        if(n==null) return new Node(key, null, null);
        if(key < n.key){
            Node nl = insert(n.left, key);
            return rebalance(new Node(n.key, nl, n.right));
        }else if(key > n.key){
            Node nr = insert(n.right, key);
            return rebalance(new Node(n.key, n.left, nr));
        }else{
            return n; // 不插重複
        }
    }

    static boolean search(Node n, int key){
        while(n!=null){
            if(key==n.key) return true;
            n = key<n.key ? n.left : n.right;
        }
        return false;
    }

    // Demo：維護多版本
    public static void main(String[] args){
        List<Node> versions = new ArrayList<>();
        Node v0 = null;
        versions.add(v0);

        Node v1 = insert(v0, 10);
        Node v2 = insert(v1, 20);
        Node v3 = insert(v2, 5);
        Node v4 = insert(v3, 15);

        versions.add(v1);
        versions.add(v2);
        versions.add(v3);
        versions.add(v4);

        System.out.println("v2 是否有 20? " + search(versions.get(2), 20)); // true
        System.out.println("v2 是否有 15? " + search(versions.get(2), 15)); // false
        System.out.println("v4 是否有 15? " + search(versions.get(4), 15)); // true
    }
}
