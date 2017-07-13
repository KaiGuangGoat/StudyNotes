package thinkingInJava.chapter15.类型擦除;

import java.lang.reflect.Array;
import java.util.Arrays;

public class ArrayMaker<T> {
	
	private Class<T> kind;
	public ArrayMaker(Class<T> kind){
		this.kind = kind;
	}
	
	@SuppressWarnings("unchecked")
	public T[] create(int size){
		return (T[])Array.newInstance(kind, size);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
			ArrayMaker<String> am = new ArrayMaker<>(String.class);
			String[] strs = am.create(10);
			System.out.println(Arrays.toString(strs));
			
			String[] testStrs = new String[10];
			System.out.println(Arrays.toString(testStrs));
	}

}
