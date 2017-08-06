

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
		FileReader

	PipedReader

	StringReader


4、Writer implements Closeable,Flushable,Appendable

	BufferedWriter

	CharArrayWriter

	FilterWriter

	OutputStreamWriter
		FileWriter

	PipedWriter

	PrintWriter

	StringWriter

例子：
	
	BufferedReader in = new BufferedReader(new FileReader(filename));
	String s;
	while((s = in.read) != null){
		stringBuilder.append(s).append("\n");
	}


标准 IO 
	System.out ---> PrintStream
	System.in  ---> InputStream



5、NIO

// 攻破JAVA NIO技术壁垒: http://www.importnew.com/19816.html

NIO 有三大核心部分：Channel,Buffer,Selector。数据总是从通道读取到缓冲区，
或者是从缓冲区写入通道

唯一与通道交互的缓冲器是 ByteBuffer

	Channel:interface
	+close():-->关闭通道
	+isOpen():return boolean-->判断通道是否打开
		||
		||
		ReadableByteChannel:interface
		+read(ByteBuffer dst):return int-->将字节序列从通道读取到指定的缓冲区
		WritableByteChannel:interface
		+write(ByteBuffer src):return int-->将字节序列从给定的缓冲区写入此通道
		ByteChannel extends ReadableByteChannel,WritableByteChannel:interface
			DatagramChannel
			FileChannel
			SocketChannel

	FileChannel:用于读取、写入、映射和操作文件的通道

	Buffer
		Buffer 的使用步骤：
			1、分配空间
			2、写入数据到Buffer
			3、调用 flip()方法
			4、从 Buffer 中读取数据
			5、调用clear()或compact()方法

			写数据到 Buffer：
				1、Channel 向 Buffer 写数据:fileChannel.read(buf);
				2、通过 Buffer 的put():buf.put(...);
			从 Buffer 读数据：
				1、从 Buffer 读取到Channel:channel.write(buf);
				2、通过 Buffer 的get()方法:buf.get();

		Buffer 的几个重要变量：

			capacity		缓冲区的总长度

			position		下一个要操作的数据元素的位置

			limit 			缓冲区数组中不可操作的下一个元素的位置：limit<=capacity

			mark			用于标记当前position的前一个位置
