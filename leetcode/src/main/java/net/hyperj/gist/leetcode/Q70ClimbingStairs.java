package net.hyperj.gist.leetcode;

public class Q70ClimbingStairs {
    public int climbStairs(int n) {
        if (n < 4) {
            return n;
        }
        int s = 2, t = 3;
        while (--n > 2) {
            t += s;
            s = t - s;
        }
        return t;
    }

    public static void main(String[] args) {
        System.out.println(new Q70ClimbingStairs().climbStairs(4));
    }
}
