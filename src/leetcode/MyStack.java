package leetcode;

import java.util.*;

class MyStack {

    private Queue<Integer> queue;

    public MyStack() {
        queue = new LinkedList<>();
    }

    public void push(int x) {
        queue.add(x);
    }

    public int pop() {
        int currSize = queue.size();
        Queue<Integer> queue2 = new LinkedList<>();

        for (int i = 0; i < currSize - 1; i++) {
            queue2.add(queue.poll());
        }

        int topValue = queue.poll();
        queue = queue2;

        return topValue;
    }

    public int top() {
        int currSize = queue.size();
        Queue<Integer> queue2 = new LinkedList<>();

        for (int i = 0; i < currSize - 1; i++) {
            queue2.add(queue.poll());
        }

        int topValue = queue.poll();
        queue2.add(topValue);
        queue = queue2;

        return topValue;
    }

    public boolean empty() {
        return queue.isEmpty();
    }
}

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */