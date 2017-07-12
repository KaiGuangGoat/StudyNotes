package thinkingInJava.chapter15.泛型方法;

public class Counter{
	private static int id = 1;
	public static int get(){
		id++;
		return id;
	}
}
