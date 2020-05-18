垃圾收集器
====
##### 背景
这里讨论的是JDK1.7 Update 14之后的HotSpot虚拟机，包含的垃圾收集器如图所示：
![图片](https://upload-images.jianshu.io/upload_images/8907519-af8964e24394efe8.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

##### 虚拟机介绍
1. Serial收集器
![图片](https://upload-images.jianshu.io/upload_images/8907519-e54d818c7a2cf9dc.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
Serial收集器是最基本，发展历史最久的收集器。Serial是一个单线程的收集器，只会使用一个线程，且必须暂停所有工作线程（Stop the word）的收集器，采用的复制算法。
相比于其他单线程收集器，Serial简单而高效。
2. ParNew收集器
![图片](https://upload-images.jianshu.io/upload_images/8907519-1787f2d25edf7d7c.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
其实就是Serial收集器的多线程版本，采用的也是复制算法；ParNew在单CPU环境中的效果没有Serial收集器的效果好。
ParNew收集器是许多运行在Server模式下虚拟机首选的新生代收集器，重要原因是除了Serail收集器外，只有ParNew能配合CMS收集器工作。
并行（Parallel）：指多条垃圾收集线程并行工作，但此时用户线程仍然处于等待状态。
并发（Concurrent）：指用户线程和垃圾收集线程同时执行，用户程序继续运行，而垃圾手机程勋运行于另一个CPU上。
3. CMS收集器
![图片](https://upload-images.jianshu.io/upload_images/8907519-b38cd6adb2f1886b.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
CMS收集器是一种以获取最短回收停顿时间为目标的收集器。CMS收集器采用的标记-清楚算法实现的。
主要分为四个步骤：初始标记，并发标记，重新标记，并发清除。
其中初始标记和重新标记任然需要Stop the word。初始标记仅仅只是标记一下GC Roots能直接关联的对象，速度很快，并发标记阶段就是GC Roots Tracing的过程，而重新标记阶段则是为了修正并发标记期间，因用户程序继续运作而导致标记产生变动的那一部分对象的标记记录，这个阶段比初始标记时间长，但是比并发标记时间短。
由于整个过程耗时最长的并发标记和并发清除过程中，收集器都是和用户线程一起工作，所以总体上说，CMS收集器的内存回收过程是与用户线程一起并发执行的。
CMS收集器是一款并发收集，低停顿的。但是仍然有三个显著的缺点:
    > CMS收集器对CPU资源非常敏感，在并发阶段，它虽然不会法制用户线程停顿，但是会因为占用一部分线程而导致应用程序变慢，总吞吐量会降低。CMS默认启动的回收线程数是(CPU数量+3)/4。
    > CMS收集器无法处理浮动垃圾，可能出现Concurrent Mode Failure 失败而导致另一次Full GC产生。由于CMS并发清理阶段用户线程还在运行着，伴随程序的运行自然还会有新的垃圾产生，这一部分垃圾出现在标记过程之后，CMS无法在本次收集中处理掉他们，只好留给下一次GC时再清理掉。这一部分垃圾就是浮动垃圾。因此CMS收集器不能像其他收集器那样等到老年代几乎完全被填满了再进行收集，需要预留一部分空间提供并发收集时的程序运作使用。在默认情况下，CMS在老年代被使用了68%的空间后就会被激活，可以适当调高参数 -XX:CMSInitiatingOccupancyFraction的值来提高触发百分比。如果CMS运行期间预留的内存无法满足程序需要，就会出现一次 Concurrent Mode Failure失败，这时候虚拟机将启动后被预案，临时启用Serial Old收集器来重新进行老年代的垃圾收集，这样停顿的时间就很长了。
    > CMS是标记-清除算法，在收集结束时会产生大量的空间碎片。所以CMS收集器提供了 -XX:+UseCMSCompactAtFullCollection开关参数，在Full GC后，再进行一次碎片整理过程，内存整理的过程是无法并发的，所以停顿时间变长了。 CMS还提供了 -XX:CMSFullGCsBeforCompaction，这个参数用来设置在执行多少次不压缩的Full GC后，进行一次压缩的Full GC。
4. G1收集器
G1（Garbage-First），它是一款面向服务端应用的垃圾收集器，在多 CPU 和大内存的场景下有很好的性能。HotSpot 开发团队赋予它的使命是未来可以替换掉 CMS 收集器。

堆被分为新生代和老年代，其它收集器进行收集的范围都是整个新生代或者老年代，而 G1 可以直接对新生代和老年代一起回收。
![hotspot堆设计](/doc/jvm/pic/hotspot堆结构.png)
G1 把堆划分成多个大小相等的独立区域（Region），新生代和老年代不再物理隔离。
![G1设计](/doc/jvm/pic/G1设计.png)
通过引入 Region 的概念，从而将原来的一整块内存空间划分成多个的小空间，使得每个小空间可以单独进行垃圾回收。这种划分方法带来了很大的灵活性，使得可预测的停顿时间模型成为可能。通过记录每个 Region 垃圾回收时间以及回收所获得的空间（这两个值是通过过去回收的经验获得），并维护一个优先列表，每次根据允许的收集时间，优先回收价值最大的 Region。
每个 Region 都有一个 Remembered Set，用来记录该 Region 对象的引用对象所在的 Region。通过使用 Remembered Set，在做可达性分析的时候就可以避免全堆扫描。
![图片](https://upload-images.jianshu.io/upload_images/8907519-9c74bb6d95247fcf.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
G1收集器是基于标记-整理算法实现的收集器，也就是说它不会产生空间碎片；G1可以非常精确地控制停顿，既能让使用者明确指定一个长度为M毫秒的时间片段内，消耗在垃圾手机上额时间不得超过N毫秒。
G1收集器可以实现在基本不牺牲吞吐量的前提下完成低停顿的内存回收，G1将整个JAVA堆（包括新生代和老年代）划分为多个大小固定的独立区域Region，并且跟踪这些区域Region里面的垃圾堆积程度，在后台维护一个优先队列，每次根据允许的收集时间，优先回收垃圾最多的区域Region。Region之间的对象引用以及其他收集器中的新生代和老年代之间的对象引用，采用Remembered Set来避免全堆扫描。
    G1的特点： 
    > 并行和并发，充分利用多CPU多段Stop the word的时间；
    > 分代收集，独立管理java堆，但是针对不同年龄的对象采用不同的策略；
    > 空间整合，局部看是复制算法，整理看是基于标记-清理算法，都不会产生空间碎片；
    > 可预测的停顿，可以明确指定一个长度为M毫秒的时间片段内，消耗在垃圾手机上额时间不得超过N毫秒。

    G1收集器的步骤：
    > 初始标记，标记一下GC Roots能直接关联的对象并修改TAMS值，需要Stop the word但耗时短；
    并发标记，根据根搜索算法，找到存活的对象，耗时较长但可以与用户线程并发执行；
    > 最终标记：为了修正并发标记期间产生变动的那一部分标记记录，这一期间的变化记录在Remembered Set Log里，然后合并到Remembered Set里，该阶段需要Stop the word，但是可以并行执行；
    > 筛选回收，对各个区域回收价值排序，根据用户期望的GC停顿时间制定回收计划来回收。

1. Parallel Scavenge收集器
Parallel Scavenge收集器是一个新生代收集器，采用复制算法，是并行的多线程收集器。
Parralle Scavenge收集器的目的是达到一个可控制的吞吐量。
6. Serial Old收集器
![图片](https://upload-images.jianshu.io/upload_images/8907519-2474fee040c68a5f.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
Serial Old是Serial收集器的老年代版本，是一个单线程收集器，采用标记-整理算法。会有 Stop the word。
它是CMS收集器的后备预案。
7. Parallel Old收集器
![图片](https://upload-images.jianshu.io/upload_images/8907519-7bdeee8ad992b12d.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
Parrallel Old是Parallel Scavenge收集器的老年代版本，使用多线程和标记-整理算法。