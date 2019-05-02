package net.hyperj.gist.java.sort;

public class CountSort extends Sort {

    public static void main(String[] args) {
        println(new CountSort());
    }

    public int[] sort(int[] array) {
        if (array != null && array.length > 1) {
            int min = array[0], max = array[0];
            for (int i = 1; i < array.length; i++) {
                if (min > array[i]) {
                    min = array[i];
                } else if (max < array[i]) {
                    max = array[i];
                }
            }
            int[] count = new int[max - min + 1];
            for (int i = 0; i < array.length; i++) {
                count[array[i] - min]++;
            }
            int index = 0;
            for (int i = 0; i < count.length; i++) {
                if (count[i] > 0) {
                    for (int j = 0; j < count[i]; j++) {
                        array[index++] = i + min;
                    }
                }
            }
        }
        return array;
    }

}
