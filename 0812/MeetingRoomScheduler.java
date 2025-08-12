import java.util.*;

/**
 * Part A：最少會議室數 = 使用最小堆追蹤「已占用房間的最早結束時間」。
 * Part B：只有 N 間房時，求最大總會議時間：
 *   - 這裡提供 N=1（單間房）時的「加權區間排程」DP 最優解。
 *   - 一般 N>1 的加權最大化屬於較難問題；此處提供 N=1 的完整正解 + 最少房間數方法。
 */
public class MeetingRoomScheduler {

    public static int minMeetingRooms(int[][] intervals) {
        if (intervals.length==0) return 0;
        Arrays.sort(intervals, Comparator.comparingInt(a->a[0]));
        PriorityQueue<Integer> ends = new PriorityQueue<>();
        for (int[] it : intervals) {
            if (!ends.isEmpty() && it[0] >= ends.peek()) ends.poll();
            ends.offer(it[1]);
        }
        return ends.size();
    }

    // N=1：最大總時長（加權區間排程）
    public static List<int[]> bestScheduleSingleRoom(int[][] intervals) {
        int n = intervals.length;
        Arrays.sort(intervals, Comparator.comparingInt(a->a[1]));
        int[] p = new int[n]; // p[i] = 最右邊且不與 i 衝突的索引
        for (int i=0;i<n;i++){
            p[i] = -1;
            for (int j=i-1;j>=0;j--){
                if (intervals[j][1] <= intervals[i][0]) { p[i]=j; break; }
            }
        }
        int[] w = new int[n]; // 權重=持續時間
        for (int i=0;i<n;i++) w[i] = intervals[i][1]-intervals[i][0];

        int[] dp = new int[n];
        boolean[] take = new boolean[n];
        for (int i=0;i<n;i++){
            int notTake = i==0? 0 : dp[i-1];
            int takeVal = w[i] + (p[i]==-1? 0 : dp[p[i]]);
            if (takeVal > notTake) { dp[i]=takeVal; take[i]=true; }
            else { dp[i]=notTake; take[i]=false; }
        }
        // 回溯
        List<int[]> chosen = new ArrayList<>();
        for (int i=n-1;i>=0;){
            if (take[i]) {
                chosen.add(intervals[i]);
                i = p[i];
            } else i--;
        }
        Collections.reverse(chosen);
        return chosen;
    }

    public static void main(String[] args) {
        System.out.println(minMeetingRooms(new int[][]{{0,30},{5,10},{15,20}})); // 2
        System.out.println(minMeetingRooms(new int[][]{{9,10},{4,9},{4,17}}));  // 2
        System.out.println(minMeetingRooms(new int[][]{{1,5},{8,9},{8,9}}));    // 2

        int[][] oneRoom = {{1,4},{2,3},{4,6}};
        List<int[]> plan = bestScheduleSingleRoom(oneRoom);
        int total=0; for (int[] it: plan) total += it[1]-it[0];
        // 期望：[1,4] + [4,6]，總時長=5
        System.out.println("Total="+total);
    }
}
