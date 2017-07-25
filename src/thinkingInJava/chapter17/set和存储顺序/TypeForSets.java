package thinkingInJava.chapter17.set和存储顺序;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

public class TypeForSets {
	
	static <T> Set<T> fillSet(Set<T> set,Class<T> type){
		try {
			for(int i=0;i<10;i++){
				set.add(type.getConstructor(int.class).newInstance(i));
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return set;
	}
	
	static <T> void test(Set<T> set,Class<T> type){
		fillSet(set, type);
		fillSet(set, type);
		fillSet(set, type);
		System.out.println(set);
	}
	
	public static void main(String[] args) {
		test(new HashSet<HashType>(), HashType.class);
		test(new LinkedHashSet<HashType>(),HashType.class);
		test(new TreeSet<TreeType>(),TreeType.class);
		
		test(new HashSet<SetType>(), SetType.class);//don't work
		test(new HashSet<TreeType>(), TreeType.class);
		test(new LinkedHashSet<SetType>(), SetType.class);
		test(new LinkedHashSet<TreeType>(),TreeType.class);
		
		try {
			test(new TreeSet<SetType>(), SetType.class);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		try {
			test(new TreeSet<HashType>(),HashType.class);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
