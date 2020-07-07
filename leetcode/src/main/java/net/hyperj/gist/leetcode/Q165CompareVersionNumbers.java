package net.hyperj.gist.leetcode;

public class Q165CompareVersionNumbers {

    public int compareVersion(String version1, String version2) {
        String[] split1 = version1.split("\\.");
        String[] split2 = version2.split("\\.");
        int length1 = split1.length;
        int length2 = split2.length;
        int length = Math.max(length1, length2);
        for (int i = 0; i < length; i++) {
            Integer int1 = i < length1 ? Integer.valueOf(split1[i]) : 0;
            Integer int2 = i < length2 ? Integer.valueOf(split2[i]) : 0;
            if (int1 < int2) {
                return -1;
            } else if (int1 > int2) {
                return 1;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        System.out.printf(String.valueOf(new Q165CompareVersionNumbers().compareVersion("1", "0")));
    }
}
