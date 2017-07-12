package thinkingInJava.chapter15.泛型接口;

public class Fibonacci implements Generator<Integer>{
	
	int count = 0;
	
	@Override
	public Integer next() {
		return fib(count++);
	}
	
	public int fib(int n){
		if(n<2) return 1;
		return fib(n-2)+fib(n-1);
	}
	
	public static void main(String[] args) {
		Fibonacci mFibonacci = new Fibonacci();
		for(int i=0;i<18;i++){
			System.out.print(mFibonacci.next() + " ");
		}
	}

}
