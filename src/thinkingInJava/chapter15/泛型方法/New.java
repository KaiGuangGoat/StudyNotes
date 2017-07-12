package thinkingInJava.chapter15.泛型方法;

import java.util.HashMap;
import java.util.Map;

public class New {
	
	public static <K,V> Map<K,V> map(){
		return new HashMap<K, V>();
	}
	
	public static void main(String[] args) {
		Map<String,String> map = map();
	}

}
