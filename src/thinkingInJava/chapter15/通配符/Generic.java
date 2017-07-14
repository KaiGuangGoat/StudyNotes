package thinkingInJava.chapter15.通配符;

public class Generic {
	
	public static class Generic1<T> {
		public Generic1(){};
		public void writeTo(T t){
			
		}
	}
	
	public static class Generic2<T> {
		private T t;
		public T gen(){
			return t;
		}
	}
	
	public <T> void generic1(Generic1<? super Fruit> t){
		Apple f = new Apple();
		t.writeTo(f);
	}
	
	public <T> void generic2(Generic2<? extends Fruit> t){
		Fruit fruit = t.gen();
	}
	
	public static void main(String[] args) {
		Generic gen = new Generic();
		Generic1<Fruit> gen1 = new Generic1<>();
		gen.generic1(gen1);
		
		Generic2<Apple> gen2 = new Generic2<>();
		gen.generic2(gen2);
		
	}

}
