import java.util.*;

public class MergeKSortedArrays {
    private static class Node {
        int val, ai, idx;
        Node(int v,int a,int i){ val=v; ai=a; idx=i; }
    }

    public static int[] merge(List<int[]> arrays) {
        int total = 0;
        for (int[] a : arrays) total += a.length;
        PriorityQueue<Node> min = new PriorityQueue<>(Comparator.comparingInt(n->n.val));
        for (int i=0;i<arrays.size();i++) if (arrays.get(i).length>0) {
            min.offer(new Node(arrays.get(i)[0], i, 0));
        }
        int[] res = new int[total];
        int p=0;
        while(!min.isEmpty()){
            Node n = min.poll();
            res[p++] = n.val;
            int next = n.idx+1;
            if (next < arrays.get(n.ai).length) {
                min.offer(new Node(arrays.get(n.ai)[next], n.ai, next));
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(merge(Arrays.asList(
            new int[]{1,4,5}, new int[]{1,3,4}, new int[]{2,6}
        )))); // [1,1,2,3,4,4,5,6]

        System.out.println(Arrays.toString(merge(Arrays.asList(
            new int[]{1,2,3}, new int[]{4,5,6}, new int[]{7,8,9}
        )))); // [1..9]

        System.out.println(Arrays.toString(merge(Arrays.asList(
            new int[]{1}, new int[]{0}
        )))); // [0,1]
    }
}
