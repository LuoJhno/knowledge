替换空格
====
[马上解题](https://www.nowcoder.com/practice/4060ac7e3e404ad1a894ef3e17650423?tpId=13&tqId=11155&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking)

##### 题目描述
将一个字符串中的空格替换成 "%20"。
```
Input:
"A B"
Output:
"A%20B"
```
###### 解题思路
在Java中String提供了现成的api进行字符串替换，可以直接调用，但是这里我们使用更加底层的代码实现。  
在字符串尾部填充任意字符，使得字符串的长度等于替换之后的长度。因为一个空格要替换成三个字符（%20），因此当遍历到一个空格时，需要在尾部填充两个任意字符。  
令 P1 指向字符串原来的末尾位置，P2 指向字符串现在的末尾位置。P1 和 P2 从后向前遍历，当 P1 遍历到一个空格时，就需要令 P2 指向的位置依次填充 02%（注意是逆序的），否则就填充上 P1 指向字符的值。  
从后向前遍是为了在改变 P2 所指向的内容时，不会影响到 P1 遍历原来字符串的内容。  
![图解](https://upload-images.jianshu.io/upload_images/8907519-de3fd67b539d9a5a.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
##### 代码
```
 public static String replaceSpace(String sourceString) {
        int index = sourceString.length() - 1;

        StringBuilder stringBuilder = new StringBuilder(sourceString);
        for (char c : sourceString.toCharArray()) {
            if (c == ' ') {
                stringBuilder.append("  ");
            }
        }
        int newIndex = stringBuilder.length() - 1;
        for (int i = index; i > 0; i--) {
            char ch = sourceString.charAt(i);
            if (ch == ' ') {
                stringBuilder.setCharAt(newIndex--, '0');
                stringBuilder.setCharAt(newIndex--, '2');
                stringBuilder.setCharAt(newIndex--, '%');
            } else {
                stringBuilder.setCharAt(newIndex--, ch);
            }
        }
        return stringBuilder.toString();
    }
```
