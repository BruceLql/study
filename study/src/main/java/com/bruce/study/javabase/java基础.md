# java 基础总结  （面试必问）
## HashMap
众所周知 HashMap 底层是基于 数组 + 链表 组成的，不过在 jdk1.7 和 1.8 中具体实现稍有不同。

 **Base 1.7**

- 1.7 中的数据结构图：
 ![avator](../../../../../resources/static/hashmap_1.7.jpg)

先来看看 1.7 中的实现:

1. 初始化桶大小16，因为底层是数组，所以这是数组默认的大小。
2. 桶最大值 2的30次幂 。
3. 默认的负载因子（0.75）
4. table 真正存放数据的数组。
5. Map 存放数量的大小。
6. 桶大小，可在初始化时显式指定。
7. 负载因子，可在初始化时显式指定。

```java
        public HashMap() {
            this(DEFAULT_INITIAL_CAPACITY, DEFAULT_LOAD_FACTOR);
        }
    
        public HashMap(int initialCapacity, float loadFactor) {
            if (initialCapacity < 0)
                throw new IllegalArgumentException("Illegal initial capacity: " +
                                                   initialCapacity);
            if (initialCapacity > MAXIMUM_CAPACITY)
                initialCapacity = MAXIMUM_CAPACITY;
            if (loadFactor <= 0 || Float.isNaN(loadFactor))
                throw new IllegalArgumentException("Illegal load factor: " +
                                                   loadFactor);
    
            this.loadFactor = loadFactor;
            threshold = initialCapacity;
            init();
        }
```



# 多线程

- 设定多少个线程数合适?
    
    
   **CPU 密集型程序:**
        
    线程数量 = CPU 核数（逻辑）+1
    
   **I/O 密集型程序:**
   
    最佳线程数 = CPU核心数 * (1/CPU利用率) 
   
            = CPU核心数 * (1 + (I/O耗时/CPU耗时))

- 我们已经知道创建多少个线程合适了，为什么还要搞一个线程池出来？
       
       因为线程池可以：降低创建线程的开销、提高线程被引用时的引用速度、线程可以具备可管理性
        

- 创建一个线程都要做哪些事情？为什么说频繁的创建线程开销很大？
        
        创建线程除了会向JVM申请程序计数器、虚拟机栈空间、本地虚拟机栈空间、在堆中存放线程对象信息等
        
- 多线程通常要注意共享变量问题，为什么局部变量就没有线程安全问题呢？
        
        线程局部变量都存放在虚拟机栈中，虚拟机栈是线程独有的
        
 
# java 线程池详解 
https://www.jianshu.com/p/7726c70cdc40

### 1、线程池的优势
（1）、降低系统资源消耗，通过重用已存在的线程，降低线程创建和销毁造成的消耗；
（2）、提高系统响应速度，当有任务到达时，通过复用已存在的线程，无需等待新线程的创建便能立即执行；
（3）方便线程并发数的管控。因为线程若是无限制的创建，可能会导致内存占用过多而产生OOM，并且会造成cpu过度切换（cpu切换线程是有时间成本的（需要保持当前执行线程的现场，并恢复要执行线程的现场））。
（4）提供更强大的功能，延时定时线程池。

### 2、线程池的主要参数
```java
public ThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
    this(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue,
         Executors.defaultThreadFactory(), defaultHandler);
}
```
1、corePoolSize（线程池基本大小）：当向线程池提交一个任务时，若线程池已创建的线程数小于corePoolSize，即便此时存在空闲线程，也会通过创建一个新线程来执行该任务，直到已创建的线程数大于或等于corePoolSize时，（除了利用提交新任务来创建和启动线程（按需构造），也可以通过 prestartCoreThread() 或 prestartAllCoreThreads() 方法来提前启动线程池中的基本线程。）

2、maximumPoolSize（线程池最大大小）：线程池所允许的最大线程个数。当队列满了，且已创建的线程数小于maximumPoolSize，则线程池会创建新的线程来执行任务。另外，对于无界队列，可忽略该参数。

3、keepAliveTime（线程存活保持时间）当线程池中线程数大于核心线程数时，线程的空闲时间如果超过线程存活时间，那么这个线程就会被销毁，直到线程池中的线程数小于等于核心线程数。

4、workQueue（任务队列）：用于传输和保存等待执行任务的阻塞队列。

