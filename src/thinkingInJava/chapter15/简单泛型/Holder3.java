package thinkingInJava.chapter15.简单泛型;

public class Holder3<T> {
	private T a;
	
	public Holder3(T a){
		this.a = a;
	}

	public T getA() {
		return a;
	}

	public void setA(T a) {
		this.a = a;
	}
	
	public static void main(String[] args) {
		Holder3 holder = new Holder3<String>("test");
		System.out.println(holder.getA());
	}

}
