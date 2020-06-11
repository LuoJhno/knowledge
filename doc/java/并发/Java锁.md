Java锁
====
##### 乐观锁
乐观锁是一种乐观思想，即认为读多写少，遇到并发写的可能性低，每次去拿数据的时候都认为别人不会修改，所以不会上锁，但是在更新的时候会判断一下在此期间别人有没有去更新这个数
据，采取在写时先读出当前版本号，然后加锁操作（比较跟上一次的版本号，如果一样则更新），如果失败则要重复读-比较-写的操作。
java 中的乐观锁基本都是通过 CAS 操作实现的，CAS 是一种更新的原子操作，比较当前值跟传入值是否一样，一样则更新，否则失败。
##### 悲观锁
悲观锁是就是悲观思想，即认为写多，遇到并发写的可能性高，每次去拿数据的时候都认为别人会修改，所以每次在读写数据的时候都会上锁，这样别人想读写这个数据就会 block 直到拿到锁。
java中的悲观锁就是Synchronized,AQS框架下的锁则是先尝试cas乐观锁去获取锁，获取不到，才会转换为悲观锁，如 RetreenLock。
##### 自旋锁
自旋锁原理非常简单，如果持有锁的线程能在很短时间内释放锁资源，那么那些等待竞争锁的线程就不需要做内核态和用户态之间的切换进入阻塞挂起状态，它们只需要等一等（自旋），
等持有锁的线程释放锁后即可立即获取锁，这样就避免用户线程和内核的切换的消耗。
线程自旋是需要消耗 cup 的，说白了就是让 cup 在做无用功，如果一直获取不到锁，那线程也不能一直占用 cup 自旋做无用功，所以需要设定一个自旋等待的最大时间。
如果持有锁的线程执行的时间超过自旋等待的最大时间扔没有释放锁，就会导致其它争用锁的线程在最大等待时间内还是获取不到锁，这时争用线程会停止自旋进入阻塞状态。
> 自旋锁的优缺点
自旋锁尽可能的减少线程的阻塞，这对于锁的竞争不激烈，且占用锁时间非常短的代码块来说性能能大幅度的提升，因为自旋的消耗会小于线程阻塞挂起再唤醒的操作的消耗，这些操作会导致线程发生两次上下文切换！
但是如果锁的竞争激烈，或者持有锁的线程需要长时间占用锁执行同步块，这时候就不适合使用自旋锁了，因为自旋锁在获取锁前一直都是占用 cpu 做无用功，占着 XX 不 XX，同时有大量线程在竞争一个锁，会导致获取锁的时间很长，线程自旋的消耗大于线程阻塞挂起操作的消耗，
其它需要 cup 的线程又不能获取到 cpu，造成 cpu 的浪费。所以这种情况下我们要关闭自旋锁；

> 自旋锁时间阈值（1.6 引入了适应性自旋锁）
自旋锁的目的是为了占着 CPU 的资源不释放，等到获取到锁立即进行处理。但是如何去选择自旋的执行时间呢？如果自旋执行时间太长，会有大量的线程处于自旋状态占用 CPU 资源，进而会影响整体系统的性能。因此自旋的周期选的额外重要！
JVM 对于自旋周期的选择，jdk1.5 这个限度是一定的写死的，在 1.6 引入了适应性自旋锁，适应性自旋锁意味着自旋的时间不在是固定的了，而是由前一次在同一个锁上的自旋时间以及锁的有者的状态来决定，基本认为一个线程上下文切换的时间是最佳的一个时间，同时 JVM 还针对当
前 CPU 的负荷情况做了较多的优化，如果平均负载小于 CPUs 则一直自旋，如果有超过(CPUs/2)个线程正在自旋，则后来线程直接阻塞，如果正在自旋的线程发现 Owner 发生了变化则延迟自旋时间（自旋计数）或进入阻塞，如果 CPU 处于节电模式则停止自旋，自旋时间的最坏情况是 CPU的存储延迟（CPU A 存储了一个数据，到 CPU B 得知这个数据直接的时间差），自旋时会适当放弃线程优先级之间的差异。

> 自旋锁的开启
JDK1.6 中-XX:+UseSpinning 开启；
-XX:PreBlockSpin=10 为自旋次数；
JDK1.7 后，去掉此参数，由 jvm 控制；

