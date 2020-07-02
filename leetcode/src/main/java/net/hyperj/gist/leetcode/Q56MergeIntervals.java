package net.hyperj.gist.leetcode;

import java.util.Arrays;

public class Q56MergeIntervals {
    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length == 1) {
            return intervals;
        }
        Arrays.sort(intervals, (va, vb) -> va[0] - vb[0]);
        int[][] result = new int[intervals.length][2];
        int index = -1;
        for (int[] interval : intervals) {
            if (index == -1 || result[index][1] < interval[0]) {
                result[++index] = interval;
            } else if (result[index][1] < interval[1]) {
                result[index][1] = interval[1];
            }
        }
        return Arrays.copyOf(result, index + 1);
    }

    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(new Q56MergeIntervals().merge(new int[][]{{1, 3}, {2, 6}, {8, 10}, {15, 18}})));
    }

}
