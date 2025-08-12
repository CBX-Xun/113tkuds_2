import java.util.*;

public class PriorityQueueWithHeap {
    private static class Task {
        final String name;
        final int seq;     // 插入順序（越小越早）
        final int version; // 任務版本（changePriority 後會+1）
        final int priority;
        Task(String n, int p, int s, int v){ name=n; priority=p; seq=s; version=v; }
    }

    private final PriorityQueue<Task> pq;
    private final Map<String, Integer> versionMap = new HashMap<>();
    private int seqGen = 0;

    public PriorityQueueWithHeap() {
        pq = new PriorityQueue<>((a,b)->{
            if (a.priority != b.priority) return Integer.compare(b.priority, a.priority);
            return Integer.compare(a.seq, b.seq);
        });
    }

    private void pushFresh(String name, int priority) {
        int v = versionMap.getOrDefault(name, 0);
        pq.offer(new Task(name, priority, seqGen++, v));
    }

    public void addTask(String name, int priority) {
        versionMap.putIfAbsent(name, 0);
        pushFresh(name, priority);
    }

    public void changePriority(String name, int newPriority) {
        int v = versionMap.getOrDefault(name, 0) + 1;
        versionMap.put(name, v);
        pq.offer(new Task(name, newPriority, seqGen++, v));
    }

    private Task topValid() {
        while (!pq.isEmpty()) {
            Task t = pq.peek();
            if (versionMap.getOrDefault(t.name, -1) == t.version) return t;
            pq.poll(); // 丟棄過期版本
        }
        return null;
    }

    public String peek() {
        Task t = topValid();
        return t == null ? null : t.name;
    }

    public String executeNext() {
        Task t = topValid();
        if (t == null) return null;
        pq.poll();
        // 任務完成後移除版本追蹤（或保留看需求）
        versionMap.remove(t.name);
        return t.name;
    }

    public static void main(String[] args) {
        PriorityQueueWithHeap q = new PriorityQueueWithHeap();
        q.addTask("備份", 1);
        q.addTask("緊急修復", 5);
        q.addTask("更新", 3);
        System.out.println(q.executeNext()); // 緊急修復
        System.out.println(q.executeNext()); // 更新
        System.out.println(q.executeNext()); // 備份
        q.addTask("A", 1);
        q.changePriority("A", 10);
        System.out.println(q.peek()); // A
    }
}
