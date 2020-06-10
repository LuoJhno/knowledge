包含min的最小栈
====


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