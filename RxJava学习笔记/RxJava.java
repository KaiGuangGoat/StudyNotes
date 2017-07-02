//栗子：  https://github.com/kaushikgopal/RxJava-Android-Samples 

Observable 	:被观察者
Observer 	:观察者

Publisher 	:发布者
Subscriber 	:订阅者

Consumer 	:消费者
package io.reactivex
	基类：Flowable.....>Subscriber
		  Observable...>Observer
		  Single.......>SingleObserver
		  Completable..>CompletableObserver


Publisher<T>:interface
	+subscribe(Subscriber<? super T> s)

Flowable.java:--->implements Publisher<T>
	+subscribe(FlowableSubscriber<? super T> s)->ObjectHelper.requireNonNull(s, "s is null")判断是否为 null,是抛出异常
											   ->Subscriber<? super T> z = RxJavaPlugins.onSubscribe(this, s):钩子
											   ->subscribeActual(z):实际调用绑定，交给子类实现
	+Flowable<T> just(T item)
		->return RxJavaPlugins.onAssembly(new FlowableJust<T>(item)):实际返回 FlowableJust 实例
	+Disposable subscribe(Consumer<? super T> onNext):
		->return  subscribe(onNext, Functions.ON_ERROR_MISSING,Functions.EMPTY_ACTION, FlowableInternalHelper.RequestMax.INSTANCE)
	+Disposable subscribe(Consumer<? super T> onNext, Consumer<? super Throwable> onError,
            					Action onComplete, Consumer<? super Subscription> onSubscribe)
		->对四个参数判空
		->LambdaSubscriber<T> ls = new LambdaSubscriber<T>(onNext, onError, onComplete, onSubscribe)
		->subscribe(ls)
		->return ls
	+fromCallable(Callable<? extends T> supplier)
		->return RxJavaPlugins.onAssembly(new FlowableFromCallable<T>(supplier))->实际返回 FlowableFromCallable 实例
	+subscribeOn(Scheduler scheduler)

-------------Subscriber----------------------
Subscriber<T>.java:interface
	+onSubscribe(Subscription s)
	+onNext(T t)
	+onError(Throwable t)
	+onComplete()
FlowableSubscriber<T>.java--->extends Subscriber:interface
	+onSubscribe(Subscription s)

Subscription.java:interface,处理订阅
	+request(long n)
	+cancel()

QueueSubscription<T>.java--->extends QueueFuseable<T>, Subscription:interface
	
ScalarSubscription<T>.java--->extends AtomicInteger implements QueueSubscription<T>
	- T value
	- Subscriber<? super T> subscriber

	+request(long n)
		->subscriber.onNext(value)
		->get() != CANCELLED->subscriber.onComplete()

Consumer.java:interface
	+accept(@NonNull T t) throws Exception

LambdaSubscriber<T>.java:extends AtomicReference<Subscription> implements FlowableSubscriber<T>, Subscription, Disposable
	- Consumer<? super T> onNext;
    - Consumer<? super Throwable> onError;
    - Action onComplete;
    - Consumer<? super Subscription> onSubscribe;

	+onSubscribe(Subscription s)
		->onSubscribe.accept(this)
	+onNext(T t)
		->if !dispose -> onNext.accept(t)
	+onError(Throwable t)
	+onComplete()
		-> onComplete.run()
	+dispose()
	+isDisposed()
	+request(long n)
	+cancel()
		->SubscriptionHelper.cancel(this)




-------------------------------------------------------------
Scheduler.java:抽象类，线程调度
	+abstract Worker createWorker()
	+start()
	+shutdown()
	+scheduleDirect(@NonNull Runnable run)->return scheduleDirect(run, 0L, TimeUnit.NANOSECONDS)
	+scheduleDirect(@NonNull Runnable run, long delay, @NonNull TimeUnit unit)
		->w.schedule(task, delay, unit) -> return task:实例为 DisposeTask
	+schedulePeriodicallyDirect(@NonNull Runnable run, long initialDelay, long period, @NonNull TimeUnit unit)
		->w.schedulePeriodically(periodicTask, initialDelay, period, unit)
	+<S extends Scheduler & Disposable> S when(@NonNull Function<Flowable<Flowable<Completable>>, Completable> combine)
		return (S) new SchedulerWhen(combine, this)

	-->Worker--->implements Disposable,abstract
			+schedule(@NonNull Runnable run) -> return schedule(run, 0L, TimeUnit.NANOSECONDS):无延迟调用线程
			+abstract Disposable schedule(@NonNull Runnable run, long delay, @NonNull TimeUnit unit): delay<=0 ==>delay = 0
			+schedulePeriodically(@NonNull Runnable run, final long initialDelay, final long period, @NonNull final TimeUnit unit):周期性执行？
			+now():return currentTime;

			-->PeriodicTask implements Runnable
					+run()
	-->DisposeTask implements Runnable,Disposable
			- final Runnable decoratedRun
       		- final Worker w
       		- Thread runner:保证是当前线程
			
			+
	-->				

