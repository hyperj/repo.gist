package net.hyperj.gist.java.sort;

public class SelectionSort extends Sort {

    public static void main(String[] args) {
        println(new SelectionSort());
    }

    public int[] sort(int[] array) {
        if (array != null && array.length > 1) {
            for (int i = 0; i < array.length - 1; i++) {
                int min = i;
                for (int j = i; j < array.length; j++) {
                    if (array[j] < array[min]) {
                        min = j;
                    }
                }
                swap(array, min, i);
            }
        }
        return array;
    }
}
