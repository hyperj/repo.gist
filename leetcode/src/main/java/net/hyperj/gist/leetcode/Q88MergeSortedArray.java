package net.hyperj.gist.leetcode;

import java.util.Arrays;

public class Q88MergeSortedArray {

    public int[] merge(int[] nums1, int m, int[] nums2, int n) {
        int k = m + n;
        while (n > 0) {
            if (m == 0 || nums1[m - 1] < nums2[n - 1]) {
                nums1[--k] = nums2[--n];
            } else {
                nums1[--k] = nums1[--m];
            }
        }
        return nums1;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Q88MergeSortedArray().merge(new int[]{2, 0}, 1, new int[]{1}, 1)));
    }
}
