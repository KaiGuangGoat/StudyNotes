

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
