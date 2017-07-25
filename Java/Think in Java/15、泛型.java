	class Fruit{}
	class Apple extends Fruit{}
	class Jonathan extends Apple{}

	协变：
	
	逆变：
	
	writeTo(List<? super Apple> apples){
		apples.add(new Apple());
		apples.add(new Jonathan());
//		apples.add(new Fruit()); //error
	}
	
	泛型的问题：
	
		1、任何基本类型不能作为类型参数，解决办法是使用基本类型的包装器类
		
		2、实现参数化接口
		
			一个类不能实现同一个泛型接口的两种变体
		
		3、转型和警告
			
			使用带有泛型类型参数的转型或 instanceof 都不会有任何效果
			
		4、重载
			//会报错，类型擦除导致生成相同的方法签名，必须使用不同的方法名
			public class 重载<W,T> {
				public void flist(W w){}
				public void flist(T t){}
			}
	
		5、基类劫持了接口
			
			public class Hourly extends Employee implements Payable<Employee>{
	
			}
		
	自限定的类型
		
			public class SelfBounded<? extends SelfBounded<T>>{}
	
	