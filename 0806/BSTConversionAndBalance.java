class BSTConversionAndBalance {
    TreeNode prev = null, head = null;
    public TreeNode bstToDoublyList(BSTNode root) {
        if (root == null) return null;
        convert(root);
        return head;
    }
    private void convert(BSTNode node) {
        if (node == null) return;
        convert(node.left);
        TreeNode curr = new TreeNode(node.val);
        if (prev != null) {
            prev.right = curr;
            curr.left = prev;
        } else {
            head = curr;
        }
        prev = curr;
        convert(node.right);
    }

    public BSTNode sortedArrayToBST(int[] nums) {
        return build(nums, 0, nums.length - 1);
    }
    private BSTNode build(int[] nums, int l, int r) {
        if (l > r) return null;
        int mid = (l + r) / 2;
        BSTNode node = new BSTNode(nums[mid]);
        node.left = build(nums, l, mid - 1);
        node.right = build(nums, mid + 1, r);
        return node;
    }

    public boolean isBalanced(TreeNode root) {
        return height(root) != -1;
    }
    private int height(TreeNode node) {
        if (node == null) return 0;
        int lh = height(node.left);
        int rh = height(node.right);
        if (lh == -1 || rh == -1 || Math.abs(lh - rh) > 1) return -1;
        return Math.max(lh, rh) + 1;
    }

    public void convertToGreaterTree(BSTNode root) {
        int[] sum = new int[1];
        convertSum(root, sum);
    }
    private void convertSum(BSTNode node, int[] sum) {
        if (node == null) return;
        convertSum(node.right, sum);
        sum[0] += node.val;
        node.val = sum[0];
        convertSum(node.left, sum);
    }
}