package net.hyperj.gist.leetcode;

import java.util.Arrays;

public class Q88MergeSortedArray {

    public int[] merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1, j = n - 1, k = m + n - 1;
        while (k >= 0 && j >= 0) {
            if (nums1[i] < nums2[j]) {
                nums1[k--] = nums2[j--];
            } else {
                nums1[k--] = nums1[i--];
            }
        }
        return nums1;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Q88MergeSortedArray().merge(new int[]{1, 2, 3, 0, 0, 0}, 3, new int[]{2, 5, 6}, 3)));
    }
}