SchedulerWhen.java--->extends Scheduler implements Disposable
	- Scheduler actualScheduler
	- FlowableProcessor<Flowable<Completable>> workerProcessor
	- Disposable disposable

	+createWorker():return Worker
		->actualScheduler.createWorker()

		->return  new QueueWorker(actionProcessor, actualWorker)

	-->ScheduledAction extends AtomicReference<Disposable> implements Disposable
			+ call(Worker actualWorker, CompletableObserver actionCompletable)
			+ callActual(Worker actualWorker, CompletableObserver actionCompletable):abstract ,return Disposable
			+ dispose()
	-->ImmediateAction extends ScheduledAction
			- Runnable action
			+ callActual(Worker actualWorker, CompletableObserver actionCompletable):return Disposable
				-> return actualWorker.schedule(new OnCompletedAction(action, actionCompletable))
	-->DelayedAction extends ScheduledAction
			- Runnable action
			- long delayTime
			- TimeUnit unit

			+ callActual(Worker actualWorker, CompletableObserver actionCompletable):return Disposable
				-> return actualWorker.schedule(new OnCompletedAction(action, actionCompletable), delayTime, unit)
    -->OnCompletedAction implements Runnable
    		- CompletableObserver actionCompletable;
        	- Runnable action;

        	+ run()
        		->action.run()->actionCompletable.onComplete()
    -->CreateWorkerFunction implements Function<ScheduledAction, Completable>
    		- Worker actualWorker
    		+ apply(final ScheduledAction action):return Completable
    			-> return new WorkerCompletable(action)

    		-->WorkerCompletable extends Completable
    			- ScheduledAction action
    			+ subscribeActual(CompletableObserver actionCompletable)
    				-> actionCompletable.onSubscribe(action) -> action.call(actualWorker, actionCompletable)
    -->QueueWorker extends Worker
    		- AtomicBoolean unsubscribed
    		- FlowableProcessor<ScheduledAction> actionProcessor
    		- Worker actualWorker

    		+ schedule(@NonNull final Runnable action, final long delayTime, @NonNull final TimeUnit unit):return Disposable
    			-> return delayedAction
    		+ schedule(@NonNull final Runnable action):return Disposable
    			-> return immediateAction



Schedulers.java:Scheduler 的工厂类，产生五个标准的调度器
	- Scheduler SINGLE 		= RxJavaPlugins.initSingleScheduler(new SingleTask())
	- Scheduler COMPUTATION = RxJavaPlugins.initComputationScheduler(new ComputationTask())
	- Scheduler IO 			= RxJavaPlugins.initIoScheduler(new IOTask())
	- Scheduler TRAMPOLINE 	= TrampolineScheduler.instance()
	- Scheduler NEW_THREAD 	= RxJavaPlugins.initNewThreadScheduler(new NewThreadTask())

	+computation()
		->return RxJavaPlugins.onComputationScheduler(COMPUTATION) 
	+io()
		->return RxJavaPlugins.onIoScheduler(IO)
	+trampoline()
		->return TRAMPOLINE	
	+newThread()
		->return RxJavaPlugins.onNewThreadScheduler(NEW_THREAD)
	+single()
		->return RxJavaPlugins.onSingleScheduler(SINGLE)
	+from(@NonNull Executor executor)
		->return new ExecutorScheduler(executor)
	+start()
	+shutdown()

	-->IOTask			implements Callable<Scheduler>  -> call() -> IoHolder.DEFAULT
	-->NewThreadTask	implements Callable<Scheduler>  -> call() -> NewThreadHolder .DEFAULT
	-->SingleTask		implements Callable<Scheduler>  -> call() -> SingleHolder.DEFAULT
	-->ComputationTask	implements Callable<Scheduler>  -> call() -> ComputationHolder.DEFAULT
	



