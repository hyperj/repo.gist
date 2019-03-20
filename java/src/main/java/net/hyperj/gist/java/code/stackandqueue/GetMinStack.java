package net.hyperj.gist.java.code.stackandqueue;

import java.util.Arrays;
import java.util.Stack;

import static java.lang.System.out;

public class GetMinStack {

    public static void main(String[] args) {
        MinStack[] mins = {new MinStack1(), new MinStack2()};
        Arrays.stream(mins).forEach(stack -> {
            out.println(stack.getClass().getName());
            stack.push(3);
            out.println(stack.min());
            stack.push(4);
            out.println(stack.min());
            stack.push(2);
            out.println(stack.min());
            stack.push(2);
            out.println(stack.min());
            stack.push(1);
            out.println(stack.min());
            out.println(stack.pop());
            out.println(stack.min());
        });
    }

}

interface MinStack {

    void push(int num);

    int pop();

    int min();

}

class MinStack1 implements MinStack {

    Stack<Integer> data;
    Stack<Integer> min;

    MinStack1() {
        data = new Stack<>();
        min = new Stack<>();
    }

    @Override
    public void push(int num) {
        if (data.isEmpty()) {
            min.push(num);
        } else if (num <= min()) {
            min.push(num);
        } else {
            min.push(min());
        }
        data.push(num);
    }

    @Override
    public int pop() {
        if (data.isEmpty()) {
            throw new RuntimeException("Stack is empty.");
        }
        min.pop();
        return data.pop();
    }

    @Override
    public int min() {
        if (data.isEmpty()) {
            throw new RuntimeException("Stack is empty.");
        }
        return min.peek();
    }
}

class MinStack2 implements MinStack {

    Stack<Integer> data;
    Stack<Integer> min;

    MinStack2() {
        data = new Stack<>();
        min = new Stack<>();
    }

    @Override
    public void push(int num) {
        if (data.isEmpty()) {
            min.push(num);
        } else if (num < min()) {
            min.push(num);
        }
        data.push(num);
    }

    @Override
    public int pop() {
        if (data.isEmpty()) {
            throw new RuntimeException("Stack is empty.");
        } else if (data.peek() == min()) {
            min.pop();
        }
        return data.pop();
    }

    @Override
    public int min() {
        if (data.isEmpty()) {
            throw new RuntimeException("Stack is empty.");
        }
        return min.peek();
    }
}