package algorithm.offer;

import algorithm.structure.TreeNode;

public class Algorithm37 {
    private String desStr;

    public String seriable(TreeNode node) {
        if (node == null) {
            return "#";
        }
        return node.val + " " + seriable(node.left) + " " + seriable(node.right);
    }

    public TreeNode deSeriable(String str) {
        this.desStr = str;
        if (desStr.length() == 0) {
            return null;
        }
        int index = str.indexOf(" ");
        String value = index == -1 ? desStr : desStr.substring(0, index);
        desStr = index == -1 ? "" : desStr.substring(index + 1);
        if ("#".equals(value)) {
            return null;
        }
        TreeNode node = new TreeNode(Integer.parseInt(value));
        node.left = deSeriable(desStr);
        node.right = deSeriable(desStr);
        return node;
    }
}