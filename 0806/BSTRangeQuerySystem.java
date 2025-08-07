class BSTNode {
    int val;
    BSTNode left, right;
    BSTNode(int x) { val = x; }
}

class BSTRangeQuerySystem {
    public void rangeQuery(BSTNode root, int min, int max, java.util.List<Integer> result) {
        if (root == null) return;
        if (root.val > min) rangeQuery(root.left, min, max, result);
        if (root.val >= min && root.val <= max) result.add(root.val);
        if (root.val < max) rangeQuery(root.right, min, max, result);
    }

    public int rangeCount(BSTNode root, int min, int max) {
        java.util.List<Integer> res = new java.util.ArrayList<>();
        rangeQuery(root, min, max, res);
        return res.size();
    }

    public int rangeSum(BSTNode root, int min, int max) {
        java.util.List<Integer> res = new java.util.ArrayList<>();
        rangeQuery(root, min, max, res);
        return res.stream().mapToInt(i -> i).sum();
    }

    public int closest(BSTNode root, int target) {
        int closest = root.val;
        while (root != null) {
            if (Math.abs(root.val - target) < Math.abs(closest - target))
                closest = root.val;
            root = (target < root.val) ? root.left : root.right;
        }
        return closest;
    }
}