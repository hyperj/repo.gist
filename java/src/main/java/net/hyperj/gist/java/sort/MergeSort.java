package net.hyperj.gist.java.sort;

import java.util.Arrays;

public class MergeSort extends Sort {

    public static void main(String[] args) {
        println(new MergeSort());
    }

    public int[] sort(int[] array) {
        if (array != null && array.length > 1) {
            int mid = array.length / 2;
            return merge(sort(Arrays.copyOfRange(array, 0, mid)),
                    sort(Arrays.copyOfRange(array, mid, array.length)));
        }
        return array;
    }

    private int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        for (int index = 0, i = 0, j = 0; index < result.length; index++) {
            if (j >= right.length) {
                result[index] = left[i++];
            } else if (i >= left.length || left[i] > right[j]) {
                result[index] = right[j++];
            } else {
                result[index] = left[i++];
            }
        }
        return result;
    }
}
