package algorithm.offer;

import algorithm.structure.TreeLinkNode;

public class Algorithm8 {

    public TreeLinkNode findNextNode(TreeLinkNode node) {
        if (null == node) {
            return null;
        }
        if (node.right != null) {
            TreeLinkNode rNode = node.right;
            while (rNode.left != null) {
                node = node.left;
            }
            return node;
        }
        while (node.next != null) {
            TreeLinkNode parent = node.next;
            if (parent.left == node) {
                return parent;
            }
            node = node.next;
        }

        return null;
    }

    public static void main(String[] arg) {

    }
}