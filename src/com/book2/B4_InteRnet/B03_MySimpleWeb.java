package com.book2.B4_InteRnet;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * @author jjf
 * @version 1.0.1
 * <describe>简单的客户端和服务端互交</describe>
 */
public class B03_MySimpleWeb {
    public static void main(String[] args) throws IOException {
        //实现服务端
        Thread serve = new Thread(() ->{
            try (ServerSocket serverSocket = new ServerSocket(8189)) {
                //搭建服务端 套接字流
                try (Socket server = serverSocket.accept();
                     InputStream inputStream = server.getInputStream();
                     OutputStream outputStream = server.getOutputStream()) {

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

                        server.close();
                        serverSocket.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        //实现客户端
        Thread client = new Thread(() ->{
            //搭建客户端 套接字流
            try (Socket socket = new Socket(InetAddress.getLocalHost().getHostAddress(), 8189);
                 Scanner in = new Scanner(socket.getInputStream(), StandardCharsets.UTF_8);
                 PrintWriter writer = new PrintWriter(socket.getOutputStream(), true, StandardCharsets.UTF_8)) {

                while (in.hasNextLine()) {
                    String ok = in.nextLine();
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
                while (in.hasNextLine()) {
                    String line = in.nextLine();
                    System.out.println("接收服务端响应： " + line);
                }

            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        });

        serve.start();
        client.start();
    }
}
