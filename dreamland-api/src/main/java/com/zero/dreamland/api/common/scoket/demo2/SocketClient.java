package com.zero.dreamland.api.common.scoket.demo2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.text.SimpleDateFormat;
import java.util.Date;
 
public class SocketClient {
    public static String _pattern = "yyyy-MM-dd HH:mm:ss SSS";
    public static SimpleDateFormat format = new SimpleDateFormat(_pattern);
    // 设置超时间
    public static int _sec = 5;
 
    public static void main(String[] args) {
        System.out.println("----------Client----------");
 
        Socket socket = null;
        try {
            // 与服务端建立连接
            socket = new Socket("127.0.0.1", 8001);
            socket.setKeepAlive(true);
            socket.setSoTimeout(_sec * 1000);
 
            System.out.println(format.format(new Date())+" : 建立了链接");

            // 往服务写数据
            Writer writer = new OutputStreamWriter(socket.getOutputStream());


            do {
                Thread.sleep(100);
                writer.write("客戶端發送的數據\n");
                writer.flush();
                BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                System.out.println(format.format(new Date()) + "\nServer:" + br.readLine());
            } while (true);


             




        } catch (SocketTimeoutException e) {
            System.out.println(format.format(new Date()) + "\n" + _sec + "秒没收到回复 我下啦\n\n\n\n\n");
            e.printStackTrace();
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}