package algorithm.offer;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import algorithm.structure.LinkNode;

public class Algorithm6 {

    /**
     * 使用递归
     * @param linkNode
     * @return
     */
    public static List<Integer> reverseLinkedList1(LinkNode linkNode) {
       List<Integer> reverseList = new ArrayList<>();
        if(linkNode!=null){
            reverseList.addAll(reverseLinkedList1(linkNode.next));
            reverseList.add(linkNode.value);
        }
        return reverseList;
    }

    /**
     * 头插法
     * @param linkNode
     * @return
     */
    public static List<Integer> reverseLinkedList2(LinkNode linkNode) {
        LinkNode head = new LinkNode(-1);
        while(linkNode!=null){
            LinkNode node = linkNode.next;
            linkNode.next = head;
            head = linkNode;
            linkNode = node;
        }
       // head = head.next;
        List<Integer> reverseList = new ArrayList<>();
        while(head!=null){
            reverseList.add(head.value);
            head=head.next;
        }
         return reverseList.subList(0, reverseList.size()-1);
     }

     /**
     * 使用栈
     * @param linkNode
     * @return
     */
    public static List<Integer> reverseLinkedList3(LinkNode linkNode) {
        Stack<Integer> stack = new Stack<>();
        while(linkNode!=null){
            stack.push(linkNode.value);
            linkNode = linkNode.next;
        }
        List<Integer> reverseList = new ArrayList<>();
        while(!stack.empty()){
            reverseList.add(stack.pop());
        }
        return reverseList;
     }


    public static void main(String[] args) {
       LinkNode linkNode1 = new LinkNode(1);
       LinkNode linkNode2 = new LinkNode(2);
       LinkNode linkNode3 = new LinkNode(3);
       linkNode1.next=linkNode2;
       linkNode2.next=linkNode3;
       linkNode3.next=null;
       System.out.println(reverseLinkedList1(linkNode1));
       System.out.println(reverseLinkedList3(linkNode1));
       System.out.println(reverseLinkedList2(linkNode1));
    }
}
