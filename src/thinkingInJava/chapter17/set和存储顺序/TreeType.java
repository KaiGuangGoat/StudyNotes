package thinkingInJava.chapter17.set和存储顺序;

public class TreeType extends SetType implements Comparable<TreeType>{

	public TreeType(int i) {
		super(i);
	}

	@Override
	public int compareTo(TreeType treeType) {
		// TODO Auto-generated method stub
		return i<treeType.i?1:(i==treeType.i?0:-1);
	}

}
