package thinkingInJava.chapter15.泛型方法;

import java.util.ArrayList;
import java.util.List;

public class GenericVarargs {
	
	public static <T> List<T> makeList(T ...ts){
		ArrayList<T> list = new ArrayList<>();
		for(T t:ts){
			list.add(t);
		}
		return list;
	}

}
