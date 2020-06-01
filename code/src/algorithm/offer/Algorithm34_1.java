package algorithm.offer;

import java.util.ArrayList;
import java.util.List;

import algorithm.structure.TreeNode;

public class Algorithm34_1 {
    public static List<List<Integer>> result = new ArrayList<>();

    public static List<List<Integer>> findPath(TreeNode root, int target) {
        bracking(root, target, new ArrayList<Integer>());
        return result;
    }

    public static void bracking(TreeNode node, int target, List<Integer> list) {
        if (node == null) {
            return;
        }
        list.add(node.val);
        target -= node.val;
        if (target == 0 && node.left == null && node.right == null) {
            result.add(list);
        } else {
            bracking(node.left, target, list);
            bracking(node.right, target, list);
        }
        list.remove(list.size() - 1);

    }
}