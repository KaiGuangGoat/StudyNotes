package thinkingInJava.chapter15.泛型方法;

import thinkingInJava.chapter15.泛型接口.Generator;

public class FishLittle {
	private static final long id = Counter.get();
	
	private FishLittle(){}
	
	public static Generator<FishLittle> generator(){
		return new Generator<FishLittle>() {
			@Override
			public FishLittle next() {
				return new FishLittle();
			}
		};
	}
	
	@Override
	public String toString() {
		return "FishLittle-"+id;
	}
	
}
