CyclicBarrier、CountDownLatch、Semaphore
===
#### CountDownLatch（线程计数器 ）
CountDownLatch 类位于 java.util.concurrent 包下，利用它可以实现类似计数器的功能。比如有一个任务 A，它要等待其他 4 个任务执行完毕之后才能执行，此时就可以利用 CountDownLatch来实现这种功能了。
```java
final CountDownLatch latch = new CountDownLatch(2);
new Thread(){
    public void run() {
        System.out.println("子线程"+Thread.currentThread().getName()+"正在执行");
        Thread.sleep(3000);
        System.out.println("子线程"+Thread.currentThread().getName()+"执行完毕");
        latch.countDown();
    }
}
.start();
new Thread(){ 
    public void run() {
        System.out.println("子线程"+Thread.currentThread().getName()+"正在执行");
        Thread.sleep(3000);
        System.out.println("子线程"+Thread.currentThread().getName()+"执行完毕");
        latch.countDown();
    };
}
.start();
    System.out.println("等待 2 个子线程执行完毕...");
    latch.await();
    System.out.println("2 个子线程已经执行完毕");
    System.out.println("继续执行主线程");
}
```
#### CyclicBarrier（回环栅栏-等待至 barrier 状态再全部同时执行）
字面意思回环栅栏，通过它可以实现让一组线程等待至某个状态之后再全部同时执行。叫做回环是因为当所有等待线程都被释放以后，CyclicBarrier 可以被重用。我们暂且把这个状态就叫做barrier，当调用 await()方法之后，线程就处于 barrier 了。
CyclicBarrier 中最重要的方法就是 await 方法，它有 2 个重载版本：
* public int await()：用来挂起当前线程，直至所有线程都到达 barrier 状态再同时执行后续任务；
* public int await(long timeout, TimeUnit unit)：让这些线程等待至一定的时间，如果还有线程没有到达 barrier 状态就直接让到达 barrier 的线程执行后续任务。

具体使用如下，另外 CyclicBarrier 是可以重用的。
```java
public static void main(String[] args) {
    int N = 4;
    CyclicBarrier barrier = new CyclicBarrier(N);
    for(int i=0;i<N;i++)
        new Writer(barrier).start();
}
static class Writer extends Thread{
    private CyclicBarrier cyclicBarrier;
    public Writer(CyclicBarrier cyclicBarrier) {
        this.cyclicBarrier = cyclicBarrier;
    }
    @Override
    public void run() {
        try {
            Thread.sleep(5000); //以睡眠来模拟线程需要预定写入数据操作
            System.out.println("线程"+Thread.currentThread().getName()+"写入数据完毕，等待其他线程写入完毕");
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }catch(BrokenBarrierException e){
            e.printStackTrace();
        }
        System.out.println("所有线程写入完毕，继续处理其他任务，比如数据操作");
    }
}
 ```
#### Semaphore（信号量-控制同时访问的线程个数）
Semaphore 翻译成字面意思为 信号量，Semaphore 可以控制同时访问的线程个数，通过acquire() 获取一个许可，如果没有就等待，而 release() 释放一个许可。
Semaphore 类中比较重要的几个方法：
* public void acquire(): 用来获取一个许可，若无许可能够获得，则会一直等待，直到获得许
可。
* public void acquire(int permits):获取 permits 个许可
* public void release() { } :释放许可。注意，在释放许可之前，必须先获获得许可。
* public void release(int permits) { }:释放 permits 个许可

上面 4 个方法都会被阻塞，如果想立即得到执行结果，可以使用下面几个方法
* public boolean tryAcquire():尝试获取一个许可，若获取成功，则立即返回 true，若获取失
败，则立即返回 false
* public boolean tryAcquire(long timeout, TimeUnit unit):尝试获取一个许可，若在指定的
时间内获取成功，则立即返回 true，否则则立即返回 false
* public boolean tryAcquire(int permits):尝试获取 permits 个许可，若获取成功，则立即返
回 true，若获取失败，则立即返回 false
* public boolean tryAcquire(int permits, long timeout, TimeUnit unit): 尝试获取 permits个许可，若在指定的时间内获取成功，则立即返回 true，否则则立即返回 false
* 还可以通过 availablePermits()方法得到可用的许可数目。
例子：若一个工厂有 5 台机器，但是有 8 个工人，一台机器同时只能被一个工人使用，只有使用完了，其他工人才能继续使用。那么我们就可以通过 Semaphore 来实现：
```java
int N = 8; //工人数
Semaphore semaphore = new Semaphore(5); //机器数目
    for(int i=0;i<N;i++)
    new Worker(i,semaphore).start();
}
static class Worker extends Thread{
    private int num;
    private Semaphore semaphore;
    public Worker(int num,Semaphore semaphore){
        this.num = num;
        this.semaphore = semaphore;
    }
    @Override
    public void run() {
        try {
            semaphore.acquire();
            System.out.println("工人"+this.num+"占用一个机器在生产...");
            Thread.sleep(2000);
            System.out.println("工人"+this.num+"释放出机器");
            semaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } 
    } 
```  
#### 区别  
* CountDownLatch 和 CyclicBarrier 都能够实现线程之间的等待，只不过它们侧重点不同；CountDownLatch 一般用于某个线程 A 等待若干个其他线程执行完任务之后，它才执行；而 CyclicBarrier 一般用于一组线程互相等待至某个状态，然后这一组线程再同时执行；另外，CountDownLatch 是不能够重用的，而 CyclicBarrier 是可以重用的。
* Semaphore 其实和锁有点类似，它一般用于控制对某组资源的访问权限。