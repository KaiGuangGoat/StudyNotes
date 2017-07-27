

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

	