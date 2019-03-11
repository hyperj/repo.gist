package net.hyperj.gist.leetcode;

import java.util.HashMap;
import java.util.Map;

public class LengthOfLongestSubstring {
    public int lengthOfLongestSubstring(String s) {
        if (s != null && s.length() > 0) {
            return maxLength(s);
        }
        return 0;
    }

    private int maxLength(String s) {
        int max = 1;
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0, t = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) {
                t = Math.max(map.get(c), t);
            }
            max = Math.max(max, i - t + 1);
            map.put(c, i + 1);
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(new LengthOfLongestSubstring().lengthOfLongestSubstring("dvdf"));
    }
}