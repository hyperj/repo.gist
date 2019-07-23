package net.hyperj.gist.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Q144BinaryTreePreorderTraversal {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                list.add(curr.val);
                if (curr.right != null) {
                    stack.push(curr.right);
                }
                curr = curr.left;
            }
            if (!stack.isEmpty()) {
                curr = stack.pop();
            }
        }
        return list;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode right = new TreeNode(2);
        root.right = right;
        right.left = new TreeNode(3);
        System.out.println(new Q144BinaryTreePreorderTraversal().preorderTraversal(root));
    }
}
