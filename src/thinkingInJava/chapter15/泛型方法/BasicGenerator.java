package thinkingInJava.chapter15.泛型方法;

import thinkingInJava.chapter15.泛型接口.Fibonacci;
import thinkingInJava.chapter15.泛型接口.Generator;

public class BasicGenerator<T> implements Generator<T>{
	
	private Class<T> type;
	
	private BasicGenerator(Class<T> type){
		this.type = type;
	}

	@Override
	public T next() {
		try {
			return type.newInstance();
		} catch (Exception e){
			throw new RuntimeException(e);
		}
	}
	
	public static <T> BasicGenerator<T> create(Class<T> type){
		return new BasicGenerator<>(type);
	}
	
	public static void main(String[] args) {
		System.out.println(create(Fibonacci.class).next());
	}
	

}
