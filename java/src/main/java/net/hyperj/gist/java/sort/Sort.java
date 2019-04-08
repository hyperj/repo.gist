package net.hyperj.gist.java.sort;

import java.util.Arrays;

abstract class Sort {

    abstract int[] sort(int[] array);

    void swap(int[] array, int left, int right) {
        int temp = array[left];
        array[left] = array[right];
        array[right] = temp;
    }

    static void println(int[] array) {
        System.out.println(Arrays.toString(array));
    }

    static void println(Sort sort) {
        int[][] arrs = {{3, 6, 4, 8, 5, 7, 2, 9, 1},
                {13, 65, 22, 86, 34, 89, 44, 85, 28, 84, 64, 87, 43}};
        Arrays.stream(arrs).forEach(arr -> println(sort.sort(arr)));
    }

}
