package net.hyperj.gist.java.sort;

public class InsertionSort extends Sort {

    public static void main(String[] args) {
        println(new InsertionSort());
    }

    public int[] sort(int[] array) {
        if (array != null && array.length > 1) {
            for (int i = 0; i < array.length - 1; i++) {
                for (int j = i; j >= 0 && array[j] > array[j + 1]; j--) {
                    swap(array, j, j + 1);
                }
            }
        }
        return array;
    }
}
