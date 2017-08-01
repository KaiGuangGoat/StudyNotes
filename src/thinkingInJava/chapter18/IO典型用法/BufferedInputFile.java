package thinkingInJava.chapter18.IO典型用法;

import java.io.BufferedReader;
import java.io.FileReader;

public class BufferedInputFile {
	
	public static String read(String fileName) throws Exception{
		BufferedReader in = new BufferedReader(new FileReader(fileName));
		StringBuilder sb = new StringBuilder();
		String s = null;
		while((s=in.readLine()) != null){
			sb.append(s).append("\n");
		}
		in.close();
		return sb.toString();
	}

}
