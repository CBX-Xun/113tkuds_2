class TreePathProblems {
    public java.util.List<java.util.List<Integer>> allPaths(TreeNode root) {
        java.util.List<java.util.List<Integer>> res = new java.util.ArrayList<>();
        backtrack(root, new java.util.ArrayList<>(), res);
        return res;
    }
    private void backtrack(TreeNode node, java.util.List<Integer> path, java.util.List<java.util.List<Integer>> res) {
        if (node == null) return;
        path.add(node.val);
        if (node.left == null && node.right == null) res.add(new java.util.ArrayList<>(path));
        else {
            backtrack(node.left, path, res);
            backtrack(node.right, path, res);
        }
        path.remove(path.size() - 1);
    }

    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) return false;
        if (root.left == null && root.right == null) return root.val == sum;
        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }

    public int maxRootToLeafSum(TreeNode root) {
        if (root == null) return Integer.MIN_VALUE;
        if (root.left == null && root.right == null) return root.val;
        return root.val + Math.max(maxRootToLeafSum(root.left), maxRootToLeafSum(root.right));
    }

    public int maxPathSum(TreeNode root) {
        int[] max = new int[]{Integer.MIN_VALUE};
        maxGain(root, max);
        return max[0];
    }
    private int maxGain(TreeNode node, int[] max) {
        if (node == null) return 0;
        int left = Math.max(0, maxGain(node.left, max));
        int right = Math.max(0, maxGain(node.right, max));
        max[0] = Math.max(max[0], left + right + node.val);
        return node.val + Math.max(left, right);
    }
}