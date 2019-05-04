package net.hyperj.gist.java.search;

public class BinarySearch extends Search {

    public static void main(String[] args) {
        println(new BinarySearch());
    }

    int search(int[] array, int key) {
        if (array != null && array.length > 0) {
            int low = 0, high = array.length - 1;
            while (low <= high) {
                int mid = (low + high) / 2;
                if (key == array[mid]) {
                    return mid;
                } else if (key < array[mid]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
        }
        return -1;
    }
}
