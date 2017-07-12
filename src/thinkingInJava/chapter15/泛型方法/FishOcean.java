package thinkingInJava.chapter15.泛型方法;

import java.util.ArrayList;
import java.util.Random;

public class FishOcean {
	
	public void eat(FishBig bigFish,FishLittle littleFish){
		System.out.println(bigFish.toString() + " eat " + littleFish.toString());
	}
	
	public static void main(String[] args) {
		Random random = new Random();
		FishOcean ocean = new FishOcean();
		ArrayList<FishBig> bigFishs = new ArrayList<FishBig>();
		Generators.fill(bigFishs, FishBig.generator(), 5);
		ArrayList<FishLittle> littleFishs = new ArrayList<>();
		Generators.fill(littleFishs, FishLittle.generator(), 30);
		for(FishLittle littleFish:littleFishs){
			ocean.eat(bigFishs.get(random.nextInt(bigFishs.size())), littleFish);
		}
	}

}
