

任何自 InputStream 和 Reader 派生而来的类都有 read() 方法，用于读取单个字节或字节数组
任何自 OutputStream 和 Writer 派生而来的类都有 writer() 方法，用于写单个字节或字节数组
但是通常我们不会使用它们，而是使用别的类使用它们，可以提供更加方便的接口。

1、InputStream implements Closeable

  从不同的数据源产生输入的类。这些数据源包括：1)字节数组 2)String 对象 3)文件 4)管道 5)其他种类的流组成的序列

  6)其他数据源，如 Internet 连接。
	
	AudioInputStream

	ByteArrayInputStream

	FileInputStream

	FilterInputStream
		DataInputStream
		BufferedInputStream
		LineNumberInputStream
		PushbackInputStream

	ObjectInputStream

	PipedInputStream

	SequenceInputStream

	StringBufferInputStream

	org.omg.CORBA.portable.InputStream


2、OutputStream implements Closeable,Flushable

  决定了输出要去往的目标:1)字节数组 2)文件 3)管道

	ByteArrayOutputStream

	FileOutputStream

	FilterOutputStream
		DataOutputStream
		PrintStream
		BufferedOutputStream

	ObjectOutputStream

	PipedOutputStream

	org.omg.CORBA.portable.OutputStream


设计 Reader 和 Writer 继承层次结构主要是为了国际化。老的IO流只支持8位字节流，
而不能很好地处理16位的Unicode字符
	
3、Reader implements Closeable,Readable

	BufferedReader

	CharArrayReader

	FilterReader

	InputStreamReader

	PipedReader

	StringReader


4、Writer implements Closeable,Flushable,Appendable

	BufferedWriter

	CharArrayWriter

	FilterWriter

	OutputStreamWriter

	PipedWriter

	PrintWriter

	StringWriter

例子：
	
	BufferedReader in = new BufferedReader(new FileReader(filename));
	String s;
	while((s = in.read) != null){
		stringBuilder.append(s).append("\n");
	}