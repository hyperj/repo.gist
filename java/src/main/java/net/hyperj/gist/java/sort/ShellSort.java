package net.hyperj.gist.java.sort;

public class ShellSort extends Sort {

    public static void main(String[] args) {
        println(new ShellSort());
    }

    public int[] sort(int[] array) {
        if (array != null && array.length > 1) {
            int gap = array.length / 2;
            while (gap > 0) {
                for (int i = gap; i < array.length; i++) {
                    for (int j = i; j - gap >= 0 && array[j - gap] > array[j]; j -= gap) {
                        swap(array, j - gap, j);
                    }
                }
                gap /= 2;
            }
        }
        return array;
    }
}
