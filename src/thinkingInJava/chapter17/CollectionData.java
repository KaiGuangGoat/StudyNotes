package thinkingInJava.chapter17;

import java.util.ArrayList;
import java.util.Collection;

public class CollectionData<T> extends ArrayList<T> {
	
	public CollectionData(){
		
	}
	
	public static void main(String[] args) {
		Collection<String> c = new ArrayList<>();
		c.add("ss");
		c.add("string2");
		c.add("string3");
		System.out.println(c.toArray(new String[0]).length);;
		int i = 0;
		System.out.println(i>>1);
	}

}
