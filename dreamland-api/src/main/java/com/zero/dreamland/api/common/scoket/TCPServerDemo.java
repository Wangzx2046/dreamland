package com.zero.dreamland.api.common.scoket;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;
import java.io.InputStream;

public class TCPServerDemo {
	public static void main(String[] args)  throws IOException {
	
		System.out.println("TCP服务端端启动...");
		
		//1.创建服务端对象
		ServerSocket ss = new ServerSocket(10000);
		
		//2.获取连接过来的客户端对象
		Socket s = ss.accept();	//阻塞式
		
		//获取ip地址
		String ip = s.getInetAddress().getHostAddress();
		
		//3.通过socket对象获取输入流,要读取客户端发来的数据
		InputStream in = s.getInputStream();
		
		byte[] buf = new byte[1024];
		
		//因为知道发的数据不会很多...所有就直接可以弄了
		int len = in.read(buf);
		String text = new String(buf,0,len);
		System.out.println(ip+":"+text);
		
		//4.关闭资源
		s.close();
		ss.close();
	
	}
}
