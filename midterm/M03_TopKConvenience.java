/* 
 * Time Complexity: O(n log K)
 * 說明：維護大小為 K 的最小堆 (qty, name)。讀入 n 筆，每筆 O(log K)。
 * 輸出前再將堆中元素以 (qty desc, name asc) 排序輸出。
 * 同銷量 tie-break：以品名字典序升冪。
 */
import java.io.*;
import java.util.*;

public class M03_TopKConvenience {
    static class Item {
        String name;
        int qty;
        Item(String n, int q){name=n; qty=q;}
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        PriorityQueue<Item> pq = new PriorityQueue<>( (a,b)->{
            if(a.qty!=b.qty) return Integer.compare(a.qty, b.qty); // 最小堆
            return b.name.compareTo(a.name); // 讓字典序小的較「大」，留下來
        });

        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            String name = st.nextToken();
            int qty = Integer.parseInt(st.nextToken());
            Item it = new Item(name, qty);
            if(pq.size()<K) pq.offer(it);
            else{
                Item top = pq.peek();
                if (top!=null){
                    if (qty>top.qty || (qty==top.qty && name.compareTo(top.name)<0)){
                        pq.poll();
                        pq.offer(it);
                    }
                }
            }
        }
        List<Item> ans = new ArrayList<>();
        while(!pq.isEmpty()) ans.add(pq.poll());
        ans.sort( (a,b)->{
            if(a.qty!=b.qty) return Integer.compare(b.qty, a.qty); // 高到低
            return a.name.compareTo(b.name);
        });
        StringBuilder sb = new StringBuilder();
        for(Item it: ans){
            sb.append(it.name).append(' ').append(it.qty).append('\n');
        }
        System.out.print(sb.toString());
    }
}
