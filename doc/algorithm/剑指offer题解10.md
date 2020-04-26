动态规划系列
====

####  斐波那契数列 

[马上解题](https://www.nowcoder.com/practice/c6c7742f5ba7442aada113136ddea0c3?tpId=13&tqId=11160&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking)

##### 题目描述   
求斐波那契数列的第 n 项，n <= 39。
![图解](https://upload-images.jianshu.io/upload_images/8907519-60cd8f1ba96ece06.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

##### 解题思路
1. 使用递归求解，会重复计算一些子问题。例如，计算 f(4) 需要计算 f(3) 和 f(2)，计算 f(3) 需要计算 f(2) 和 f(1)，可以看到 f(2) 被重复计算了。
![图解](https://upload-images.jianshu.io/upload_images/8907519-f068abf27cd88225.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
2. 使用动态规划，递归是将一个问题划分成多个子问题求解，动态规划也是如此，但是动态规划会把子问题的解缓存起来，从而避免重复求解子问题。
##### 代码
```
    /**
     * 递归求解
     * @param n
     * @return
     */
    public static int febonacci(int n) {
        if (n <= 2) {
            return n;
        }
        return febonacci(n - 1) + febonacci(n - 2);
    }

    /**
     * 空间复杂度 O(N) 时间复杂度O(N)
     * 
     * @param n
     * @return
     */
    public static int febonacciWithDynamicProgram1(int n) {
        int[] resultArr = new int[n];
        resultArr[0] = 1;
        resultArr[1] = 2;
        for (int i = 2; i < n; i++) {
            resultArr[i] = resultArr[i - 1] + resultArr[i - 2];
        }
        return resultArr[n - 1];
    }

    /**
     * 空间复杂度 O(1) 时间复杂度O(N)
     * 
     * @param n
     * @return
     */
    public static int febonacciWithDynamicProgram2(int n) {

        int pre1 = 1;
        int pre2 = 2;
        int result = 0;
        for (int i = 2; i < n; i++) {
            result = pre1 + pre2;
            pre1 = pre2;
            pre2 = result;
        }
        return result;
    }

```

####  矩形覆盖 
[马上解题](https://www.nowcoder.com/practice/72a5a919508a4251859fb2cfb987a0e6?tpId=13&tqId=11163&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking)

##### 题目描述   
我们可以用 2*1 的小矩形横着或者竖着去覆盖更大的矩形。请问用 n 个 2*1 的小矩形无重叠地覆盖一个 2*n 的大矩形，总共有多少种方法？


##### 解题思路
当 n 为 1 时，只有一种覆盖方法：
![图解1](https://upload-images.jianshu.io/upload_images/8907519-24ed735024308f3c.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
当 n 为 2 时，有两种覆盖方法：
![图解2](https://upload-images.jianshu.io/upload_images/8907519-db7eb683ab873a68.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
要覆盖 2 * n的大矩形，可以先覆盖 2 * 1 的矩形，再覆盖 2 * (n-1) 的矩形；或者先覆盖 2 * 2 的矩形，再覆盖 2 * (n-2) 的矩形。而覆盖 2 * (n-1) 和 2 * (n-2) 的矩形可以看成子问题。该问题的递推公式如下：
![图解3](https://upload-images.jianshu.io/upload_images/8907519-e88fcebfea11702f.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
##### 代码
```
    /**
     * 空间复杂度 O(1) 时间复杂度O(N)
     * 
     * @param n
     * @return
     */
    public static int reactCover(int n) {

        int pre1 = 1;
        int pre2 = 2;
        int result = 0;
        for (int i = 2; i < n; i++) {
            result = pre1 + pre2;
            pre1 = pre2;
            pre2 = result;
        }
        return result;
    }

```


####  跳台阶  
[马上解题](https://www.nowcoder.com/practice/8c82a5b80378478f9484d87d1c5f12a4?tpId=13&tqId=11161&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking)

##### 题目描述   
一只青蛙一次可以跳上 1 级台阶，也可以跳上 2 级。求该青蛙跳上一个 n 级的台阶总共有多少种跳法。
![图解](https://upload-images.jianshu.io/upload_images/8907519-a37bbd9a69e4b6bf.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


##### 解题思路
跳 n 阶台阶，可以先跳 1 阶台阶，再跳 n-1 阶台阶；或者先跳 2 阶台阶，再跳 n-2 阶台阶。而 n-1 和 n-2 阶台阶的跳法可以看成子问题，该问题的递推公式为：
![图解](https://upload-images.jianshu.io/upload_images/8907519-9c0035dbe71f7fb3.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
##### 代码
```
    /**
     * 空间复杂度 O(1) 时间复杂度O(N)
     * 
     * @param n
     * @return
     */
    public static int reactCover(int n) {

        int pre1 = 1;
        int pre2 = 2;
        int result = 0;
        for (int i = 2; i < n; i++) {
            result = pre1 + pre2;
            pre1 = pre2;
            pre2 = result;
        }
        return result;
    }

```


####  变态跳台阶  
[马上解题](https://www.nowcoder.com/practice/22243d016f6b47f2a6928b4313c85387?tpId=13&tqId=11162&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking)

##### 题目描述   
一只青蛙一次可以跳上 1 级台阶，也可以跳上 2 级... 它也可以跳上 n 级。求该青蛙跳上一个 n 级的台阶总共有多少种跳法。
![图解](https://upload-images.jianshu.io/upload_images/8907519-25fd95f9f12d132a.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


##### 解题思路
跳上 n-1 级台阶，可以从 n-2 级跳 1 级上去，也可以从 n-3 级跳 2 级上去...，那么
f(n-1) = f(n-2) + f(n-3) + ... + f(0)
同样，跳上 n 级台阶，可以从 n-1 级跳 1 级上去，也可以从 n-2 级跳 2 级上去... ，那么
f(n) = f(n-1) + f(n-2) + ... + f(0)
##### 代码
```
  public static int jumpFloor2(int n) {
        int[] resultArr = new int[n];
        resultArr[0] = 1;
        resultArr[1] = 2;
        for (int i = 2; i < n; i++) {
            for (int j = 0; j < i; j++) {
                resultArr[i] += resultArr[j];
            }
        }
        return resultArr[n - 1];

    }

```