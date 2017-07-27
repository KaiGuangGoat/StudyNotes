package thinkingInJava.chapter17.队列;

import java.util.PriorityQueue;

public class ToDoList extends PriorityQueue<ToDoList.ToDoItem>{
	
	static class ToDoItem implements Comparable<ToDoItem>{

		@Override
		public int compareTo(ToDoItem o) {
			// TODO Auto-generated method stub
			return 0;
		}
		
	}

}
