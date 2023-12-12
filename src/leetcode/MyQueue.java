package leetcode;

import java.util.*;

class MyQueue {

    Deque<Integer> stack;

    public MyQueue() {
        stack = new LinkedList<>();
    }

    public void push(int x) {
        stack.push(x);
    }

    public int pop() {
        Deque<Integer> stack2 = new LinkedList<>();
        while (stack.size() > 1) {
            stack2.push(stack.pop());
        }
        int peek = stack.pop();

        while (!stack2.isEmpty()) {
            stack.push(stack2.pop());
        }

        return peek;
    }

    public int peek() {
        Deque<Integer> stack2 = new LinkedList<>();
        while (stack.size() > 1) {
            stack2.push(stack.pop());
        }
        int peek = stack.pop();

        stack2.push(peek);

        while (!stack2.isEmpty()) {
            stack.push(stack2.pop());
        }

        return peek;
    }

    public boolean empty() {
        return stack.isEmpty();
    }
}