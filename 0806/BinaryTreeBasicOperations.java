// 檔名：BinaryTreeBasicOperations.java

import java.util.*;

class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int val) { this.val = val; }
}

public class BinaryTreeBasicOperations {

    // 1. 計算總和與平均
    static int getSum(TreeNode root) {
        if (root == null) return 0;
        return root.val + getSum(root.left) + getSum(root.right);
    }

    static int getCount(TreeNode root) {
        if (root == null) return 0;
        return 1 + getCount(root.left) + getCount(root.right);
    }

    static double getAverage(TreeNode root) {
        int sum = getSum(root);
        int count = getCount(root);
        return count == 0 ? 0 : (double) sum / count;
    }

    // 2. 最大與最小值
    static int getMax(TreeNode root) {
        if (root == null) return Integer.MIN_VALUE;
        return Math.max(root.val, Math.max(getMax(root.left), getMax(root.right)));
    }

    static int getMin(TreeNode root) {
        if (root == null) return Integer.MAX_VALUE;
        return Math.min(root.val, Math.min(getMin(root.left), getMin(root.right)));
    }

    // 3. 寬度（最大層節點數）
    static int getWidth(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int maxWidth = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            maxWidth = Math.max(maxWidth, size);
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
        }
        return maxWidth;
    }

    // 4. 判斷是否為完全二元樹
    static boolean isComplete(TreeNode root) {
        if (root == null) return true;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean reachedEnd = false;

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node == null) {
                reachedEnd = true;
            } else {
                if (reachedEnd) return false; // 已經遇過空節點，不能再遇到非空節點
                queue.offer(node.left);
                queue.offer(node.right);
            }
        }
        return true;
    }

    // 測試程式
    public static void main(String[] args) {
        /*
               10
              /  \
             5    15
            / \     \
           2   7     20
        */
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(15);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(7);
        root.right.right = new TreeNode(20);

        System.out.println("🌲 節點總和：" + getSum(root));
        System.out.println("📊 平均值：" + getAverage(root));
        System.out.println("🔼 最大值：" + getMax(root));
        System.out.println("🔽 最小值：" + getMin(root));
        System.out.println("📏 樹的最大寬度：" + getWidth(root));
        System.out.println("✅ 是否為完全二元樹：" + isComplete(root));
    }
}
