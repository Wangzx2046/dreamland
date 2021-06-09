package com.zero.dreamland.api.common.scoket;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class TCPClientDemo {
    public static void main(String[] args) throws IOException {
		System.out.println("TCP客户端启动...");
		
        //1.建立客户端socket服务
        Socket s = new Socket("127.0.0.1", 10000);

        //2.获取socket流中的输出流
        OutputStream out = s.getOutputStream();

        //使用输出流将指定的数据写出去
        out.write("tcp demo: Hello Java1".getBytes());

        //关闭资源
        s.close();
    }
}
