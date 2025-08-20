/* 
 * Time Complexity: O(n)
 * 說明：標準原地 Heap Sort：建最大堆 O(n)，每次取最大與尾交換並下沉 O(log n)，
 * 總計 O(n log n)；但建堆部分是 O(n)。本題 tie-break：分數相同時 index 較小者優先
 * （在遞增輸出中會較前面）。實作為 pair(score, index) 的比較：
 * (s1 > s2) 或 (s1 == s2 且 idx1 > idx2) 視為「更大」。
 */
import java.io.*;
import java.util.*;

public class M11_HeapSortWithTie {
    static int[] score, idx;
    static boolean greater(int i, int j){
        if (score[i] != score[j]) return score[i] > score[j];
        return idx[i] > idx[j]; // 分數同時 index 較大者視為更大 -> 會被放到更後面
    }
    static void swap(int i, int j){
        int ts = score[i]; score[i]=score[j]; score[j]=ts;
        int ti = idx[i]; idx[i]=idx[j]; idx[j]=ti;
    }
    static void heapifyDown(int n, int i){
        while(true){
            int l=i*2+1, r=i*2+2, best=i;
            if (l<n && greater(l, best)) best=l;
            if (r<n && greater(r, best)) best=r;
            if (best==i) break;
            swap(i, best);
            i=best;
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());
        score = new int[n];
        idx = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++){
            if(!st.hasMoreTokens()) st = new StringTokenizer(br.readLine());
            score[i] = Integer.parseInt(st.nextToken());
            idx[i] = i;
        }
        // 建最大堆
        for(int i=n/2-1;i>=0;i--) heapifyDown(n, i);
        // HeapSort
        for(int end=n-1; end>0; end--){
            swap(0, end);
            heapifyDown(end, 0);
        }
        // 遞增輸出分數
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<n;i++){
            if(i>0) sb.append(' ');
            sb.append(score[i]);
        }
        System.out.println(sb.toString());
    }
}
