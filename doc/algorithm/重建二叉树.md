重建二叉树
====
[马上解题](https://www.nowcoder.com/practice/8a19cbe657394eeaac2f6ea9b0f6fcf6?tpId=13&tqId=11157&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-rankingg)

##### 题目描述   
根据二叉树的前序遍历和中序遍历的结果，重建出该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
![图解](https://upload-images.jianshu.io/upload_images/8907519-1d0b894c7136ac54.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

##### 解题思路
前序遍历的第一个值为根节点的值，使用这个值将中序遍历结果分成两部分，左部分为树的左子树中序遍历结果，右部分为树的右子树中序遍历的结果。
##### 代码
```
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
```
