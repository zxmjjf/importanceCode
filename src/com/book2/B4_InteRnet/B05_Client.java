package com.book2.B4_InteRnet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class B05_Client {
    private Socket socket;
    private Scanner reader;
    private PrintWriter writer;
    public static void main(String[] args) {
        /**<a>搭建客户端 套接字流</a>*/
        new B05_Client();
    }

    B05_Client(){
        try {
            socket = new Socket(InetAddress.getLocalHost().getHostAddress(), 8089);
            reader = new Scanner(socket.getInputStream(), StandardCharsets.UTF_8);
            writer = new PrintWriter(socket.getOutputStream(), true, StandardCharsets.UTF_8);
            while (reader.hasNextLine()) {
                String ok = reader.nextLine();
                if (ok.trim().equals("ok")) {
                    break;
                }

                System.out.println(ok);
            }
            //客户端请求
            boolean done = false;
            try (Scanner scanner = new Scanner(System.in)) {
                String read;
                while (!done) {
                    Thread.sleep(50);
                    System.out.print("客户端请求： ");
                    read = scanner.nextLine();
                    writer.println(read);
                    if (read.trim().equals("BYE")) {
                        done = true;
                        System.out.println("ok，请求完毕");
                        System.out.println();
                    }
                }
            }

            //接收服务端响应
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                System.out.println("接收服务端响应： " + line);
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
