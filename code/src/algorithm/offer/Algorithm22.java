package algorithm.offer;

import algorithm.structure.ListNode;

public class Algorithm22 {
    public static ListNode findKthTrail(ListNode head, int k) {
        if (head == null || k == 0) {
            return null;
        }
        ListNode firstNode = head;
        for (int i = 0; i < k - 1; i++) {
            if (firstNode.next != null) {
                firstNode = firstNode.next;
            } else {
                return null;
            }
        }

        ListNode secondeNode = head;
        while (firstNode.next != null) {
            firstNode = firstNode.next;
            secondeNode = secondeNode.next;
        }
        return secondeNode;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        ListNode node6 = new ListNode(6);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        System.out.println(findKthTrail(node1, 3));
    }
}