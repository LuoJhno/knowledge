package algorithm.offer;

import algorithm.structure.RandomListNode;

public class Algorithm35 {
    public static RandomListNode copyRandomListNode(RandomListNode root) {
        if (root == null) {
            return null;
        }
        RandomListNode currNode = root;
        while (currNode != null) {
            RandomListNode node = new RandomListNode(currNode.val);
            node.next = currNode.next;
            currNode.next = node;
            currNode = node.next;
        }
        currNode = root;
        while (currNode != null) {
            RandomListNode next = currNode.next;
            if (currNode.next != null) {
                next.random = currNode.random.next;
            }
            currNode = next.next;
        }

        currNode = root;
        RandomListNode newRoot = currNode.next;
        while (currNode.next != null) {
            RandomListNode node = currNode.next;
            currNode.next = node.next;
            currNode = node;
        }

        return newRoot;
    }

    public static void main(String[] args) {
        RandomListNode node1 = new RandomListNode(1);
        RandomListNode node2 = new RandomListNode(2);
        RandomListNode node3 = new RandomListNode(3);
        RandomListNode node4 = new RandomListNode(4);
        RandomListNode node5 = new RandomListNode(5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node1.random = node4;
        node2.random = node5;
        node3.random = node1;
        node4.random = node2;
        node5.random = node3;
        RandomListNode newNode = copyRandomListNode(node1);
        System.out.println(newNode);
    }
}
