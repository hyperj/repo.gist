package net.hyperj.gist.leetcode;

public class Q13RomanToInteger {

    final static int I = 1;
    final static int V = 5;
    final static int X = 10;
    final static int L = 50;
    final static int C = 100;
    final static int D = 500;
    final static int M = 1000;

    public int romanToInt(String s) {
        int res = 0;
        if (s.contains("CM") || s.contains("CD")) {
            res -= 200;
        }
        if (s.contains("XC") || s.contains("XL")) {
            res -= 20;
        }
        if (s.contains("IX") || s.contains("IV")) {
            res -= 2;
        }
        while (s.length() > 0) {
            if (s.indexOf("M") == 0) {
                res += M;
                s = s.substring(1);
            }
            if (s.indexOf("D") == 0) {
                res += D;
                s = s.substring(1);
            }
            if (s.indexOf("C") == 0) {
                res += C;
                s = s.substring(1);
            }
            if (s.indexOf("L") == 0) {
                res += L;
                s = s.substring(1);
            }
            if (s.indexOf("X") == 0) {
                res += X;
                s = s.substring(1);
            }
            if (s.indexOf("V") == 0) {
                res += V;
                s = s.substring(1);
            }
            if (s.indexOf("I") == 0) {
                res += I;
                s = s.substring(1);
            }
        }
        return res;
    }

    public static void main(String args[]) {
        System.out.println(new Q13RomanToInteger().romanToInt("MCMIV"));
    }
}
