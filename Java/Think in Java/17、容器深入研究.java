

7、队列
	
	优先级队列

		优先级队列的排序顺序是通过 Comparable 进行控制的


8、理解Map

	HashMap

	TreeMap:基于红黑树的实现，唯一带有 subMap() 方法的Map，返回一个子树
		|
		----SortedMap:TreeMap 的实现，可以确保键处于排序状态

	LinkedHashMap

	WeakHashMap

	ConcurrentHashMap

	IdentifyHashMap

	
	Map 的键要求和 Set 的一样，任何键都必须具有一个 equals()方法；

	如果键用于散列 Map 还必须具有恰当的 hashCod()方法

	如果用于 TreeMap 还必须实现 Comparable 接口

	##要使用自己的类作为 HashMap 的键值，必须同时重载 hashcode()和equals()方法

	hashcode(){
		return this.number;
	}

	equals(Object o){
		return o instanceof ThisClass && 
			(this.number == ((ThisClass)o).number);
	}

9、散列和散列码 hashcode

	在一个运行的进程中，相等的对象必须要有相同的哈希码

	不相等的对象可能也有相同的哈希码

	有同一个哈希值的对象不一定相等

	通过重写 equals 方法，你将申明一些对象与其他对象相等，
	但是原始的 hashCode 方法将所有的对象看做是不同的。所
	以你将会有不同哈希码的相同对象。例如，在 HashMap 中
	调用 contains 方法将会返回 false，即使这个对象已经
	被添加。



13、Java 1.0/1.1 的容器

	Vector 和 Enumeration

	HashTable

	Stack:继承自Vector
	
	BitSet

=================================================================
ArrayList 源码笔记，基于JDK7

	ArrayList 是基于数组的一个实现 Object[] elementData,elementData是其底层的数组

	size:元素的个数，自增和自减由add和remove方法改变

	创建

		new ArrayList()--->创建一个空的集合，elementData = {}; 指定默认为空的数组

	添加元素

		add(E element)--->如果为空的话则进行扩容，初始扩容大小为10，
						  之后为数组大小的一半(数组长度加上右移一位)

	插入元素

		add(int index,E element)--->index和index后面的元素后移一个位置
									给index位置的元素赋值element

	删除元素

		1、指定的元素后面的所有元素向前移动一个位置（System.arraycopy方法） 
		2、把最后一个元素指定为null，方便GC回收

		remove(int index):return oldValue(E);--->删除指定位置的元素

		remove(Object o):return boolean;--->删除检测到的第一个匹配的对象

	优点：
		1、随机访问元素非常快
		2、顺序添加元素非常方便
	缺点：
		1、删除元素比较耗费性能，特别是集合的元素很多的时候
		2、插入元素也是如此

	特点：
		1、允许添加元素为 null
		2、允许添加重复元素
		3、允许有序(读取数据的顺序是否和存放数据的顺序一致)
		4、非线程安全