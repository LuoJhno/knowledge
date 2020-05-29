package algorithm.offer;

import java.util.Stack;

public class Algorithm31 {
    public boolean isPopOrder(int[] pushA, int[] pushB) {
        if (pushA == null || pushA.length == 0) {
            return pushB == null || pushB.length == 0;
        }
        if (pushB == null || pushB.length == 0) {
            return false;
        }
        if (pushA.length != pushB.length) {
            return false;
        }
        Stack<Integer> stack = new Stack<>();
        int popIndex = 0;
        for (int i : pushA) {
            stack.push(i);
            while (popIndex < pushA.length && !stack.isEmpty() && stack.peek() == pushB[popIndex]) {
                stack.pop();
                popIndex++;
            }
        }
        return stack.isEmpty();
    }
}