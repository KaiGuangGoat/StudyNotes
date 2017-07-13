package thinkingInJava.chapter15.类型擦除;

public class StrongLight implements LightInterface{

	@Override
	public void open() {
		System.out.println("StrongLight open...");
	}

	@Override
	public void close() {
		System.out.println("StrongLight close...");
	}
	
	public <T extends LightInterface> void take(T t){
		t.open();
		t.close();
	}
	
	public static void main(String[] args) {
		StrongLight light = new StrongLight();
		light.take(light);
	}
	
}
