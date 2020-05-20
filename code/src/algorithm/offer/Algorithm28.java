package algorithm.offer;

import algorithm.structure.TreeNode;

public class Algorithm28 {
    public static Boolean isSymmetrical(TreeNode node) {
        if (node == null) {
            return true;
        }
        return isSymmetrical(node.left, node.right);

    }

    public static Boolean isSymmetrical(TreeNode left, TreeNode right) {
        if (left == null) {
            return right == null;
        }
        if (right == null) {
            return false;
        }
        if (left.val != right.val) {
            return false;
        }
        return isSymmetrical(left.left, right.right) && isSymmetrical(left.right, right.left);
    }

}