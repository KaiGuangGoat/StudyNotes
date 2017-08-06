

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

==========================================================================
LinkedList 源码笔记，基于JDK7
	
	LinkedList 是基于双链表实现的，链表的节点定义如下：

	private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

    size:存储的元素的个数

    first:首节点,要么为 null，要么 prev==null,item!=null

    last:尾节点，要么为 null,要么 next==null,item!=null

    创建：

    	new LinkedList():创建一个空的集合

   	添加元素:

   		add(E element):return true;---> 保存last节点为l，创建一个新节点赋值给last，item=element，
   										prev指向l，next指向null，而l.next则指向last
   										其实就是把值添加到链表末尾

   	获取元素:

   		get(int index):return E;--->如果 index 小于集合长度的一半，从first开始算起，否则从last开始

   	插入元素:

   		add(int index,E element) ---> 先获取到index位置的节点e，然后在这个节点e和e前面的节点之间插入
   									  新的节点，并赋值element

   	删除元素:
   		
   		remove():return element;--->返回第一个节点的值，并删除掉第一个节点

   		remove(int index):return element;--->获取index位置的节点，并删除


LinkedList 和 ArrayList 的比较：
	
	1、顺序插入速度ArrayList会比较快，因为ArrayList是基于数组实现的，数组是事先new好的，
	只要往指定位置塞一个数据就好了；LinkedList 则不同，每次顺序插入的时候LinkedList将new
	一个对象出来，如果对象比较大，那么new的时间势必会长一点，再加上一些引用赋值的操作，
	所以顺序插入LinkedList必然慢于ArrayList

	2、基于上一点，因为LinkedList里面不仅维护了待插入的元素，还维护了Entry的前置Entry和
	后继Entry，如果一个LinkedList中的Entry非常多，那么LinkedList将比ArrayList更耗费一些内存

	3、数据遍历的速度，看最后一部分，这里就不细讲了，结论是：使用各自遍历效率最高的方式，
	ArrayList 的遍历效率会比LinkedList的遍历效率高一些

	4、有些说法认为LinkedList做插入和删除更快，这种说法其实是不准确的：

	（1）LinkedList 做插入、删除的时候，慢在寻址，快在只需要改变前后Entry的引用地址

	（2）ArrayList 做插入、删除的时候，慢在数组元素的批量copy，快在寻址

	所以当插入或删除数据时，越往前 LinkedList 效率越高，越往后 ArrayList 效率越高

	LinkedList 使用 foreach 速度更快，ArrayList 使用普通循环更快