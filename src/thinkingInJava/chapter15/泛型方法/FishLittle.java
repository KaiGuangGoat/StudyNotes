package thinkingInJava.chapter15.泛型方法;

import thinkingInJava.chapter15.泛型接口.Generator;

public class FishLittle {
	private static long counter = 1;
	private final long id = counter++;
	
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
