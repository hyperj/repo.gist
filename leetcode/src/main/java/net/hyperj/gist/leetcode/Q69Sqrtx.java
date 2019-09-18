package net.hyperj.gist.leetcode;

public class Q69Sqrtx {
    public int mySqrt(int x) {
        if (x == 0 || x == 1) {
            return x;
        }
        int t = x / 2;
        if (t > 46340) {
            t = 46340;
        }
        while (t > x / t) {
            t = (t + x / t) / 2;
        }
        return t;
    }

    public static void main(String[] args) {
        System.out.println(new Q69Sqrtx().mySqrt(2<<16));
    }
}
