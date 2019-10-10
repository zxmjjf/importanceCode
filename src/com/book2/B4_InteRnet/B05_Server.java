package com.book2.B4_InteRnet;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class B05_Server {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(8189)) {
            //搭建服务端 套接字流
            System.out.println("正等待连接。。。");
            try (Socket server = serverSocket.accept();
                 InputStream inputStream = server.getInputStream();
                 OutputStream outputStream = server.getOutputStream()) {
                System.out.println("已有客户端连接成功。。。");
                //转化流
                try (Scanner in = new Scanner(inputStream, StandardCharsets.UTF_8)) {
                    PrintWriter printWriter = new PrintWriter(outputStream,  true, StandardCharsets.UTF_8);
                    //通告客户端
                    printWriter.println("Hello!, Enter Bye to exit.");
                    printWriter.println("我可以为你做你说能及的事情。。");
                    printWriter.println("ok");
                    //响应客户端
                    boolean done = false;
                    while (!done && in.hasNextLine()) {
                        //读取客户端请求
                        String line = in.nextLine();
                        System.out.println("响应客户端： " + line + "\t\t处理成功.....");

                        //回应客户端
                        printWriter.println(line + "\t我收到了， 我的处理是......");
                        if (line.trim().equals("BYE")) {
                            done = true;
                            System.out.println("服务端 处理请求完毕......");
                            System.out.println();
                        }
                    }

                    System.out.println(in.hasNextLine());
                    System.out.println("关闭服务器！");
                    server.close();
                    serverSocket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
