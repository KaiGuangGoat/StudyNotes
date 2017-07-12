package thinkingInJava.chapter15.泛型方法;

import java.util.HashSet;
import java.util.Set;

public class Sets {

	/**
	 * 并集
	 * @param a
	 * @param b
	 * @return
	 */
	public static <T> Set<T> union(Set<T>a,Set<T>b){
		Set<T> result = new HashSet<>(a);
		result.addAll(b);
		return result;
	}
	
	/**
	 * 交集
	 * @param a
	 * @param b
	 * @return
	 */
	public static <T> Set<T> intersection(Set<T>a,Set<T>b){
		Set<T> result = new HashSet<>(a);
		result.retainAll(b);
		return result;
	}
	
	/**
	 * 补集
	 * @param superSet
	 * @param subSet
	 * @return
	 */
	public static <T> Set<T> different(Set<T>superSet,Set<T>subSet){
		Set<T> result = new HashSet<>(superSet);
		result.removeAll(subSet);
		return result;
	}
	
	/**
	 * 并集移除交集部分
	 * @param a
	 * @param b
	 * @return
	 */
	public static <T> Set<T> complement(Set<T>a,Set<T>b){
		return different(union(a, b), intersection(a, b));
	}
}
