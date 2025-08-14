import java.util.*;

public class AVLDeleteExercise {

    static class Node {
        int key, height;
        Node left, right;
        Node(int k){ key=k; height=1; }
    }
    private Node root;

    private int h(Node n){ return n==null?0:n.height; }
    private void upd(Node n){ if(n!=null) n.height = 1 + Math.max(h(n.left), h(n.right)); }
    private int bf(Node n){ return n==null?0:h(n.left)-h(n.right); }

    private Node rotL(Node x){
        Node y=x.right, T2=y.left;
        y.left=x; x.right=T2; upd(x); upd(y); return y;
    }
    private Node rotR(Node y){
        Node x=y.left, T2=x.right;
        x.right=y; y.left=T2; upd(y); upd(x); return x;
    }

    public void insert(int key){ root=insert(root,key); }
    private Node insert(Node n, int k){
        if(n==null) return new Node(k);
        if(k<n.key) n.left=insert(n.left,k);
        else if(k>n.key) n.right=insert(n.right,k);
        else return n;
        upd(n); return rebalance(n);
    }

    public void delete(int key){ root=delete(root,key); }
    private Node delete(Node n, int key){
        if(n==null) return null;
        if(key<n.key) n.left=delete(n.left,key);
        else if(key>n.key) n.right=delete(n.right,key);
        else{
            // 找到節點
            if(n.left==null || n.right==null){
                n = (n.left!=null)?n.left:n.right;
            }else{
                // 兩子節點：用後繼替換
                Node succ = n.right;
                while(succ.left!=null) succ=succ.left;
                n.key = succ.key;
                n.right = delete(n.right, succ.key);
            }
        }
        if(n==null) return null;
        upd(n); return rebalance(n);
    }

    private Node rebalance(Node n){
        int b = bf(n);
        if(b>1){
            if(bf(n.left)<0) n.left = rotL(n.left);
            return rotR(n);
        }
        if(b<-1){
            if(bf(n.right)>0) n.right = rotR(n.right);
            return rotL(n);
        }
        return n;
    }

    public List<Integer> inorder(){
        List<Integer> out=new ArrayList<>();
        inorder(root,out); return out;
    }
    private void inorder(Node n, List<Integer> out){
        if(n==null) return;
        inorder(n.left,out); out.add(n.key); inorder(n.right,out);
    }

    public static void main(String[] args){
        AVLDeleteExercise avl = new AVLDeleteExercise();
        int[] a = {20,10,30,5,15,25,40,22,27};
        for(int x:a) avl.insert(x);
        System.out.println("初始: " + avl.inorder());

        avl.delete(5);   // 刪葉
        avl.delete(30);  // 刪有兩子
        avl.delete(25);  // 刪單子
        System.out.println("刪後: " + avl.inorder());
    }
}
