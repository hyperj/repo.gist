package net.hyperj.gist.leetcode;

public class Q169MajorityElement {

    public int majorityElement(int[] nums) {
        int majority = 0, count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (count == 0) {
                majority = nums[i];
                count++;
            } else if (majority == nums[i]) {
                count++;
            } else {
                count--;
            }
        }
        return majority;
    }

    public static void main(String[] args) {
        System.out.println(new Q169MajorityElement().majorityElement(new int[]{2, 2, 1, 1, 1, 2, 2}));
    }
}
