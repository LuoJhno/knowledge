package algorithm.offer;

import java.util.Stack;

public class Algorithm30 {
    private Stack<Integer> stack = new Stack<>();
    private int min = Integer.MAX_VALUE;

    public void push(int num) {
        if (num < min) {
            stack.push(min);
        }
        stack.push(num);
    }

    public int pop() {
        int num = stack.pop();
        if (num == min) {
            min = stack.pop();
        }
        return num;
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return this.min;
    }
}