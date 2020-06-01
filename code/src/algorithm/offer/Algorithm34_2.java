package algorithm.offer;

import algorithm.structure.TreeNode;

public class Algorithm34_2 {

    public static boolean hasPathSum(TreeNode root, int target) {
        if (root == null) {
            return false;
        }
        if (root.val == target && root.left == null && root.right == null) {
            return true;
        }
        return hasPathSum(root.left, target - root.val) || hasPathSum(root.right, target - root.val);
    }
}