package thinkingInJava.chapter15.简单泛型;

import java.util.ArrayList;
import java.util.Random;

public class RandomList<T> {
	
	ArrayList<T> storage = new ArrayList<T>();
	
	Random random = new Random();
	
	public void add(T t){
		storage.add(t);
	}
	
	public T select(){
		return storage.get(random.nextInt(storage.size()));
	}
	
	public static void main(String[] args) {
		RandomList<String> randomList = new RandomList<>();
		String test = "fke kkk hello world Java firefox google disk sky hahaha";
		for(String item:test.split(" ")){
			randomList.add(item);
		}
		for(int i=0;i<10;i++){
			System.out.println(randomList.select());
		}
	}

}

//sky
//firefox
//world
//kkk
//kkk
//hahaha
//sky
//fke
//hello
//disk

