package net.hyperj.gist.java.search;

abstract class Search {

    abstract int search(int[] array, int key);

    static void println(Search search) {
        int[] arr = {13, 22, 28, 34, 43, 44, 46, 64, 65, 84, 85, 86, 87, 89};
        System.out.println(search.search(arr, 46));
        System.out.println(search.search(arr, 47));
    }

}
