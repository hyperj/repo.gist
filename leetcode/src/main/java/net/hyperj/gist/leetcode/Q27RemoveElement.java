package net.hyperj.gist.leetcode;

public class Q27RemoveElement {

    public int removeElement(int[] nums, int val) {
        if (nums.length > 0) {
            int index = 0;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] != val) {
                    nums[index] = nums[i];
                    index++;
                }
            }
            return index;
        }
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(new Q27RemoveElement().removeElement(new int[]{0, 1, 2, 2, 3, 0, 4, 2}, 2));
    }
}