##### Synchronized同步锁
synchronized 它可以把任意一个非NULL 的对象当作锁。他属于独占式的悲观锁，同时属于可重
入锁
1. Synchronized 作用范围
* 作用于方法时，锁住的是对象的实例(this)；
* 当作用于静态方法时，锁住的是Class 实例，又因为Class 的相关数据存储在永久带PermGen（jdk1.8 则是metaspace），永久带是全局共享的，因此静态方法锁相当于类的一个全局锁，会锁所有调用该方法的线程；
* synchronized 作用于一个对象实例时，锁住的是所有以该对象为锁的代码块。它有多个队列，当多个线程一起访问某个对象监视器的时候，对象监视器会将这些线程存储在不同的容器中。
2. Synchronized 核心组件
* Wait Set：哪些调用wait 方法被阻塞的线程被放置在这里；
* Contention List：竞争队列，所有请求锁的线程首先被放在这个竞争队列中；
* Entry List：Contention List 中那些有资格成为候选资源的线程被移动到Entry List 中；
* OnDeck：任意时刻，最多只有一个线程正在竞争锁资源，该线程被成为OnDeck；
* Owner：当前已经获取到所资源的线程被称为Owner；
* !Owner：当前释放锁的线程。
3. Synchronized 实现
![Sychronized实现](/doc/java/pic/sychronized实现.jpg)
* JVM 每次从队列的尾部取出一个数据用于锁竞争候选者（OnDeck），但是并发情况下，ContentionList 会被大量的并发线程进行CAS 访问，为了降低对尾部元素的竞争，JVM会将一部分线程移动到EntryList 中作为候选竞争线程。
* Owner 线程会在unlock 时，将ContentionList 中的部分线程迁移到EntryList 中，并指定EntryList 中的某个线程为OnDeck 线程（一般是最先进去的那个线程）。
* Owner 线程并不直接把锁传递给OnDeck 线程，而是把锁竞争的权利交给OnDeck，OnDeck 需要重新竞争锁。这样虽然牺牲了一些公平性，但是能极大的提升系统的吞吐量，在JVM 中，也把这种选择行为称之为“竞争切换”。
* OnDeck 线程获取到锁资源后会变为Owner 线程，而没有得到锁资源的仍然停留在EntryList中。如果Owner 线程被wait 方法阻塞，则转移到WaitSet 队列中，直到某个时刻通过notify或者notifyAll 唤醒，会重新进去EntryList中。
* 处于ContentionList、EntryList、WaitSet 中的线程都处于阻塞状态，该阻塞是由操作系统来完成的（Linux 内核下采用pthread_mutex_lock 内核函数实现的）。
* Synchronized 是非公平锁。 Synchronized 在线程进入ContentionList 时，等待的线程会先尝试自旋获取锁，如果获取不到就进入ContentionList，这明显对于已经进入队列的线程是不公平的，还有一个不公平的事情就是自旋获取锁的线程还可能直接抢占OnDeck 线程的锁资源。
* 每个对象都有个monitor 对象，加锁就是在竞争monitor 对象，代码块加锁是在前后分别加上monitorenter 和monitorexit 指令来实现的，方法加锁是通过一个标记位来判断的
* synchronized 是一个重量级操作，需要调用操作系统相关接口，性能是低效的，有可能给线程加锁消耗的时间比有用操作消耗的时间更多。
* Java1.6，synchronized 进行了很多的优化，有适应自旋、锁消除、锁粗化、轻量级锁及偏向锁等，效率有了本质上的提高。在之后推出的Java1.7 与1.8 中，均对该关键字的实现机理做了优化。引入了偏向锁和轻量级锁。都是在对象头中有标记位，不需要经过操作系统加锁。
* 锁可以从偏向锁升级到轻量级锁，再升级到重量级锁。这种升级过程叫做锁膨胀；
* JDK 1.6 中默认是开启偏向锁和轻量级锁，可以通过-XX:-UseBiasedLocking 来禁用偏向锁。
##### ReentrantLock
ReentantLock 继承接口Lock 并实现了接口中定义的方法，他是一种可重入锁，除了能完成synchronized 所能完成的所有工作外，还提供了诸如可响应中断锁、可轮询锁请求、定时锁等避免多线程死锁的方法。
Lock 接口的主要方法
* void lock(): 执行此方法时, 如果锁处于空闲状态, 当前线程将获取到锁. 相反, 如果锁已经被其他线程持有, 将禁用当前线程, 直到当前线程获取到锁.
* boolean tryLock()：如果锁可用, 则获取锁, 并立即返回true, 否则返回false. 该方法和lock()的区别在于, tryLock()只是"试图"获取锁, 如果锁不可用, 不会导致当前线程被禁用,当前线程仍然继续往下执行代码. 而lock()方法则是一定要获取到锁, 如果锁不可用, 就一
直等待, 在未获得锁之前,当前线程并不继续向下执行.
* void unlock()：执行此方法时, 当前线程将释放持有的锁. 锁只能由持有者释放, 如果线程并不持有锁, 却执行该方法, 可能导致异常的发生.
* Condition newCondition()：条件对象，获取等待通知组件。该组件和当前的锁绑定，当前线程只有获取了锁，才能调用该组件的await()方法，而调用后，当前线程将缩放锁。
* getHoldCount() ：查询当前线程保持此锁的次数，也就是执行此线程执行lock 方法的次数。
* getQueueLength()：返回正等待获取此锁的线程估计数，比如启动10 个线程，1 个线程获得锁，此时返回的是9
* getWaitQueueLength()：（Condition condition）返回等待与此锁相关的给定条件的线程估计数。比如10 个线程，用同一个condition 对象，并且此时这10 个线程都执行了condition 对象的await 方法，那么此时执行此方法返回10
* hasWaiters(Condition condition) ： 查询是否有线程等待与此锁有关的给定条件(condition)，对于指定contidion 对象，有多少线程执行了condition.await 方法
* hasQueuedThread(Thread thread)：查询给定线程是否等待获取此锁
* hasQueuedThreads()：是否有线程等待此锁
* isFair()：该锁是否公平锁
* isHeldByCurrentThread()： 当前线程是否保持锁锁定，线程的执行lock 方法的前后分别是false 和true
* isLock()：此锁是否有任意线程占用
* lockInterruptibly（）：如果当前线程未被中断，获取锁
* tryLock（）：尝试获得锁，仅在调用时锁未被线程占用，获得锁
* tryLock(long timeout TimeUnit unit)：如果锁在给定等待时间内没有被另一个线程保持，则获取该锁。

