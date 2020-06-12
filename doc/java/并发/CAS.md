CAS
===
#### 概念及特性
CAS（Compare And Swap/Set）比较并交换，CAS 算法的过程是这样：它包含3 个参数CAS(V,E,N)。V 表示要更新的变量(内存值)，E 表示预期值(旧的)，N 表示新值。当且仅当V 值等于E 值时，才会将V 的值设为N，如果V 值和E 值不同，则说明已经有其他线程做了更新，则当前线程什么都不做。最后，CAS 返回当前V 的真实值。
CAS 操作是抱着乐观的态度进行的(乐观锁)，它总是认为自己可以成功完成操作。当多个线程同时使用CAS 操作一个变量时，只有一个会胜出，并成功更新，其余均会失败。失败的线程不会被挂起，仅是被告知失败，并且允许再次尝试，当然也允许失败的线程放弃操作。基于这样的原理，CAS 操作即使没有锁，也可以发现其他线程对当前线程的干扰，并进行恰当的处理。
#### 原子包 java.util.concurrent.atomic（锁自旋）
JDK1.5 的原子包：java.util.concurrent.atomic 这个包里面提供了一组原子类。其基本的特性就是在多线程环境下，当有多个线程同时执行这些类的实例包含的方法时，具有排他性，即当某个线程进入方法，执行其中的指令时，不会被其他线程打断，而别的线程就像自旋锁一样，一直等到该方法执行完成，才由JVM 从等待队列中选择一个另一个线程进入，这只是一种逻辑上的理解。
相对于对于synchronized 这种阻塞算法，CAS 是非阻塞算法的一种常见实现。由于一般CPU 切换时间比CPU 指令集操作更加长， 所以J.U.C 在性能上有了很大的提升。如下代码：
```JAVA
public class AtomicInteger extends Number implements java.io.Serializable {
    private volatile int value;
    public final int get() {
        return value;
    }
    public final int getAndIncrement() {
        for (;;) { //CAS 自旋，一直尝试，直达成功
            int current = get();
            int next = current + 1;
            if (compareAndSet(current, next))
            return current;
        }
    }
    public final boolean compareAndSet(int expect, int update) {
        return unsafe.compareAndSwapInt(this, valueOffset, expect, update);
    }
}
```
getAndIncrement 采用了CAS 操作，每次从内存中读取数据然后将此数据和+1 后的结果进行CAS 操作，如果成功就返回结果，否则重试直到成功为止。而compareAndSet 利用JNI 来完成CPU 指令的操作。

#### ABA 问题
CAS 会导致“ABA 问题”。CAS 算法实现一个重要前提需要取出内存中某时刻的数据，而在下时刻比较并替换，那么在这个时间差类会导致数据的变化。比如说一个线程one 从内存位置V 中取出A，这时候另一个线程two 也从内存中取出A，并且two 进行了一些操作变成了B，然后two 又将V 位置的数据变成A，这时候线程one 进行CAS 操作发现内存中仍然是A，然后one 操作成功。尽管线程one 的CAS 操作成功，但是不代表这个过程就是没有问题的。
部分乐观锁的实现是通过版本号（version）的方式来解决ABA 问题，乐观锁每次在执行数据的修改操作时，都会带上一个版本号，一旦版本号和数据的版本号一致就可以执行修改操作并对版本号执行+1 操作，否则就执行失败。因为每次操作的版本号都会随之增加，所以不会出现ABA 问题，因为版本号只会增加不会减少。