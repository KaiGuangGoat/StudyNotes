
1、HTTP(HyperText Transfer Protocol) 位于应用层

	应用层:
	
	.应用层协议定义应用进程间（运行的程序）的通信规则,应用层协议主要 HTTP、SMTP、FTP

	.交互的数据单元称为报文

	.基本上是基于 C/S方式


2、HTTP 介绍:

	.规定了应用进程间通信的准则

	.特点	

		-> 无连接

		-> 无状态

		-> 传输可靠性高

		-> 兼容性好

		-> 简单快速

		-> 灵活		


3、报文详解:

	HTTP 报文分为请求报文和响应报文


					|	--> 请求行 : 请求方法、请求地址、协议版本

		请求报文 ---| 	--> 请求头 : 说明服务器、客户端或报文的部分信息

					|	--> 请求体 : 存放需要发送给服务器的数据信息



	请求行:

		.组成格式，空格不能省略

			-->请求方法 空格 请求地址 空格 协议版本\r\n


		.. 请求方法

				方法类别			意义

				OPTION			请求"选项"的信息

				HEAD			请求读取"URL标志信息的首部"信息

				GET				请求读取"URL标志的信息"的信息

				POST			为服务器添加信息

				PUT				为指定的URL下添加（存储）一个文档

				DELETE			删除指定URL所标志的信息

				TRACE			用于进行环回测试的请求报文

				CONNECT			用于代理服务器

		.. 请求路径

				组成: <协议>:/ /<主机>:<端口>/<路径>

					-->协议为应用层的协议，主机为主机域名或IP地址，

		.. 协议版本

				HTTP 协议版本主要是 1.0、1.1、2.0


		举个栗子:

			URL: https://github.com/KaiGuangGoat/StudyNotes

			请求方式为 GET

			请求版本为 HTTP 1.1

			则请求行为:GET /KaiGuangGoat/StudyNotes HTTP/1.1


	请求头:

		. 作用:说明客户端、服务器或报文的部分信息

		. 使用方式:采用 "header(字段名):value(值)"的方式

		. 常用请求头

			1.请求和响应报文的通用 Header

			名称							作用

			Content-Type		请求体/响应体的类型，如:text/plain、application/json

			Accept				说明接收的类型，可以多个值，用","(半角逗号)分开

			Content-Length		请求体/响应体的长度，单位字节

			Content-Encoding	请求体/响应体的编码格式，如gzip，deflat

			Accept-Encoding		告知对方我方接受的 Content-Encoding

			ETag				给当前资源的标识，和Last-Modified,If-None-Match,If-Modified-Since配合，用于缓存控制

			Cache-Control		取值一般为 no-cache或max-age=xx,xx为整数，表示该资源缓存有效期(秒)


			2.常见请求 Header

			名称							作用

			Authorization	 	用于设置身份认证信息

			User-Agent			用户标识，如：OS 和浏览器的类型和版本

			If-Modified-Since 	值为上一次服务器返回的 Last-Modified 值，用于确认某个资源是否被更改过，没有更改(304)过久从缓存读取

			If-None-Match		值为上一次服务器返回的 ETag 值，一般会和 If-Modified-Since 一起出现

			Cookie				已有的 Cookie

			Referer				标识请求引用自哪个地址，比如从页面A跳转到页面B，值为页面A的地址

			Host				请求的主机和端口号
			

	请求体

		.作用:用于存放需要发送给服务器的数据信息

		.使用方式:目前有三种

			-->1.数据交换格式

				请求体可以是任意类型的，需要服务器额外进行解析，比如 JSON，XML

			-->2.键值对的形式

				键值之间用=连接，每个键值对间用&连接，其只能用ASCII字符

			-->3.分部分形式

				请求体被分为多个部分，应用场景是文件上传。

					.每段以	--{boundary}开头

					.然后是该段的描述头

					.描述头之后空一行接内容

					.每段以--{boundary}--结束





					|	--> 状态行 : 协议版本、状态码、状态信息

		响应报文 ---| 	--> 请求头 : 说明服务器、客户端或报文的部分信息

					|	--> 请求体 : 存放需要发送给服务器的数据信息


			状态行格式:协议版本 空格 状态码 空格 状态信息\r\n

				状态码:

					类别				含义

					1xx			表示信息通知，如请求收到了或正在进行处理

					2xx			表示成功，如接受或知道了

					3xx			表示重定向，如要完成请求还必须采取进一步行动

					4xx			客户的差错，如请求中有错误的语法或不能完成：404

					5xx			表示服务器的差错，如服务器失效无法完成请求

				状态码详解：http://tool.oschina.net/commons?type=5


