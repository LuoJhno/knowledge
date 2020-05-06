package algorithm.offer;

import algorithm.structure.ListNode;

public class Algorithm18_2 {

    public static ListNode deleteDuplication(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        
        if (head.value == head.next.value) {
            // 直接指向下一节点
            ListNode next = head.next;
            while (next!=null&&next.value == head.value) {
                next = next.next;
            }
            // 返回的是非重复的节点开始的ListNode
            return deleteDuplication(next);
        } else {
            head.next = deleteDuplication(head.next);
            return head;
        }
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node2_1 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node3_1 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        ListNode node5_1 = new ListNode(5);
        node1.next = node2;
        node2.next = node2_1;
        node2_1.next = node3;
        node3.next = node3_1;
        node3_1.next = node4;
        node4.next = node5;
        node5.next = node5_1;
        node1 = deleteDuplication(node1);
        System.out.println(node1);
    }
}