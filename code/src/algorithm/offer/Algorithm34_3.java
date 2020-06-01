package algorithm.offer;

import algorithm.structure.TreeNode;

public class Algorithm34_3 {

    public static int pathSum(TreeNode root, int target) {
        if (root == null) {
            return 0;
        }
        return pathSumFrom(root, target) + pathSum(root.left, target) + pathSum(root.right, target);
    }

    public static int pathSumFrom(TreeNode node, int target) {
        if (node == null) {
            return 0;
        }
        return ((node.val == target) ? 1 : 0) + pathSum(node.left, target - node.val)
                + pathSum(node.right, target - node.val);
    }
}