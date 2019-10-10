package com.book2.B4_InteRnet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class B01_FirstInternetTest {
    public static void main(String[] args) throws IOException {
        /*//连接超时版， 无响应则返回
        Socket socket1 = new Socket();
        socket1.connect(new InetSocketAddress("horstmann.com", 80), 5000);*/

        //无超时版
        final Scanner[] scanners = {null};
        try {
            Socket socket = new Socket("horstmann.com", 80);
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true, StandardCharsets.UTF_8);

            new Thread(() -> {
                try {
                    scanners[0] = new Scanner(socket.getInputStream(), StandardCharsets.UTF_8);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                /*try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
                while (scanners[0].hasNextLine()) {
                    /*synchronized (socket) {
                        String line = scanners[0].nextLine();
                        System.out.println(line);
                    }*/
                    String line = scanners[0].nextLine();
                    System.out.println(line);
                }
            }).start();

            socket.setSoTimeout(1000);
            //writer.println("i am boy");
            synchronized (socket) {
                writer.println("GET / HTTP/1.1");
                writer.println("Host:horstmann.com");
            }

            Thread.sleep(2000);
            writer.println("GET / HTTP/1.1");
            writer.println("Host:horstmann.com");
            System.out.println("第二次发送完成");

            writer.println();
            /*System.out.println(socket.getInetAddress());
            System.out.println(socket.getLocalAddress());*/
            //writer.println("Jjf will comming!"); 会有不同的输出结果
            /*while (in.hasNextLine()) {
                String line = in.nextLine();
                System.out.println(line);
            }*/
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
