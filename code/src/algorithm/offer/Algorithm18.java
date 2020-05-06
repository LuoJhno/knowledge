package algorithm.offer;

import algorithm.structure.ListNode;

public class Algorithm18 {

    public static ListNode deleteNode(ListNode head, ListNode tobeDelete) {
        if (head == null || tobeDelete == null) {
            return head;
        }
        if (tobeDelete.next != null) {
            ListNode next = tobeDelete.next;
            tobeDelete.next = next.next;
            tobeDelete.value = next.value;
        } else {
            if (head == tobeDelete) {
                return null;
            }
            ListNode node = head;
            while (node.next != tobeDelete) {
                node = node.next;
            }
            node.next = null;
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node1 = deleteNode(node1, node3);
        System.out.println(node1);
    }
}