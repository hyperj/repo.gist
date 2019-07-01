package net.hyperj.gist.leetcode;

public class Q35SearchInsertPosition {

    public int searchInsert(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            if (target <= nums[i]) {
                return i;
            }
        }
        return nums.length;
    }

    public static void main(String[] args) {
        System.out.println(new Q35SearchInsertPosition().searchInsert(new int[]{1, 3, 5}, 7));
    }

}
