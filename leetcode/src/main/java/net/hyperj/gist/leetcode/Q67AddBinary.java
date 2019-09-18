package net.hyperj.gist.leetcode;

public class Q67AddBinary {
    public String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int sum = 0;
        for (int i = a.length() - 1, j = b.length() - 1; i >= 0 || j >= 0; ) {
            if (i >= 0) {
                sum += a.charAt(i) - '0';
                i--;
            }
            if (j >= 0) {
                sum += b.charAt(j) - '0';
                j--;
            }
            sb.insert(0, sum % 2);
            sum = sum / 2;
        }
        if (sum > 0) sb.insert(0, 1);
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Q67AddBinary().addBinary("1010", "1011"));
    }
}
