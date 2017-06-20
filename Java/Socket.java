一、网络基础

1、网络分层

	-> 应用层
	   .
	-> 运输层 ：TCP UDP
	   .
	-> 网络层 ：IP
	   .
	-> 数据链路层 
	   .
	-> 物理层 

2、端口号

一台主机的端口数为 2^16=65535 个，0~1023 为分配给系统的端口，socket可用的端口号为：1024~65535

3、Socket

Socket 基于 TCP 或 UDP 协议的使用和封装。需要成对存在，一个连接客户端，一个连接服务端

4、TCP协议

定义：Transmission Control Protocol，即传输控制协议，是一种传输层通信协议
	
	->基于TCP的应用层协议有 FTP、Telnet、SMTP、HTTP、POP3 与 DNS

特点：

	-> 面向连接		:使用TCP传输数据，必须先建立TCP连接，传输完成后释放连接，就像打电话那样

	-> 面向字节流	:InputStream ,OutputStream

	-> 全双工通信	:建立连接后双方都可以互传数据

	-> 可靠			:无差错，不重复，不丢失，顺序到达

TCP 建立连接

	三次握手
		Client ---> Server
		Client <--- Server
		Client ---> Server

	四次挥手
		Client ---> Server
		Client <--- Server
		Client <--- Server
		Client ---> Server

5、UDP协议

定义：User Datagram Protocol，即用户数据报协议，是一种传输层通信协议。
		
		->基于UDP的应用层协议有 TFTP、SNMP 与 DNS

特点：

	-> 无连接的		:不需要建立连接，直接发送。就像写信一样

	-> 不可靠的		:会出现丢包，使传输的数据出错

	-> 面向报文		:数据包，直接发送，不拆分，TCP 可拆分

	-> 没有拥塞控制	:TCP 发送的由于一些原因数据不能到达目的地，会重发，可能导致网络拥塞，需要进行拥塞控制，而 UDP 发送出去就不管了

二、Socket 


.定义：对 TCP / IP 协议进行封装的编程调用接口（API），成对出现


.使用类型：
	
	-> 流套接字		:基于 TCP 协议，采用流(stream)的方式发送

	-> 数据报套接字	:基于 UDP 协议，采用数据报文(datagram)的方式发送


.和 HTTP 对比
	
	->Socket 属于运输层，解决数据如何传输的问题，HTTP 属于应用层，解决的是如何包装数据

	->Socket 采用服务器主动发送数据的方式，HTTP 采用 请求-响应 的方式

三、Socket 的使用

TCP Socket : Socket.java ServerSocket.java 

UDP Socket : DatagramSocket.java 
