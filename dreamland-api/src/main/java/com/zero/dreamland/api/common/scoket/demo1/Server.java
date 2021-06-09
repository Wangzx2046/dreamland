package com.zero.dreamland.api.common.scoket.demo1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    //监听端口
    private static final int PORT = 60020;

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        Socket socket = null;
        try {
            //建立服务器的Socket，并设定一个监听的端口PORT
            serverSocket = new ServerSocket(PORT);
            //由于需要进行循环监听，因此获取消息的操作应放在一个while大循环中
            while(true){
                 try {
                    //建立跟客户端的连接
                    socket = serverSocket.accept();
                 } catch (Exception e) {
                     System.out.println("建立与客户端的连接出现异常");
                     e.printStackTrace();
                 }
                 ServerThread thread = new ServerThread(socket);
                 thread.start();
            }
        } catch (Exception e) {
            System.out.println("端口被占用");
            e.printStackTrace();
        }        
        finally {
            serverSocket.close();
        }
    }
}

//服务端线程类
//继承Thread类的话，必须重写run方法，在run方法中定义需要执行的任务。
class ServerThread extends Thread {
    private Socket socket ;
    InputStream inputStream;
    OutputStream outputStream;
    
    public  ServerThread(Socket socket){
        this.socket=socket;
    }
    public void run(){
        try {
            while (true){
                //接收客户端的消息并打印
                System.out.println(socket);

                inputStream=socket.getInputStream();
                byte[] bytes = new byte[1024];
                inputStream.read(bytes);
                String string = new String(bytes);
                System.out.println(string);



                
                //向客户端发送消息
                outputStream = socket.getOutputStream();
                outputStream.write("OK".getBytes());
                System.out.println("OK");
                
            }
        } catch (Exception e) {
            System.out.println("客户端主动断开连接了");
            //e.printStackTrace();
        }
        //操作结束，关闭socket
        try{
            socket.close(); 
        }catch(IOException e){
            System.out.println("关闭连接出现异常");    
            e.printStackTrace();
        }
    }
}