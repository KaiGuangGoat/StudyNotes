package thinkingInJava.chapter15.泛型方法;

import thinkingInJava.chapter15.泛型接口.Generator;

public class FishBig {
	private static long counter = 1;
	private final long id = counter++;
	
	private FishBig(){};
	
	public static Generator<FishBig> generator(){
		return new Generator<FishBig>() {
			@Override
			public FishBig next() {
				return new FishBig();
			}
		};
	}
	
	@Override
	public String toString() {
		return "FishBig-"+id;
	}
	
}
