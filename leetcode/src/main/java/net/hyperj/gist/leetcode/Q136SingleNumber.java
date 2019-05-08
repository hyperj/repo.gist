package net.hyperj.gist.leetcode;

class Q136SingleNumber {
    public int singleNumber(int[] nums) {
        int res = 0;
        for (int i : nums) {
            res ^= i;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new Q136SingleNumber().singleNumber(new int[]{4, 1, 2, 1, 2}));
    }
}