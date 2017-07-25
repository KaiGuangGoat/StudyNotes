package thinkingInJava.chapter17.set和存储顺序;

public class SetType {
	public int i;
	public SetType(int i){this.i = i;}
	
	@Override
	public boolean equals(Object obj) {
		return obj instanceof SetType && this.i == ((SetType)obj).i;
	}
	@Override
	public String toString() {
		return Integer.toString(i);
	}
}
