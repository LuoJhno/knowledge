package algorithm.leetcode;

import algorithm.structure.ListNode;

public class Solution2 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode result = new ListNode(-1);
        ListNode head = result;
        boolean isCarry = false;
        while (l1 != null || l2 != null) {
            int nodeSum = (l1 == null ? 0 : l1.value) + (l2 == null ? 0 : l2.value);
            if (isCarry) {
                nodeSum++;
            }
            isCarry = nodeSum > 9;
            nodeSum = nodeSum % 10;
            ListNode node = new ListNode(nodeSum);
            result.next = node;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
            result = result.next;
        }
        if (isCarry) {
            ListNode tail = new ListNode(1);
            result.next = tail;
        }
        return head.next;
    }

    /**
     * 使用递归实现
     */
    public ListNode addTwoNumbersByRecursion(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        helper(head, l1, l2, 0);
        return head.next;
    }

    private void helper(ListNode result, ListNode l1, ListNode l2, int carry) {
        if (l1 == null && l2 == null && carry == 0)
            return;
        int sum = (l1 != null ? l1.value : 0) + (l2 != null ? l2.value : 0) + carry;
        result.next = new ListNode(sum % 10);
        carry = sum / 10;
        helper(result.next, l1 != null ? l1.next : null, l2 != null ? l2.next : null, carry);
    }


}