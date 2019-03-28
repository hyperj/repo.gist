package net.hyperj.gist.java.code.stackandqueue;

import java.util.Arrays;
import java.util.LinkedList;

public class SlidingWindowMaxArray {

    public static int[] getSlidingWindowMax(int[] arr, int w) {
        if (arr == null || w < 1 || arr.length < w) {
            return null;
        }
        int[] res = new int[arr.length - w + 1];
        LinkedList<Integer> maxList = new LinkedList<>();
        for (int i = 0; i < arr.length; i++) {
            maxList.addFirst(i);
            if (maxList.size() > w) {
                maxList.pollLast();
            }
            while (!maxList.isEmpty() && arr[maxList.peekLast()] < arr[i]) {
                maxList.pollLast();
            }
            if (i >= w - 1) {
                res[i - w + 1] = arr[maxList.peekLast()];
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = {2, 3, 5, 4, 3, 3, 6, 7};
        int w = 7;
        System.out.println(Arrays.toString(getSlidingWindowMax(arr, w)));
    }

}
