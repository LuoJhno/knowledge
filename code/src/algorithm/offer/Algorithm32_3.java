package algorithm.offer;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;

import algorithm.structure.TreeNode;

public class Algorithm32_3 {
    public List<List<Integer>> levelOfZOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        boolean revert = false;
        while (!queue.isEmpty()) {
            int size = queue.size();
            int index = 0;
            List<Integer> row = new ArrayList<>();
            while (index < size) {
                TreeNode node = queue.poll();
                row.add(node.val);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
                index++;
            }
            if (revert) {
                Collections.reverse(row);
            }
            revert = !revert;
            result.add(row);
        }
        return result;
    }
}