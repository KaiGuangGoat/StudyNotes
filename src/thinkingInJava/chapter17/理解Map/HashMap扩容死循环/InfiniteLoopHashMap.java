package thinkingInJava.chapter17.理解Map.HashMap扩容死循环;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class InfiniteLoopHashMap extends Thread{
	private static AtomicInteger ai = new AtomicInteger(0);
	private static HashMap<Integer, Integer> map = new HashMap<>();
	
	@Override
	public void run() {
		while(ai.get()<100000){
			map.put(ai.get(), ai.get());
			ai.incrementAndGet();
		}
	}
	
	public static void main(String[] args) {
		InfiniteLoopHashMap map1 = new InfiniteLoopHashMap();
		InfiniteLoopHashMap map2 = new InfiniteLoopHashMap();
		InfiniteLoopHashMap map3 = new InfiniteLoopHashMap();
		InfiniteLoopHashMap map4 = new InfiniteLoopHashMap();
		InfiniteLoopHashMap map5 = new InfiniteLoopHashMap();
		map1.start();
		map2.start();
		map3.start();
		map4.start();
		map5.start();
	}

}
