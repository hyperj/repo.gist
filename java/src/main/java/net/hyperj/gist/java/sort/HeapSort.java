package net.hyperj.gist.java.sort;

public class HeapSort extends Sort {

    public static void main(String[] args) {
        println(new HeapSort());
    }

    public int[] sort(int[] array) {
        if (array != null && array.length > 1) {
            int maxIndex = array.length - 1;
            for (int i = 0; i < array.length; i++) {
                adjust(array, maxIndex - i);
                swap(array, 0, maxIndex - i);
            }
        }
        return array;
    }

    private void adjust(int[] array, int maxIndex) {
        for (int i = (maxIndex - 1) / 2; i >= 0; i--) {
            int k = i;
            while (k * 2 + 1 <= maxIndex) {
                int biggerIndex = 2 * k + 1;
                if (biggerIndex < maxIndex && array[biggerIndex] < array[biggerIndex + 1]) {
                    biggerIndex++;
                }
                if (array[k] < array[biggerIndex]) {
                    swap(array, k, biggerIndex);
                    k = biggerIndex;
                } else {
                    break;
                }
            }
        }
    }

}
