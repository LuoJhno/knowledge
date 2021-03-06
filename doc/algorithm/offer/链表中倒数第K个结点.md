链表中倒数第K个结点
====


##### 题目描述   


##### 解题思路
设链表的长度为 N。设置两个指针 P1 和 P2，先让 P1 移动 K 个节点，则还有 N - K 个节点可以移动。此时让 P1 和 P2 同时移动，可以知道当 P1 移动到链表结尾时，P2 移动到第 N - K 个节点处，该位置就是倒数第 K 个节点。
![图片](https://upload-images.jianshu.io/upload_images/8907519-6bd359f514343f30.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

##### 代码
```java
public ListNode findKthTrail(ListNode head, int k) {
        if (head == null || k == 0) {
            return null;
        }
        ListNode firstNode = head;
        for (int i = 0; i < k - 1; i++) {
            if (firstNode.next != null) {
                firstNode = firstNode.next;
            } else {
                return null;
            }
        }

        ListNode secondeNode = head;
        while (firstNode.next != null) {
            firstNode = firstNode.next;
            secondeNode = secondeNode.next;
        }
        return secondeNode;
    }
```