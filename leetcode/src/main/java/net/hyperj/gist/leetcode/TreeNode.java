package net.hyperj.gist.leetcode;

public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }

    public static TreeNode init(int[] arrs) {
        if (arrs != null && arrs.length > 0) {
            TreeNode tree = new TreeNode(arrs[0]);
            TreeNode temp = tree;
            for (int i = 1; i < arrs.length; ) {

            }
            return tree;
        }
        return null;
    }
}
