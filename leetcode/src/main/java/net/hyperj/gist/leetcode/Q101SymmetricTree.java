package net.hyperj.gist.leetcode;

public class Q101SymmetricTree {

    public boolean isSymmetric(TreeNode root) {
        return isSymmetric(root, root);
    }

    public boolean isSymmetric(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) {
            return true;
        } else if (t1 == null || t2 == null) {
            return false;
        }
        return (t1.val == t2.val)
                && isSymmetric(t1.right, t2.left)
                && isSymmetric(t1.left, t2.right);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.left = new TreeNode(2);
        System.out.println(new Q101SymmetricTree().isSymmetric(root));
    }

}
