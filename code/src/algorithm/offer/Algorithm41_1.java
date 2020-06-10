package algorithm.offer;

import java.util.PriorityQueue;

public class Algorithm41_1 {
    private PriorityQueue<Integer> left = new PriorityQueue<>((o1, o2) -> o2.compareTo(o1));
    private PriorityQueue<Integer> right = new PriorityQueue<>();
    int count = 0;

    public void insert(int num) {
        if (count % 2 == 0) {
            left.add(num);
            right.add(left.poll());
        } else {
            right.add(num);
            left.add(right.poll());
        }
        count++;
    }

    public double getMedia() {
        if (count % 2 == 0) {
            return (left.peek() + right.peek()) / 2.0;
        } else {
            return right.peek();
        }
    }
}
