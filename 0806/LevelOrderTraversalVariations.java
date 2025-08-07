class LevelOrderTraversalVariations {
    public java.util.List<java.util.List<Integer>> levelOrder(TreeNode root) {
        java.util.List<java.util.List<Integer>> res = new java.util.ArrayList<>();
        if (root == null) return res;
        java.util.Queue<TreeNode> queue = new java.util.LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            java.util.List<Integer> level = new java.util.ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                level.add(node.val);
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            res.add(level);
        }
        return res;
    }

    public java.util.List<java.util.List<Integer>> zigzagLevelOrder(TreeNode root) {
        java.util.List<java.util.List<Integer>> res = levelOrder(root);
        for (int i = 1; i < res.size(); i += 2)
            java.util.Collections.reverse(res.get(i));
        return res;
    }

    public java.util.List<Integer> lastInEachLevel(TreeNode root) {
        java.util.List<Integer> res = new java.util.ArrayList<>();
        java.util.Queue<TreeNode> queue = new java.util.LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (i == size - 1) res.add(node.val);
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
        }
        return res;