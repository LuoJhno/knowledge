二叉树的下一个节点
====
[马上解题](https://www.nowcoder.com/practice/9023a0c988684a53960365b889ceaf5e?tpId=13&tqId=11210&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking)

##### 题目描述   
给定一个二叉树和其中的一个结点，请找出中序遍历顺序的下一个结点并且返回。注意，树中的结点不仅包含左右子结点，同时包含指向父结点的指针。
```
public class TreeLinkNode{
    int val;
    TreeLinkNode left=null;//左节点
    TreeLinkNode right=null;//右节点
    TreeLinkNode next=null;//父节点
    TreeLinkNode(int val) {
        this.val=val;    
    }
}
```

##### 解题思路
如果一个节点的右子树不为空，那么该节点的下一个节点是右子树的最左节点；
否则，向上找第一个左链接指向的树包含该节点的祖先节点。
##### 代码
```
    public TreeLinkNode findNextNode(TreeLinkNode node) {
        if (null == node) {
            return null;
        }
        if (node.right != null) {
            TreeLinkNode rNode = node.right;
            while (rNode.left != null) {
                node = node.left;
            }
            return node;
        }
        while (node.next != null) {
            TreeLinkNode parent = node.next;
            if (parent.left == node) {
                return parent;
            }
            node = node.next;
        }

        return null;
    }
```
