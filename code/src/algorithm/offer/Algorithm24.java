package algorithm.offer;

import algorithm.structure.ListNode;

public class Algorithm24 {
    public static ListNode revertNodeist1(ListNode head) {
        if(head==null||head.next==null){
            return head;
        }
        ListNode next = head.next;
        head.next = null;
        ListNode newNode = revertNodeist1(next);
        next.next = head;
        return newNode;
    }

    public static ListNode revertNodeist2(ListNode head) {
        if(head==null||head.next==null){
            return head;
        }
        ListNode node = new ListNode(-1);
        while(head!=null){
            ListNode next = head.next;
            head.next = node.next;
            node.next =head;
            head = next;
        }
        return node.next;
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

        System.out.println(revertNodeist1(node1));
    }
}