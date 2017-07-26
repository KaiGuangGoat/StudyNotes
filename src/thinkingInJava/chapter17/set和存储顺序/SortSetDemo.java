package thinkingInJava.chapter17.set和存储顺序;

import java.util.Collections;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

public class SortSetDemo {
	public static void main(String[] args) {
		SortedSet<String> sortedSet = new TreeSet<>();
		Collections.addAll(sortedSet, "one two three four 五 six 七 eight nine ten".split(" "));
		System.out.println(sortedSet);
		String low = sortedSet.first();
		String heigh = sortedSet.last();
		System.out.println(low);
		System.out.println(heigh);
		Iterator<String> iter = sortedSet.iterator();
		for(int i=0;i<10;i++){
			if(i==3)low=iter.next();
			else if(i==5)heigh = iter.next();
			else iter.next();
		}
		System.out.println(low);
		System.out.println(heigh);
		System.out.println(sortedSet.subSet(low, heigh));
		System.out.println(sortedSet.headSet(heigh));
		System.out.println(sortedSet.tailSet(low));
	}
}
