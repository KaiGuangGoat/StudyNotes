
并发是提高提高运行在单处理机上的程序的性能。

    线程间的切换是有性能开销的，增加了上下文的切换的代价（从一个线程切换到另外一个），而单任务没有此开销。由于程序运行的时候会有一些程序

    控制之外的某些条件导致阻塞（如IO操作），这个时候就需要用到并发。如果程序没有阻塞，在单处理机上使用并发完全没有意义。
    


线程池

	 CachedThreadPool

	 FixedThreadPool

	 SignalThreadPool



从任务中产生返回值

	java.concurrent.Callable

	Callable c = new Callable<String>{
		public String call(){
			return "test";
		}
	}

	ExecutorService exec = ExecutorService.newCachedThreadPool();
	Future f = exec.submit(c);
	f.get();//阻塞等待，直到返回



休眠
	Thread.sleep();
	TimeUtil



后台线程

	设置为后台线程,后台线程在全部非后台线程结束的时候会自动终止
	thread.setDeamon(true);
	thread.start();



volatile synchronized Lock

	Lock lock = new ReentrantLock();
	lock.lock();
	.
	.
	.
	lock.unlock();

	lock.trylock();




原子性和易变性
	原子操作可由线程机制保证其不可中断，专家级别的程序员可以利用这一点写出完全无锁的代码，这些代码不需要同步。

	第一选择应该是使用 synchronized 关键字，而其他的方式是有风险的。

原子类
	AtomicInteger AtomicLong AtomicReference

	--> boolean compareAndSet(expectedValue,updateValue)

	常规编程很少派上用场，性能调优大有用武之地;

对象锁
	被锁的对象其他加了 synchronized 关键字的其他方法或代码块都会被锁住

线程本地存储
	ThreadLocal







 