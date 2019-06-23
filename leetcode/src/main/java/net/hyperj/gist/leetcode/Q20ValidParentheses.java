package net.hyperj.gist.leetcode;

import java.util.Stack;

public class Q20ValidParentheses {

    public boolean isValid(String s) {
        String left = "([{";
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (left.indexOf(c) >= 0) {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                } else {
                    if (c - stack.peek() > 0 && c - stack.peek() < 3) {
                        stack.pop();
                    } else {
                        return false;
                    }
                }
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println(new Q20ValidParentheses().isValid("()[]{}"));

    }
}
