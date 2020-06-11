package algorithm.offer;

import java.util.LinkedList;
import java.util.Queue;

public class Algorithm41_2 {
    private Queue<Character> queue = new LinkedList<>();

    int[] count = new int[256];

    public void inser(char ch) {
        count[ch]++;
        queue.add(ch);
        while (!queue.isEmpty() && count[queue.peek()] > 1) {
            queue.poll();
        }
    }

    public char getFirstAppearingOnce() {
        return queue.isEmpty() ? '#' : queue.peek();
    }

}
