class MultiWayTreeNode {
    int val;
    java.util.List<MultiWayTreeNode> children = new java.util.ArrayList<>();
    MultiWayTreeNode(int val) { this.val = val; }
}

class MultiWayTreeAndDecisionTree {
    public void dfs(MultiWayTreeNode root) {
        if (root == null) return;
        System.out.print(root.val + " ");
        for (MultiWayTreeNode child : root.children) dfs(child);
    }

    public void bfs(MultiWayTreeNode root) {
        java.util.Queue<MultiWayTreeNode> queue = new java.util.LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            MultiWayTreeNode node = queue.poll();
            System.out.print(node.val + " ");
            for (MultiWayTreeNode child : node.children) queue.add(child);
        }
    }

    public int height(MultiWayTreeNode root) {
        if (root == null) return 0;
        int max = 0;
        for (MultiWayTreeNode child : root.children) max = Math.max(max, height(child));
        return max + 1;
    }

    public java.util.Map<MultiWayTreeNode, Integer> degrees(MultiWayTreeNode root) {
        java.util.Map<MultiWayTreeNode, Integer> map = new java.util.HashMap<>();
        countDegrees(root, map);
        return map;
    }
    private void countDegrees(MultiWayTreeNode node, java.util.Map<MultiWayTreeNode, Integer> map) {
        if (node == null) return;
        map.put(node, node.children.size());
        for (MultiWayTreeNode child : node.children) countDegrees(child, map);
    }

    // 猜數字遊戲 (簡單版決策樹)
    public void guessNumber(int number) {
        int low = 1, high = 100, mid;
        while (low <= high) {
            mid = (low + high) / 2;
            System.out.println("你想的是 " + mid + " 嗎？");
            // 模擬比較，實作時用輸入替代
            if (mid == number) {
                System.out.println("答對了！"); return;
            } else if (mid < number) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
    }
}
