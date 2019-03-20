package net.hyperj.gist.java.code.stackandqueue;

import java.util.Stack;

public class TwoStacksImplementQueue {

    public static void main(String[] args) {
        TwoStacksQueue test = new TwoStacksQueue();
        test.add(1);
        test.add(2);
        System.out.println(test.peek());
        System.out.println(test.poll());
        System.out.println(test.peek());
        System.out.println(test.poll());
        test.add(3);
        System.out.println(test.peek());
        System.out.println(test.poll());
    }

}

class TwoStacksQueue {
    public Stack<Integer> push;
    public Stack<Integer> pop;

    public TwoStacksQueue() {
        push = new Stack<>();
        pop = new Stack<>();
    }

    public void add(int num) {
        push.push(num);
    }

    public int poll() {
        trans();
        return pop.pop();
    }

    public int peek() {
        trans();
        return pop.peek();
    }

    private void trans() {
        if (push.isEmpty() && pop.isEmpty()) {
            throw new RuntimeException("Queue is empty!");
        } else if (pop.isEmpty()) {
            while (!push.isEmpty()) {
                pop.push(push.pop());
            }
        }
    }
}
