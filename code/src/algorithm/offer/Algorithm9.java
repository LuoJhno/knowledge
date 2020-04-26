package algorithm.offer;

import java.util.Stack;

public class Algorithm9 {
    Stack<Integer> stack1 = new Stack<>();
    Stack<Integer> stack2 = new Stack<>();

    public void push(int node) {
        this.stack1.push(node);
    }

    public int pop() {
        if(this.stack2.isEmpty()){
            while(!stack1.isEmpty()){
                stack2.push(stack1.pop());
            }
        }
       return stack2.pop();
    }

    public static void main(String[] arg) {

    }
}