5、threadFactory（线程工厂）：用于创建新线程。threadFactory创建的线程也是采用new Thread()方式，threadFactory创建的线程名都具有统一的风格：pool-m-thread-n（m为线程池的编号，n为线程池内的线程编号）。

6、handler（线程饱和策略）：当线程池和队列都满了，再加入线程会执行此策略。

### 3、线程池流程
![avator](../../../../../resources/static/xcclc.png)


    1、判断线程池中当前线程数是否大于核心线程数，如果小于，在创建一个新的线程来执行任务，如果大于则
    2、判断任务队列是否已满，没满则将新提交的任务添加在工作队列，已满则。
    3、判断线程池中当前线程数是否大于最大线程数，如果小于，则创建一个新的线程来执行任务，如果大于，则执行饱和策略。）

### 4、线程池为什么需要使用（阻塞）队列？
回到了非线程池缺点中的第3点：

1、因为线程若是无限制的创建，可能会导致内存占用过多而产生OOM，并且会造成cpu过度切换。

另外回到了非线程池缺点中的第1点：

2、创建线程池的消耗较高。
或者下面这个网上并不高明的回答：
 线程池创建线程需要获取mainlock这个全局锁，影响并发效率，阻塞队列可以很好的缓冲。

### 5、线程池为什么要使用阻塞队列而不使用非阻塞队列？
阻塞队列可以保证任务队列中没有任务时阻塞获取任务的线程，使得线程进入wait状态，释放cpu资源。
当队列中有任务时才唤醒对应线程从队列中取出消息进行执行。
使得在线程不至于一直占用cpu资源。

（线程执行完任务后通过循环再次从任务队列中取出任务进行执行，代码片段如下
while (task != null || (task = getTask()) != null) {}）。

不用阻塞队列也是可以的，不过实现起来比较麻烦而已，有好用的为啥不用呢？

### 6、如何配置线程池
CPU密集型任务
尽量使用较小的线程池，一般为CPU核心数+1。 因为CPU密集型任务使得CPU使用率很高，若开过多的线程数，会造成CPU过度切换。

IO密集型任务
可以使用稍大的线程池，一般为2*CPU核心数。 IO密集型任务CPU使用率并不高，因此可以让CPU在等待IO的时候有其他线程去处理别的任务，充分利用CPU时间。

混合型任务
可以将任务分成IO密集型和CPU密集型任务，然后分别用不同的线程池去处理。 只要分完之后两个任务的执行时间相差不大，那么就会比串行执行来的高效。
因为如果划分之后两个任务执行时间有数据级的差距，那么拆分没有意义。
因为先执行完的任务就要等后执行完的任务，最终的时间仍然取决于后执行完的任务，而且还要加上任务拆分与合并的开销，得不偿失。

### 7、java中提供的线程池
#### Executors类提供了4种不同的线程池：
        
    newCachedThreadPool, 
    newFixedThreadPool,
    newScheduledThreadPool,
    newSingleThreadExecutor

#### java线程池对比:
1、newCachedThreadPool：用来创建一个可以无限扩大的线程池，适用于负载较轻的场景，执行短期异步任务。（可以使得任务快速得到执行，因为任务时间执行短，可以很快结束，也不会造成cpu过度切换）

2、newFixedThreadPool：创建一个固定大小的线程池，因为采用无界的阻塞队列，所以实际线程数量永远不会变化，适用于负载较重的场景，对当前线程数量进行限制。（保证线程数可控，不会造成线程过多，导致系统负载更为严重）

3、newSingleThreadExecutor：创建一个单线程的线程池，适用于需要保证顺序执行各个任务。

4、newScheduledThreadPool：适用于执行延时或者周期性任务。

### 8、execute()和submit()方法
1、execute()，执行一个任务，没有返回值。

2、submit()，提交一个线程任务，有返回值。

submit(Callable<T> task)能获取到它的返回值，通过future.get()获取（阻塞直到任务执行完）。
一般使用FutureTask+Callable配合使用（IntentService中有体现）。

submit(Runnable task, T result)能通过传入的载体result间接获得线程的返回值。
submit(Runnable task)则是没有返回值的，就算获取它的返回值也是null。

Future.get方法会使取结果的线程进入阻塞状态，知道线程执行完成之后，唤醒取结果的线程，然后返回结果。



