package net.hyperj.gist.leetcode;

import java.util.LinkedList;
import java.util.List;

public class Q107BinaryTreeLevelOrderTraversalII {


    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> list = new LinkedList<>();
        getLevel(root, list, 0);
        return list;
    }

    private void getLevel(TreeNode root, List<List<Integer>> list, int level) {
        if (root == null) {
            return;
        }
        if (level >= list.size()) {
            list.add(0, new LinkedList<>());
        }
        getLevel(root.left, list, level + 1);
        getLevel(root.right, list, level + 1);
        list.get(list.size() - level - 1).add(root.val);
    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        System.out.println(new Q107BinaryTreeLevelOrderTraversalII().levelOrderBottom(root));
    }

}
