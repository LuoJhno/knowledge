package algorithm.structure;

public class TreeLinkNode {
    public int val;
    public TreeLinkNode left = null;// 左节点
    public TreeLinkNode right = null;// 右节点
    public TreeLinkNode next = null;// 父节点

    TreeLinkNode(int val) {
        this.val = val;
    }
}