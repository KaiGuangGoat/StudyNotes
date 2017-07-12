package thinkingInJava.chapter15.泛型接口;

import java.util.Iterator;

public class IterableFibonacci extends Fibonacci implements Iterable<Integer>{

	private int n;
	
	public IterableFibonacci(int count){n=count;}
	
	@Override
	public Iterator<Integer> iterator() {
		// TODO Auto-generated method stub
		
		return new Iterator<Integer>() {

			@Override
			public boolean hasNext() {
				// TODO Auto-generated method stub
				return n>0;
			}

			@Override
			public Integer next() {
				// TODO Auto-generated method stub
				n--;
				return IterableFibonacci.this.next();
			}

			@Override
			public void remove() {
				// TODO Auto-generated method stub
				
			}
		};
	}
	
	public static void main(String[] args) {
		for(int i:new IterableFibonacci(10)){
			System.out.print(i+" ");
		}
	}

}
