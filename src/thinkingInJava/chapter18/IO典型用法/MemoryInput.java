package thinkingInJava.chapter18.IO典型用法;

import java.io.StringReader;
import java.nio.channels.ServerSocketChannel;

public class MemoryInput {
	
	public static void main(String[] args) throws Exception {
		StringReader in = new StringReader(BufferedInputFile.read("G:\\myeclipseWorkspace\\StudyNote\\src\\thinkingInJava\\chapter18\\IO典型用法\\MemoryInput.java"));
		int c;
		while((c=in.read())!=-1){
			System.out.print((char)c);
		}
	}

}
