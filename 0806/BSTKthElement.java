class BSTKthElement {
    java.util.List<Integer> inorder = new java.util.ArrayList<>();

    public void inorderTraversal(BSTNode root) {
        if (root == null) return;
        inorderTraversal(root.left);
        inorder.add(root.val);
        inorderTraversal(root.right);
    }

    public int kthSmallest(BSTNode root, int k) {
        inorder.clear();
        inorderTraversal(root);
        return inorder.get(k - 1);
    }

    public int kthLargest(BSTNode root, int k) {
        inorder.clear();
        inorderTraversal(root);
        return inorder.get(inorder.size() - k);
    }

    public java.util.List<Integer> kthRange(BSTNode root, int k, int j) {
        inorder.clear();
        inorderTraversal(root);
        return inorder.subList(k - 1, j);
    }
}
