package net.hyperj.gist.leetcode;

 public class Q7ReverseInteger {

     public static void main(String[] args) {
        System.out.println(new Q7ReverseInteger().reverse(-23));
    }

     public int reverse(int x) {
        long res = 0;
        while (x != 0) {
            res = res * 10 + x % 10;
            x /= 10;
        }
        if (res >= Integer.MAX_VALUE || res <= Integer.MIN_VALUE) {
            return 0;
        }
        return (int) res;
    }
}