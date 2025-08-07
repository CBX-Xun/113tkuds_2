class BSTValidationAndRepair {
    public boolean isValidBST(BSTNode root) {
        return validate(root, null, null);
    }
    private boolean validate(BSTNode node, Integer min, Integer max) {
        if (node == null) return true;
        if ((min != null && node.val <= min) || (max != null && node.val >= max)) return false;
        return validate(node.left, min, node.val) && validate(node.right, node.val, max);
    }

    BSTNode first = null, second = null, prev = new BSTNode(Integer.MIN_VALUE);
    public void findInvalidNodes(BSTNode root) {
        if (root == null) return;
        findInvalidNodes(root.left);
        if (prev != null && root.val < prev.val) {
            if (first == null) first = prev;
            second = root;
        }
        prev = root;
        findInvalidNodes(root.right);
    }

    public void recoverBST(BSTNode root) {
        first = null; second = null; prev = new BSTNode(Integer.MIN_VALUE);
        findInvalidNodes(root);
        if (first != null && second != null) {
            int tmp = first.val;
            first.val = second.val;
            second.val = tmp;
        }
    }

    public int countToRemove(BSTNode root) {
        java.util.List<Integer> list = new java.util.ArrayList<>();
        inorder(root, list);
        return list.size() - lengthOfLIS(list);
    }
    private void inorder(BSTNode node, java.util.List<Integer> list) {
        if (node == null) return;
        inorder(node.left, list);
        list.add(node.val);
        inorder(node.right, list);
    }
    private int lengthOfLIS(java.util.List<Integer> list) {
        java.util.ArrayList<Integer> dp = new java.util.ArrayList<>();
        for (int num : list) {
            int i = java.util.Collections.binarySearch(dp, num);
            if (i < 0) i = -(i + 1);
            if (i == dp.size()) dp.add(num);
            else dp.set(i, num);
        }
        return dp.size();
    }
}