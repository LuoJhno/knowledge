package algorithm.offer;

import algorithm.structure.TreeNode;

public class Algorithm36 {
    private TreeNode pre = null;
    private TreeNode head = null;

    public TreeNode convert(TreeNode root) {
        if (root == null) {
            return null;
        }
        inOrder(root);
        return head;
    }

    public void inOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        inOrder(node.left);
        node.left = pre;
        if (pre != null) {
            pre.right = node;
        }
        pre = node;
        if (head == null) {
            head = pre;
        }
        inOrder(node.right);
    }
}