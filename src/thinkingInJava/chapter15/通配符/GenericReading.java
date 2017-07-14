package thinkingInJava.chapter15.通配符;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GenericReading {
	
	static<T> T readExact(List<T> list){
		return list.get(0);
	}
	
	static List<Apple> apples = Arrays.asList(new Apple());
	static List<Fruit> fruits = Arrays.asList(new Fruit());
	
	static void f1(){
		Apple apple = readExact(apples);
		Fruit fruit = readExact(fruits);
		fruit = readExact(apples);
	}
	
	static class Reader<T>{
		T readExact(List<T> list){
			return list.get(0);
		}
	}
	
	static void f2(){
		Reader<Apple> reader = new Reader<>();
		reader.readExact(apples);
//		reader.readExact(fruits);//Error
		Reader<Fruit> reader2 = new Reader<>();
		reader2.readExact(fruits);
//		reader2.readExact(apples);//Error
	}
	
	static class ConvariantReader<T>{
		T readConvariant(List<? extends T> list){
			return list.get(0);
		}
	}
	
	void f3(){
		ConvariantReader<Fruit> cr = new ConvariantReader<>();
		Fruit fruit = cr.readConvariant(fruits);
//		Apple apple = cr.readConvariant(apples);//Error
		fruit = cr.readConvariant(apples);
	}

}
