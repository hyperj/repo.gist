package net.hyperj.gist.java.code.stackandqueue;

import java.util.Stack;

public class ReverseStackUsingRecursive {

    public static void reverse(Stack<Integer> stack) {
        if (stack.isEmpty()) {
            return;
        }
        int item = exchange(stack, stack.pop());
        reverse(stack);
        stack.push(item);
    }

    private static int exchange(Stack<Integer> stack, int num) {
        if (stack.isEmpty()) {
            return num;
        }
        int next = exchange(stack, stack.pop());
        stack.push(num);
        return next;
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        Stack<Integer> temp = (Stack<Integer>) stack.clone();
        while (!temp.isEmpty()) {
            System.out.println(temp.pop());
        }
        reverse(stack);
        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }

}
