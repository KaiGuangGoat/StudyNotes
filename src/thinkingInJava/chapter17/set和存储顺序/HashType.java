package thinkingInJava.chapter17.set和存储顺序;

public class HashType extends SetType{

	public HashType(int i) {
		super(i);
	}

	@Override
	public int hashCode() {
		return i;
	}
}
