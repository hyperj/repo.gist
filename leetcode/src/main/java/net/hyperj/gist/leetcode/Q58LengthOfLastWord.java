package net.hyperj.gist.leetcode;

public class Q58LengthOfLastWord {
    public int lengthOfLastWord(String s) {
        if (s != null) {
            int start = s.length() - 1;
            while (start >= 0 && s.charAt(start) == ' ') {
                start -= 1;
            }
            if (start < 0) return 0;
            for (int i = start - 1; i >= 0; i--) {
                if (s.charAt(i) == ' ') {
                    if (start >= 0) {
                        return start - i;
                    } else {
                        start = i;
                    }
                }
            }
            return start + 1;
        }
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(new Q58LengthOfLastWord().lengthOfLastWord("a"));
    }
}
