package net.hyperj.gist.java.sort;

public class RadixSort extends Sort {

    public static void main(String[] args) {
        println(new RadixSort());
    }

    public int[] sort(int[] array) {
        if (array != null && array.length > 1) {
            int max = array[0];
            for (int i = 1; i < array.length; i++) {
                if (max < array[i]) {
                    max = array[i];
                }
            }
            int count = 1;
            while (max / 10 > 0) {
                max /= 10;
                count++;
            }
            for (int i = 1, mod = 10, div = 1; i <= count; i++, mod *= 10, div *= 10) {
                int[][] rc = new int[10][array.length];
                for (int j = 0; j < array.length; j++) {
                    int num = array[j] % mod / div;
                    rc[num][0]++;
                    rc[num][rc[num][0]] = array[j];
                }
                int index = 0;
                for (int j = 0; j < 10; j++) {
                    for (int k = 1; k <= rc[j][0]; k++) {
                        array[index++] = rc[j][k];
                    }
                }
            }
        }
        return array;
    }

}
