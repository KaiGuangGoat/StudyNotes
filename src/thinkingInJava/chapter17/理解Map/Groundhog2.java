package thinkingInJava.chapter17.理解Map;

import java.util.HashMap;

public class Groundhog2 extends Groundhog{

	public Groundhog2(int number) {
		super(number);
	}
	
	@Override
	public int hashCode() {
		return getNumber();
	}
	
	@Override
	public boolean equals(Object obj) {
		return obj instanceof Groundhog2 
				&& getNumber() == ((Groundhog2)obj).getNumber();
	}
	
	public static void main(String[] args) {
		HashMap<Groundhog2, Integer> hashMap = new HashMap<>();
		hashMap.put(new Groundhog2(1), 1);
		hashMap.put(new Groundhog2(1), 2);
		
		System.out.println(hashMap);
	}

}
