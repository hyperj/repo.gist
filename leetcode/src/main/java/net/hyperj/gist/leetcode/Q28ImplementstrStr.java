package net.hyperj.gist.leetcode;

public class Q28ImplementstrStr {

    public int strStr(String haystack, String needle) {
        if (haystack != null && needle != null) {
            if ("".equals(needle)) {
                return 0;
            }
            return haystack.indexOf(needle);
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(new Q28ImplementstrStr().strStr("hello", "ll"));
    }
}
