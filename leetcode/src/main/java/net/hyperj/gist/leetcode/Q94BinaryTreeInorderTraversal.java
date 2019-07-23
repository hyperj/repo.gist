package net.hyperj.gist.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Q94BinaryTreeInorderTraversal {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            TreeNode temp = stack.pop();
            list.add(temp.val);
            curr = temp.right;
        }
        return list;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        TreeNode right = new TreeNode(1);
        root.right = right;
        right.left = new TreeNode(2);
        System.out.println(new Q94BinaryTreeInorderTraversal().inorderTraversal(root));
    }
}
