package thinkingInJava.chapter17.理解Map;

public class Groundhog {
	
	private int number;
	public Groundhog(int number){
		this.number = number;
	}
	@Override
	public String toString() {
		return "Groundhot#"+number;
	}
	
	public int getNumber(){
		return number;
	}

}
