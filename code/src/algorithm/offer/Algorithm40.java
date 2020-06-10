package algorithm.offer;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Algorithm40 {
    public List<Integer> getNMinNum(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return new ArrayList<>();
        }
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>((o1, o2) -> o2.compareTo(o1));
        for (int i : nums) {
            priorityQueue.add(i);
            if (priorityQueue.size() > k) {
                priorityQueue.poll();
            }
        }
        return new ArrayList<>(priorityQueue);
    }
}
