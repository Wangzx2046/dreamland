package com.zero.dreamland.api.common.scoket.demo2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SocketServer {
    public static String pattern = "yyyy-MM-dd HH:mm:ss SSS";
    public static SimpleDateFormat format = new SimpleDateFormat(pattern);
    // 设置超时间
    public static int sec = 0;

    public static void main(String[] args) {
        System.out.println("----------Server----------");
        System.out.println(format.format(new Date()));

        ServerSocket server;
        try {

            server = new ServerSocket(8002);
            System.out.println("监听建立 等你上线\n");
            while (true) {
                Socket socket = server.accept();
                System.out.println(format.format(new Date()));
                System.out.println("建立了链接\n");

                BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));


                System.out.println(format.format(new Date()) + "\nClient:" + br.readLine() + "\n");
                Writer writer = new OutputStreamWriter(socket.getOutputStream());


                do {
                    Thread.sleep(100);
                    if (socket.isConnected()) {
                        writer.write("收到\n");
                        writer.flush();
                    }
                } while (socket.isConnected());

                /*System.out.println(format.format(new Date()));
                System.out.println("写完啦 你收下\n\n\n\n\n");*/
            }
        } catch (SocketTimeoutException e) {
            System.out.println(format.format(new Date()) + "\n" + sec + "秒没给我数据 我下啦\n\n\n\n\n");
            e.printStackTrace();
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}