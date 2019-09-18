package net.hyperj.gist.leetcode;

public class Q100SameTree {

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        } else if (q == null || p == null || p.val != q.val) {
            return false;
        }
        return isSameTree(p.right, q.right) &&
                isSameTree(p.left, q.left);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode right = new TreeNode(2);
        root.right = right;
        right.left = new TreeNode(3);
        System.out.println(new Q100SameTree().isSameTree(root, right));
    }
}
