package algorithm.offer;

import java.time.LocalDateTime;

import algorithm.structure.TreeLinkNode;

public class Algorithm8 {

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

    public static void main(String[] arg) {
        LocalDateTime time1 = LocalDateTime.now();
        try{
            Thread.sleep(20);
        }catch(Exception e){
            
        }
        
        LocalDateTime time2 = LocalDateTime.now();
        int result = time1.compareTo(time2);
        System.out.println(result);
    }
}