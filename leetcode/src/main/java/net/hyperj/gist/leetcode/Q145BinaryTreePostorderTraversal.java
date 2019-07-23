package net.hyperj.gist.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Q145BinaryTreePostorderTraversal {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(new TreeNode(curr.val));
                if (curr.right != null) {
                    stack.push(curr.right);
                }
                curr = curr.left;
            }
            TreeNode temp = stack.pop();
            if (temp.left == null && temp.right == null) {
                list.add(temp.val);
                if (!stack.isEmpty()) {
                    curr = stack.pop();
                }
            } else {
                stack.push(new TreeNode(temp.val));
                if (temp.right != null) {
                    stack.push(temp.right);
                }
                curr = temp.left;
            }
        }
        return list;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.right = new TreeNode(2);
        System.out.println(new Q145BinaryTreePostorderTraversal().postorderTraversal(root));
    }
}
