Observable 	:���۲���
Observer 	:�۲���

Publisher 	:������
Subscriber 	:������

Consumer 	:������
package io.reactivex
	���ࣺFlowable.....>Subscriber
		  Observable...>Observer
		  Single.......>SingleObserver
		  Completable..>CompletableObserver


Publisher<T>:interface
	+subscribe(Subscriber<? super T> s)

Flowable.java:--->implements Publisher<T>
	+subscribe(FlowableSubscriber<? super T> s)->ObjectHelper.requireNonNull(s, "s is null")�ж��Ƿ�Ϊ null,���׳��쳣
											   ->Subscriber<? super T> z = RxJavaPlugins.onSubscribe(this, s):����
											   ->subscribeActual(z):ʵ�ʵ��ð󶨣���������ʵ��
	+Flowable<T> just(T item)
		->return RxJavaPlugins.onAssembly(new FlowableJust<T>(item)):ʵ�ʷ��� FlowableJust ʵ��
	+Disposable subscribe(Consumer<? super T> onNext):
		->return  subscribe(onNext, Functions.ON_ERROR_MISSING,Functions.EMPTY_ACTION, FlowableInternalHelper.RequestMax.INSTANCE)
	+Disposable subscribe(Consumer<? super T> onNext, Consumer<? super Throwable> onError,
            					Action onComplete, Consumer<? super Subscription> onSubscribe)
		->���ĸ������п�
		->LambdaSubscriber<T> ls = new LambdaSubscriber<T>(onNext, onError, onComplete, onSubscribe)
		->subscribe(ls)
		->return ls
	+fromCallable(Callable<? extends T> supplier)
		->return RxJavaPlugins.onAssembly(new FlowableFromCallable<T>(supplier))->ʵ�ʷ��� FlowableFromCallable ʵ��
	+subscribeOn(Scheduler scheduler)

-------------Subscriber----------------------
Subscriber<T>.java:interface
	+onSubscribe(Subscription s)
	+onNext(T t)
	+onError(Throwable t)
	+onComplete()
FlowableSubscriber<T>.java--->extends Subscriber:interface
	+onSubscribe(Subscription s)

Subscription.java:interface,������
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
Scheduler.java:�����࣬�̵߳���

Schedulers.java:Scheduler �Ĺ����࣬���������׼�ĵ�����
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
ObservableSource<T>.java:interface,���۲��ߵĻ����ӿڣ�T Ϊ����		
	+subscribe(@NonNull Observer<? super T> observer)			

Observable<T>.java--->implements ObservableSource<T>,���۲���
	+subscribe(Observer<? super T> observer):final,return void
		->�ж�observer�Ƿ�Ϊ��
		->observer = RxJavaPlugins.onSubscribe(this, observer),����observer�Ĺ���,��ȡ�����пմ���
		->subscribeActual(observer)
	+subscribeActual(Observer<? super T> observer):abstract,return void ������,��������ʵ��


Observer<T>.java:interface,�۲���
	+void onSubscribe(@NonNull Disposable d):ͬ�����첽�ķ��������ȡ�������Ĺ۲���
	+void onNext(@NonNull T t)
	+void onError(@NonNull Throwable e)
	+void onComplete()

Disposable.java:interface,�������������Դ��
	+void dispose()
	+boolean isDisposed():������ true ʱ����ʾ��Դ�Ѿ�������
 
-----------------------------------------------------
RxJavaPlugins.java:���ӣ�ע��
	-Function<? super Flowable, ? extends Flowable> onFlowableAssembly

	+static <T> Flowable<T> onAssembly(@NonNull Flowable<T> source)
		->onFlowableAssemblyΪ��->return source
		->onFlowableAssembly��Ϊ��->return apply(onFlowableAssembly, source)

	+static <T, R> R apply(@NonNull Function<T, R> f, @NonNull T t)
		->return f.apply(t)
----------------------------------------------------------------------------------------------------
Function<T, R>.java:interface,T:�����ֵ��R:���ص�ֵ
	+ R apply(@NonNull T t) throws Exception:����ҵ�������� T ֵ�����������ֵ

Action.java:interface,���߳� Runnable ����
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