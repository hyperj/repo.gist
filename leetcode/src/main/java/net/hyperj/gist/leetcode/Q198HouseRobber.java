package net.hyperj.gist.leetcode;

public class Q198HouseRobber {

    public int rob(int[] nums) {
        if (nums.length == 0) return 0;
        int result = 0;
        int record = 0;
        for (int num : nums) {
            int temp = result;
            result = Math.max(record + num, result);
            record = temp;
        }
        return result;
    }

}
