package net.hyperj.gist.leetcode;

public class Q9PalindromeNumber {
    public boolean isPalindrome(int x) {
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }
        int reverted = 0;
        while (reverted < x) {
            reverted = reverted * 10 + x % 10;
            x /= 10;
        }
        return reverted == x || reverted / 10 == x;
    }

    public static void main(String[] args) {
        System.out.println(new Q9PalindromeNumber().isPalindrome(10));
    }
}
