package net.hyperj.gist.java.sort;

public class QuickSort extends Sort {

    public static void main(String[] args) {
        println(new QuickSort());
    }

    public int[] sort(int[] array) {
        if (array != null && array.length > 1) {
            quick(array, 0, array.length - 1);
        }
        return array;
    }

    private void quick(int[] array, int start, int end) {
        int middle = middle(array, start, end);
        if (middle > start) {
            quick(array, start, middle - 1);
        }
        if (middle < end) {
            quick(array, middle + 1, end);
        }
    }

    private int middle(int[] array, int start, int end) {
        int pivot = (int) (start + Math.random() * (end - start + 1));
        swap(array, pivot, end);
        int mid = start - 1;
        for (int i = start; i <= end; i++) {
            if (array[i] <= array[end]) {
                mid++;
                if (i > mid) {
                    swap(array, i, mid);
                }
            }
        }
        return mid;
    }
}
