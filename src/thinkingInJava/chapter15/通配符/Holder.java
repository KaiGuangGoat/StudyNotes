package thinkingInJava.chapter15.通配符;

public class Holder<T> {
	private T value;
	
	public Holder(T value){this.value = value;}

	public T get() {
		return value;
	}

	public void set(T value) {
		this.value = value;
	}
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return value.equals(obj);
	}
	
	public static void main(String[] args) {
		Holder<Apple> apple = new Holder<Apple>(new Apple());
		Apple a = apple.get();
		apple.set(a);
		
//		Holder<Fruit> fruit1 = apple;//error
		
		Holder<? extends Fruit> fruit = apple;
		Fruit fit = fruit.get();
		Apple ap = (Apple) fruit.get();
//		fruit.set(fit); //error
		
		Holder<? super Fruit> superFruit = new Holder<Fruit>(new Apple());
		Apple app = (Apple) superFruit.get();
		superFruit.set(app);
		
	}
	
	
}
