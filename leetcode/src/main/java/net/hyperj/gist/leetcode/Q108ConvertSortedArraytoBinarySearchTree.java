package net.hyperj.gist.leetcode;

public class Q108ConvertSortedArraytoBinarySearchTree {

    public TreeNode sortedArrayToBST(int[] nums) {
        return sortedArrayToBST(nums, 0, nums.length - 1);
    }

    private TreeNode sortedArrayToBST(int[] nums, int start, int end) {
        if (start > end) {
            return null;
        }
        int mid = (start + end + 1) / 2;
        TreeNode node = new TreeNode(nums[mid]);
        node.left = sortedArrayToBST(nums, start, mid - 1);
        node.right = sortedArrayToBST(nums, mid + 1, end);
        return node;
    }

    public static void main(String[] args) {
        TreeNode tn = new Q108ConvertSortedArraytoBinarySearchTree().sortedArrayToBST(new int[]{-10, -3, 0, 5, 9});
        System.out.println(new Q144BinaryTreePreorderTraversal().preorderTraversal(tn));
    }
}
