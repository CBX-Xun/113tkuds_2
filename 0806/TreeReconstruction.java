class TreeReconstruction {
    public TreeNode buildTreePreIn(int[] preorder, int[] inorder) {
        return helperPreIn(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }
    private TreeNode helperPreIn(int[] pre, int ps, int pe, int[] in, int is, int ie) {
        if (ps > pe) return null;
        TreeNode root = new TreeNode(pre[ps]);
        int idx = is;
        while (in[idx] != pre[ps]) idx++;
        int leftSize = idx - is;
        root.left = helperPreIn(pre, ps + 1, ps + leftSize, in, is, idx - 1);
        root.right = helperPreIn(pre, ps + leftSize + 1, pe, in, idx + 1, ie);
        return root;
    }

    public TreeNode buildTreePostIn(int[] post, int[] inorder) {
        return helperPostIn(post, 0, post.length - 1, inorder, 0, inorder.length - 1);
    }
    private TreeNode helperPostIn(int[] post, int ps, int pe, int[] in, int is, int ie) {
        if (ps > pe) return null;
        TreeNode root = new TreeNode(post[pe]);
        int idx = is;
        while (in[idx] != post[pe]) idx++;
        int leftSize = idx - is;
        root.left = helperPostIn(post, ps, ps + leftSize - 1, in, is, idx - 1);
        root.right = helperPostIn(post, ps + leftSize, pe - 1, in, idx + 1, ie);
        return root;
    }

    public TreeNode buildCompleteTreeLevel(int[] arr) {
        if (arr.length == 0) return null;
        TreeNode[] nodes = new TreeNode[arr.length];
        for (int i = 0; i < arr.length; i++) nodes[i] = new TreeNode(arr[i]);
        for (int i = 0; i < arr.length; i++) {
            if (2 * i + 1 < arr.length) nodes[i].left = nodes[2 * i + 1];
            if (2 * i + 2 < arr.length) nodes[i].right = nodes[2 * i + 2];
        }
        return nodes[0];
    }

    public boolean validateRebuild(TreeNode root, int[] inorder) {
        java.util.List<Integer> list = new java.util.ArrayList<>();
        inorderTraversal(root, list);
        if (list.size() != inorder.length) return false;
        for (int i = 0; i < inorder.length; i++) {
            if (list.get(i) != inorder[i]) return false;
        }
        return true;
    }
    private void inorderTraversal(TreeNode node, java.util.List<Integer> list) {
        if (node == null) return;
        inorderTraversal(node.left, list);
        list.add(node.val);
        inorderTraversal(node.right, list);
    }
}