package net.hyperj.gist.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Q1TwoSum {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                return new int[]{map.get(nums[i]), i};
            }
            map.put(target - nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    public static void main(String[] args) {
        int[] res = new Q1TwoSum().twoSum(new int[]{2, 7, 11, 19}, 9);
        System.out.println(Arrays.toString(res));
    }
}