##### 非公平锁
JVM 按随机、就近原则分配锁的机制则称为不公平锁，ReentrantLock 在构造函数中提供了是否公平锁的初始化方式，默认为非公平锁。非公平锁实际执行的效率要远远超出公平锁，除非程序有特殊需要，否则最常用非公平锁的分配机制。
##### 公平锁
公平锁指的是锁的分配机制是公平的，通常先对锁提出获取请求的线程会先被分配到锁，ReentrantLock 在构造函数中提供了是否公平锁的初始化方式来定义公平锁。
##### ReentrantLock 与 synchronized
1. ReentrantLock 通过方法lock()与unlock()来进行加锁与解锁操作，与synchronized 会被JVM 自动解锁机制不同，ReentrantLock 加锁后需要手动进行解锁。为了避免程序出现异常而无法正常解锁的情况，使用ReentrantLock 必须在finally 控制块中进行解锁操作。
2. ReentrantLock 相比synchronized 的优势是可中断、公平锁、多个锁。这种情况下需要使用ReentrantLock。
##### Lock示例
```java
public class MyService {
    private Lock lock = new ReentrantLock();
    //Lock lock=new ReentrantLock(true);//公平锁
    //Lock lock=new ReentrantLock(false);//非公平锁
    private Condition condition=lock.newCondition();//创建Condition
    public void testMethod() {
        try {
            lock.lock();//lock 加锁
            //1：wait 方法等待：
            //System.out.println("开始wait");
            condition.await();
            //通过创建Condition 对象来使线程wait，必须先执行lock.lock 方法获得锁
            //2：signal 方法唤醒
            condition.signal();//condition 对象的signal 方法可以唤醒wait 线程
            for (int i = 0; i < 5; i++) {
                System.out.println("ThreadName=" + Thread.currentThread().getName()+ (" " + (i + 1)));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally{
            lock.unlock();
        }
    }
}
```
##### Condition 类 Object类的区别
1. Condition 类的awiat 方法和Object 类的wait 方法等效
2. Condition 类的signal 方法和Object 类的notify 方法等效
3. Condition 类的signalAll 方法和Object 类的notifyAll 方法等效
4. ReentrantLock 类可以唤醒指定条件的线程，而object 的唤醒是随机的
##### tryLock和lock和lockInterruptibly的区别
1. tryLock 能获得锁就返回true，不能就立即返回false，tryLock(long timeout,TimeUnitunit)，可以增加时间限制，如果超过该时间段还没获得锁，返回false
2. lock 能获得锁就返回true，不能的话一直等待获得锁
3. lock 和lockInterruptibly，如果两个线程分别执行这两个方法，但此时中断这两个线程，
lock 不会抛出异常，而lockInterruptibly 会抛出异常。
##### Semaphore 信号量
Semaphore 是一种基于计数的信号量。它可以设定一个阈值，基于此，多个线程竞争获取许可信号，做完自己的申请后归还，超过阈值后，线程申请许可信号将会被阻塞。Semaphore 可以用来构建一些对象池，资源池之类的，比如数据库连接池。
1. 实现互斥锁（计数器为1）
我们也可以创建计数为1 的Semaphore，将其作为一种类似互斥锁的机制，这也叫二元信号量，
表示两种互斥状态。
它的用法如下：
```
// 创建一个计数阈值为5 的信号量对象
// 只能5 个线程同时访问
Semaphore semp = new Semaphore(5);
try { // 申请许可
    semp.acquire();
    try {
    // 业务逻辑
    } catch (Exception e) {
    } finally {
        // 释放许可
        semp.release();
    }
} catch (InterruptedException e) {
}
```
2. Semaphore 与 ReentrantLock
Semaphore 基本能完成ReentrantLock 的所有工作，使用方法也与之类似，通过acquire()与release()方法来获得和释放临界资源。经实测，Semaphone.acquire()方法默认为可响应中断锁，
与ReentrantLock.lockInterruptibly()作用效果一致，也就是说在等待临界资源的过程中可以被Thread.interrupt()方法中断。
此外，Semaphore 也实现了可轮询的锁请求与定时锁的功能，除了方法名tryAcquire 与tryLock不同，其使用方法与ReentrantLock 几乎一致。Semaphore 也提供了公平与非公平锁的机制，也可在构造函数中进行设定。
Semaphore 的锁释放操作也由手动进行，因此与ReentrantLock 一样，为避免线程因抛出异常而无法正常释放锁的情况发生，释放锁的操作也必须在finally 代码块中完成。
##### AtomicInteger
首先说明，此处AtomicInteger ， 一个提供原子操作的Integer 的类， 常见的还有AtomicBoolean、AtomicInteger、AtomicLong、AtomicReference 等，他们的实现原理相同，区别在与运算对象类型的不同。令人兴奋地，还可以通过AtomicReference<V>将一个对象的所
有操作转化成原子操作。
我们知道，在多线程程序中，诸如++i 或 i++等运算不具有原子性，是不安全的线程操作之一。
通常我们会使用synchronized 将该操作变成一个原子操作，但JVM 为此类操作特意提供了一些同步类，使得使用更方便，且使程序运行效率变得更高。通过相关资料显示，通常AtomicInteger的性能是ReentantLock 的好几倍。
##### 可重入锁
本文里面讲的是广义上的可重入锁，而不是单指JAVA 下的ReentrantLock。可重入锁，也叫
做递归锁，指的是同一线程 外层函数获得锁之后 ，内层递归函数仍然有获取该锁的代码，但不受影响。在JAVA 环境下 ReentrantLock 和synchronized 都是 可重入锁。

