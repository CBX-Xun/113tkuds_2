import java.io.*;
import java.util.*;

public class M08_BSTRangedSum {
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
    static long rangeSum(Node r, int L, int R){
        if(r==null) return 0;
        if(r.val < L) return rangeSum(r.right, L, R);
        if(r.val > R) return rangeSum(r.left, L, R);
        return r.val + rangeSum(r.left, L, R) + rangeSum(r.right, L, R);
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
        st = new StringTokenizer(br.readLine());
        int L = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());
        System.out.println("Sum: " + rangeSum(root, L, R));
    }
}
