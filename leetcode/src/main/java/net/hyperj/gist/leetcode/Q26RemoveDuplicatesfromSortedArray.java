package net.hyperj.gist.leetcode;

public class Q26RemoveDuplicatesfromSortedArray {

    public int removeDuplicates(int[] nums) {
        if (nums != null && nums.length > 0) {
            int tmp = 0;
            for (int i = 1; i < nums.length; i++) {
                if (nums[tmp] != nums[i]) {
                    tmp++;
                    nums[tmp] = nums[i];
                }
            }
            return tmp + 1;
        }
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(new Q26RemoveDuplicatesfromSortedArray().removeDuplicates(new int[]{1, 1, 2, 3, 4, 4, 5, 5, 5, 6, 7, 7}));
    }
}
