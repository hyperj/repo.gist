package net.hyperj.gist.java.sort;

public class BucketSort extends Sort {

    public static void main(String[] args) {
        println(new BucketSort());
    }

    public int[] sort(int[] array) {
        return sort(array, 10);
    }

    public int[] sort(int[] array, int bucket) {
        if (array != null && array.length > 1) {
            int min = array[0], max = array[0];
            for (int i = 1; i < array.length; i++) {
                if (min > array[i]) {
                    min = array[i];
                } else if (max < array[i]) {
                    max = array[i];
                }
            }
            int count = 1;
            if (bucket > 0) {
                if (bucket > array.length) {
                    bucket = array.length;
                }
                if (bucket > max - min + 1) {
                    bucket = max - min + 1;
                }
                count = (max - min + 1) / (bucket - 1);
            }
            int[][] bc = new int[bucket][count];
            for (int i = 0; i < array.length; i++) {
                bc[(array[i] - min) / count][(array[i] - min) % count]++;
            }
            int index = 0;
            for (int i = 0; i < bucket; i++) {
                for (int k = 0; k < count; k++) {
                    if (bc[i][k] > 0) {
                        array[index++] = i * count + k + min;
                    }
                }
            }
        }
        return array;
    }

}