-------------------------------------------------------------
ObservableSource<T>.java:interface,被观察者的基础接口，T 为泛型		
	+subscribe(@NonNull Observer<? super T> observer)			

Observable<T>.java--->implements ObservableSource<T>,被观察者
	+subscribe(Observer<? super T> observer):final,return void
		->判断observer是否为空
		->observer = RxJavaPlugins.onSubscribe(this, observer),设置observer的钩子,获取后再判空处理
		->subscribeActual(observer)
	+subscribeActual(Observer<? super T> observer):abstract,return void 抽象类,交给子类实现


Observer<T>.java:interface,观察者
	+void onSubscribe(@NonNull Disposable d):同步或异步的方法处理或取消关联的观察者
	+void onNext(@NonNull T t)
	+void onError(@NonNull Throwable e)
	+void onComplete()

Disposable.java:interface,用来处理相关资源的
	+void dispose()
	+boolean isDisposed():当返回 true 时，表示资源已经被处理
 
-----------------------------------------------------
RxJavaPlugins.java:钩子，注入
	-Function<? super Flowable, ? extends Flowable> onFlowableAssembly

	+static <T> Flowable<T> onAssembly(@NonNull Flowable<T> source)
		->onFlowableAssembly为空->return source
		->onFlowableAssembly不为空->return apply(onFlowableAssembly, source)

	+static <T, R> R apply(@NonNull Function<T, R> f, @NonNull T t)
		->return f.apply(t)
----------------------------------------------------------------------------------------------------
Function<T, R>.java:interface,T:输入的值，R:返回的值
	+ R apply(@NonNull T t) throws Exception:根据业务和输入的 T 值，返回另外的值

Action.java:interface,和线程 Runnable 类似
	+void run() throws Exception
------------------------------------------------------
FlowableJust<T>:extends Flowable<T> implements ScalarCallable<T>
	- T value

	+subscribeActual(Subscriber<? super T> s)
		->s.onSubscribe(new ScalarSubscription<T>(s, value))
	+call()
		->return value;

FlowableFromCallable<T>.java--->extends Flowable<T> implements Callable<T>
	- Callable<? extends T> callable

	+subscribeActual(Subscriber<? super T> s)

------------------------------------------------------
====== subject ========
Subject<T>.java:--->extends Observable<T> implements Observer<T>,abstract
	+abstract boolean hasObservers()
	+abstract boolean hasThrowable()
	+abstract boolean hasComplete()
	+abstract Throwable getThrowable()
AsyncSubject<T>.java--->extends Subject<T>
BehaviorSubject<T>.java--->extends Subject<T>
PublishSubject<T>.java--->extends Subject<T>
ReplaySubject<T>.java--->extends Subject<T>
SerializedSubject<T>.java--->extends Subject<T> implements NonThrowingPredicate<Object>
UnicastSubject<T>.java--->extends Subject<T>

CompletableSource.java:interface
	+void subscribe(@NonNull CompletableObserver cs)
Completable.java--->implements CompletableSource
CompletableSubject.java--->extends Completable implements CompletableObserver

CompletableObserver.java:interface
	+void onSubscribe(@NonNull Disposable d)
	+void onComplete()
	+void onError(@NonNull Throwable e)

MaybeSource<T>.java:interface
	+void subscribe(@NonNull MaybeObserver<? super T> observer);
Maybe<T>.java--->implements MaybeSource<T>
MaybeSubject<T>.java--->extends Maybe<T> implements MaybeObserver<T>

MaybeObserver<T>.java:interface
	+void onSubscribe(@NonNull Disposable d)
	+void onSuccess(@NonNull T t)
	+void onError(@NonNull Throwable e)
	+void onComplete()


SingleSource<T>.java:interface
	+void subscribe(@NonNull SingleObserver<? super T> observer)


Single<T>.java--->implements SingleSource<T>
SingleSubject<T>.java--->extends Single<T> implements SingleObserver<T>

SingleObserver<T>.java:interface
	+void onSubscribe(@NonNull Disposable d)
	+void onSuccess(@NonNull T t)
	+void onError(@NonNull Throwable e)

======= subject ===========

BiFunction<T1, T2, R>.java:interface
	+R apply(@NonNull T1 t1, @NonNull T2 t2) throws Exception

	