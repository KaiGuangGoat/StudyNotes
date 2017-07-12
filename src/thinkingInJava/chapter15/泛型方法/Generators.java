package thinkingInJava.chapter15.泛型方法;

import java.util.ArrayList;
import java.util.Collection;

import thinkingInJava.chapter15.泛型接口.Fibonacci;
import thinkingInJava.chapter15.泛型接口.Generator;

public class Generators {
	
	public static <T> Collection<T> fill(Collection<T> coll,Generator<T> gen,int n){
		for(int i=0;i<n;i++){
			coll.add(gen.next());
		}
		return coll;
	}
	
	public static void main(String[] args) {
		Collection<Integer> fibonaccis = fill(new ArrayList<Integer>(),new Fibonacci(), 10);
		for(int i:fibonaccis){
			System.out.print(i+" ");
		}
	}

}