##### 公平锁和非公平锁
* 公平锁（Fair）
加锁前检查是否有排队等待的线程，优先排队等待的线程，先来先得
* 非公平锁（Nonfair）
加锁时不考虑排队等待问题，直接尝试获取锁，获取不到自动到队尾等待
1. 非公平锁性能比公平锁高5~10 倍，因为公平锁需要在多核的情况下维护一个队列
2. Java 中的synchronized 是非公平锁，ReentrantLock 默认的lock()方法采用的是非公平锁。
##### ReadWriteLock
为了提高性能，Java 提供了读写锁，在读的地方使用读锁，在写的地方使用写锁，灵活控制，如果没有写锁的情况下，读是无阻塞的,在一定程度上提高了程序的执行效率。读写锁分为读锁和写锁，多个读锁不互斥，读锁与写锁互斥，这是由jvm 自己控制的，你只要上好相应的锁即可。
* 读锁
如果你的代码只读数据，可以很多人同时读，但不能同时写，那就上读锁
* 写锁
如果你的代码修改数据，只能有一个人在写，且不能同时读取，那就上写锁。总之，读的时候上
读锁，写的时候上写锁！
Java 中读写锁有个接口java.util.concurrent.locks.ReadWriteLock ， 也有具体的实现
ReentrantReadWriteLock。
##### 共享锁和独占锁
java 并发包提供的加锁模式分为独占锁和共享锁。
* 独占锁
独占锁模式下，每次只能有一个线程能持有锁，ReentrantLock 就是以独占方式实现的互斥锁。
独占锁是一种悲观保守的加锁策略，它避免了读/读冲突，如果某个只读线程获取锁，则其他读线
程都只能等待，这种情况下就限制了不必要的并发性，因为读操作并不会影响数据的一致性。
* 共享锁
共享锁则允许多个线程同时获取锁，并发访问 共享资源，如：ReadWriteLock。共享锁则是一种乐观锁，它放宽了加锁策略，允许多个执行读操作的线程同时访问共享资源。
1. AQS 的内部类Node 定义了两个常量SHARED 和EXCLUSIVE，他们分别标识 AQS 队列中等
待线程的锁获取模式。
2. java 的并发包中提供了ReadWriteLock，读-写锁。它允许一个资源可以被多个读操作访问，或者被一个 写操作访问，但两者不能同时进行。

