package thinkingInJava.chapter15.简单泛型;

public class TwoTuple<A, B> {
	public final A first;
	public final B Second;
	public TwoTuple(A first,B second){
		this.first = first;
		this.Second = second;
	}
	
	public static class ThreeTuple<A,B,C> extends TwoTuple<A, B>{
		
		public final C third;

		public ThreeTuple(A first, B second, C third) {
			super(first, second);
			// TODO Auto-generated constructor stub
			this.third = third;
		}
		
	}

}
