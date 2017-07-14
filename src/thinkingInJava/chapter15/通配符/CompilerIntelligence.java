package thinkingInJava.chapter15.通配符;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CompilerIntelligence {
	
	public static void main(String[] args) {
		List<? extends Fruit> flist = //new ArrayList<>();
				Arrays.asList(new Apple());
//		flist.add(new Apple());//error
		Apple apple = (Apple)flist.get(0);
		flist.contains(new Apple());
		flist.indexOf(new Apple());
		flist.contains(new String());
	}

}