##### 重量级锁Mutex Lock
Synchronized 是通过对象内部的一个叫做监视器锁（monitor）来实现的。但是监视器锁本质又是依赖于底层的操作系统的Mutex Lock 来实现的。而操作系统实现线程之间的切换这就需要从用户态转换到核心态，这个成本非常高，状态之间的转换需要相对比较长的时间，这就是为什么Synchronized 效率低的原因。因此，这种依赖于操作系统Mutex Lock 所实现的锁我们称之为“重量级锁”。JDK 中对Synchronized 做的种种优化，其核心都是为了减少这种重量级锁的使用。
JDK1.6 以后，为了减少获得锁和释放锁所带来的性能消耗，提高性能，引入了“轻量级锁”和
“偏向锁”。
##### 轻量级锁
锁的状态总共有四种：无锁状态、偏向锁、轻量级锁和重量级锁。
锁升级
随着锁的竞争，锁可以从偏向锁升级到轻量级锁，再升级的重量级锁（但是锁的升级是单向的，
也就是说只能从低到高升级，不会出现锁的降级）。
“轻量级”是相对于使用操作系统互斥量来实现的传统锁而言的。但是，首先需要强调一点的是，
轻量级锁并不是用来代替重量级锁的，它的本意是在没有多线程竞争的前提下，减少传统的重量
级锁使用产生的性能消耗。在解释轻量级锁的执行过程之前，先明白一点，轻量级锁所适应的场
景是线程交替执行同步块的情况，如果存在同一时间访问同一锁的情况，就会导致轻量级锁膨胀
为重量级锁。
##### 偏向锁
Hotspot 的作者经过以往的研究发现大多数情况下锁不仅不存在多线程竞争，而且总是由同一线程多次获得。偏向锁的目的是在某个线程获得锁之后，消除这个线程锁重入（CAS）的开销，看起来让这个线程得到了偏护。引入偏向锁是为了在无多线程竞争的情况下尽量减少不必要的轻量级锁执行路径，因为轻量级锁的获取及释放依赖多次CAS 原子指令，而偏向锁只需要在置换
ThreadID 的时候依赖一次CAS 原子指令（由于一旦出现多线程竞争的情况就必须撤销偏向锁，所以偏向锁的撤销操作的性能损耗必须小于节省下来的CAS 原子指令的性能消耗）。上面说过，轻量级锁是为了在线程交替执行同步块时提高性能，而偏向锁则是在只有一个线程执行同步块时进一步提高性能。
##### 分段锁
分段锁也并非一种实际的锁，而是一种思想ConcurrentHashMap 是学习分段锁的最好实践
##### 锁优化
* 减少持锁时间
只用在有线程安全要求的程序上加锁
* 减小锁力度
将大对象（这个对象可能会被很多线程访问），拆成小对象，大大增加并行度，降低锁竞争。
降低了锁的竞争，偏向锁，轻量级锁成功率才会提高。最最典型的减小锁粒度的案例就是
ConcurrentHashMap。
* 锁分离
最常见的锁分离就是读写锁ReadWriteLock，根据功能进行分离成读锁和写锁，这样读读不互
斥，读写互斥，写写互斥，即保证了线程安全，又提高了性能，具体也请查看[高并发Java 五]
JDK 并发包1。读写分离思想可以延伸，只要操作互不影响，锁就可以分离。比如
LinkedBlockingQueue 从头部取出，从尾部放数据
* 锁粗化
通常情况下，为了保证多线程间的有效并发，会要求每个线程持有锁的时间尽量短，即在使用完
公共资源后，应该立即释放锁。但是，凡事都有一个度，如果对同一个锁不停的进行请求、同步
和释放，其本身也会消耗系统宝贵的资源，反而不利于性能的优化 。
* 锁消除
锁消除是在编译器级别的事情。在即时编译器时，如果发现不可能被共享的对象，则可以消除这
些对象的锁操作，多数是因为程序员编码不规范引起。