/* 
 * Time Complexity: O(n)
 * 說明：一次建樹，BST 以範圍 (min,max) 遞迴檢查；AVL 以後序回傳高度，
 * 子樹不平衡或不合法回傳特殊值。兩者皆各自一次 DFS。
 */
import java.io.*;
import java.util.*;

public class M09_AVLValidate {
    static class Node{
        int val; Node left, right;
        Node(int v){val=v;}
    }
    static Node build(int[] a){
        int n=a.length;
        if(n==0 || a[0]==-1) return null;
        Node[] nodes = new Node[n];
        for(int i=0;i<n;i++) if(a[i]!=-1) nodes[i]=new Node(a[i]);
        for(int i=0;i<n;i++){
            if(nodes[i]==null) continue;
            int L=2*i+1, R=2*i+2;
            if(L<n && nodes[L]!=null) nodes[i].left=nodes[L];
            if(R<n && nodes[R]!=null) nodes[i].right=nodes[R];
        }
        return nodes[0];
    }
    static boolean isBST(Node r, long lo, long hi){
        if(r==null) return true;
        if(r.val<=lo || r.val>=hi) return false;
        return isBST(r.left, lo, r.val) && isBST(r.right, r.val, hi);
    }
    static final int BAD = Integer.MIN_VALUE/2;
    static int heightIfAVL(Node r){
        if(r==null) return 0;
        int lh = heightIfAVL(r.left);
        if(lh==BAD) return BAD;
        int rh = heightIfAVL(r.right);
        if(rh==BAD) return BAD;
        if (Math.abs(lh-rh) > 1) return BAD;
        return Math.max(lh, rh) + 1;
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());
        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++){
            if(!st.hasMoreTokens()) st = new StringTokenizer(br.readLine());
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Node root = build(arr);
        if(!isBST(root, Long.MIN_VALUE, Long.MAX_VALUE)){
            System.out.println("Invalid BST");
            return;
        }
        if(heightIfAVL(root)==BAD){
            System.out.println("Invalid AVL");
        }else{
            System.out.println("Valid");
        }
    }
}
