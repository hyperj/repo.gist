package net.hyperj.gist.leetcode;

public class Q53MaximumSubarray {

    public int maxSubArray(int[] nums) {
        if (nums.length > 0) {
            int max = nums[0], temp = nums[0];
            for (int i = 1; i < nums.length; i++) {
                temp = Math.max(temp + nums[i], nums[i]);
                max = Math.max(max, temp);
            }
            return max;
        }
        return Integer.MIN_VALUE;
    }

    public static void main(String[] args) {
        System.out.println(new Q53MaximumSubarray().maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 2, 5}));
    }

}
