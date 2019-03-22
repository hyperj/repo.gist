package net.hyperj.gist.java.code.stackandqueue;

import java.util.Stack;

public class StackSortStack {

    public static Stack<Integer> sortStackByStack(Stack<Integer> stack) {
        Stack<Integer> help = new Stack<>();
        while (!stack.isEmpty()) {
            int tmp = stack.pop();
            while (!help.isEmpty() && help.peek() < tmp) {
                stack.push(help.pop());
            }
            help.push(tmp);
        }
        return help;
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(3);
        stack.push(1);
        stack.push(6);
        stack.push(2);
        stack.push(5);
        stack.push(4);
        Stack<Integer> sort = sortStackByStack(stack);
        while (!sort.isEmpty()) {
            System.out.println(sort.pop());
        }
    }

}
