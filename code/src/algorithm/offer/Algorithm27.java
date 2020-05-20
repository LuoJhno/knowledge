package algorithm.offer;
import algorithm.structure.TreeNode;

public class Algorithm27 {
    public static TreeNode mirroTree(TreeNode node) {
        if (node == null) {
            return node;
        }
        TreeNode leftNode = node.left;
        node.left = node.right;
        node.right = leftNode;
        mirroTree(node.left);
        mirroTree(node.right);
        return node;
    }

}