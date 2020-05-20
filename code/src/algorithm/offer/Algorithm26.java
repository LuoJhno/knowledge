package algorithm.offer;
import algorithm.structure.TreeNode;

public class Algorithm26 {
    public static boolean isChildrenTree(TreeNode node1, TreeNode node2) {
        if (node1 == null || node2 == null) {
            return false;
        }
        return isEquasTree(node1, node2) || isChildrenTree(node1.left, node2) || isChildrenTree(node1.right, node2);

    }

    public static boolean isEquasTree(TreeNode node1, TreeNode node2) {
        if (node1 == null) {
            return node2 == null;
        }
        if (node2 == null) {
            return true;
        }
        if (node1.val != node2.val) {
            return false;
        }
        return isEquasTree(node1.left, node2.left) && isEquasTree(node1.right, node2.right);
    }

}