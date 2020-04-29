二进制中1的个数
====
[马上解题](https://www.nowcoder.com/practice/8ee967e43c2c4ec193b040ea7fbb10b8?tpId=13&tqId=11164&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking)

##### 题目描述   
输入一个整数，输出该数二进制表示中 1 的个数。。

##### 解题思路
n&(n-1)
该位运算去除 n 的位级表示中最低的那一位。
n      : 10110100
n-1    : 10110011
n&(n-1) : 10110000
时间复杂度：O(M)，其中 M 表示 1 的个数。
##### 代码
```
  public static int numberOf1(int n) {
        int count = 0;
        while (n > 0) {
            if ((n & 1) > 0) {
                count++;
            }
            n >>= 1;
        }
        return count;
    }

    public static int numberOf1Of2(int n) {
        int i = 1;
        int count = 0;
        while (i != 0) {
            if ((n & i) > 0) {
                count++;
            }
            i <<= 1;
        }
        return count;
    }

    public static int numberOf1Of3(int n) {
        int count = 0;
        while (n != 0) {
            count++;
            n = n & (n - 1);
        }
        return count;
    }
```
