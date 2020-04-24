package algorithm.offer;

import java.util.*;

import algorithm.structure.TreeNode;

public class Algrithm7 {
    private static Map<Integer, Integer> indexMapForInOrders = new HashMap<>();

    public static TreeNode reConstrucTree(int[] pre, int[] in) {
        for (int i = 0; i < in.length; i++) {
            indexMapForInOrders.put(in[i], i);
        }
        return reConstrucTree(pre, 0, pre.length - 1, 0);
    }

    public static TreeNode reConstrucTree(int[] pre, int preL, int preR, int inL) {
        if (preL > preR) {
            return null;
        }
        TreeNode root = new TreeNode(pre[preL]);
        int inIndex = indexMapForInOrders.get(root.val);
        int leftTreeSize = inIndex - inL;
        root.left = reConstrucTree(pre, preL + 1, preL + leftTreeSize, inL);
        root.right = reConstrucTree(pre, preL + leftTreeSize + 1, preR, inL + leftTreeSize + 1);
        return root;
    }

    public static void main(String[] arg) {
        int[] pre = { 3, 9, 20, 15, 7 };
        int[] in = { 9, 3, 15, 20, 7 };
        TreeNode node = reConstrucTree(pre, in);
        System.out.println(node);

    }
}