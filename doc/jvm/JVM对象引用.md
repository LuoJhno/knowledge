对象引用
========
##### 概述
Java语言中，最简单的访问，也会涉及Java栈，Java堆，方法区这三个最重要内存区域之间的关联关系。

    Object obj = new Object();

Object obj这部分反应到Java栈的本地变量表中，作为一个reference类型出现。而new Object()这部分的会反映到java堆中，形成一块存储了Object类型所有实例数据值(Instance Data,对象中各个实例字段的数据)的结构化数据，这块内存的长度不固定。另外Java堆中还必须包还能查找到到此对象类型数据（如对象类型，父类，实现的接口，方法等）的地址信息，这些类型数据存储在方法区中。

由于reference类型在Java虚拟机规范里面只规定了一个指向对象的引用，没有定义用什么方式去定位。现在主流的方式有两种：使用句柄和直接指针。
##### 句柄方式引用
如果使用句柄访问方式，Java堆中会划分出一块内存来作为句柄池，reference中存储的就是对象的句柄地址，而句柄中包含了对象实例数据和类型数据各自的具体地址信息。
![图解](https://upload-images.jianshu.io/upload_images/8907519-661f04f3e414907f.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
##### 直接引用
如果直接指针访问，Java堆对象的布局就必须考虑如何放置访问类型数据的先关信息，reference中直接存储的就是对象地址。
![图解](https://upload-images.jianshu.io/upload_images/8907519-43705024a4b70408.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
##### 总结
**句柄方式**reference中存储的是稳定的句柄方式，在对象被移动（垃圾收集时移动对象很常见）时只会改变句柄中的实例数据指针，而reference本身不需要被修改。
**直接指针**访问方式最大的好处就是速度快，节省了一次指针定位的时间开销。HotSpot中使用的就是直接指针访问方式。