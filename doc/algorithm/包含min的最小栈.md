包含min的最小栈
====
[马上解题](https://www.nowcoder.com/practice/4c776177d2c04c2494f2555c9fcc1e49?tpId=13&tqId=11173&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking)

##### 题目描述   
定义栈的数据结构，请在该类型中实现一个能够得到栈最小元素的 min 函数。

##### 解题思路


##### 代码
```java
private Stack<Integer> stack = new Stack<>();
private int min = Integer.MAX_VALUE;

public void push(int num) {
    if (num < min) {
        stack.push(min);
    }
    stack.push(num);
}

public int pop() {
    int num = stack.pop();
    if (num == min) {
        min = stack.pop();
    }
    return num;
}

public int top() {
    return stack.peek();
}

public int getMin() {
    return this.min;
}
```