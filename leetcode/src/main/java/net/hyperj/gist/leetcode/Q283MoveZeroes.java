package net.hyperj.gist.leetcode;

public class Q283MoveZeroes {
    public void moveZeroes(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        int pos = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                if (pos < i) {
                    nums[pos++] = nums[i];
                    nums[i] = 0;
                } else {
                    pos++;
                }
            }
        }
    }
}
