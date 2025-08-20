import java.io.*;
import java.util.*;

public class M12_MergeKTimeTables {
    static class Node{
        int val, li, idx;
        Node(int v, int l, int i){ val=v; li=l; idx=i; }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int K = Integer.parseInt(br.readLine().trim());
        List<int[]> lists = new ArrayList<>();
        for(int i=0;i<K;i++){
            int len = Integer.parseInt(br.readLine().trim());
            int[] arr = new int[len];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0;j<len;j++){
                if(!st.hasMoreTokens()) st = new StringTokenizer(br.readLine());
                arr[j] = Integer.parseInt(st.nextToken());
            }
            lists.add(arr);
        }
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(a->a.val));
        for(int i=0;i<K;i++){
            if(lists.get(i).length>0){
                pq.offer(new Node(lists.get(i)[0], i, 0));
            }
        }
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        while(!pq.isEmpty()){
            Node cur = pq.poll();
            if(!first) sb.append(' ');
            first=false;
            sb.append(cur.val);
            int ni = cur.idx + 1;
            int[] arr = lists.get(cur.li);
            if(ni < arr.length){
                pq.offer(new Node(arr[ni], cur.li, ni));
            }
        }
        System.out.println(sb.toString());
    }
}
