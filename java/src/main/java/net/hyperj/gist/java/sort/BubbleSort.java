package net.hyperj.gist.java.sort;

public class BubbleSort extends Sort {

    public static void main(String[] args) {
        println(new BubbleSort());
    }

    public int[] sort(int[] array) {
        if (array != null && array.length > 1) {
            for (int i = 0; i < array.length - 1; i++) {
                for (int j = 0; j < array.length - 1 - i; j++) {
                    if (array[j] > array[j + 1]) {
                        swap(array, j, j + 1);
                    }
                }
            }
        }
        return array;
    }
}
