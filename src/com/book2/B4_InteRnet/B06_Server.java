package com.book2.B4_InteRnet;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

public class B06_Server {
    private static int clientNum = 0;
    private static Socket server;
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(22)) {
            //搭建服务端 套接字流
//
            System.out.println("正等待连接。。。");
            boolean hadClient = false;
            while (clientNum > 0 || !hadClient) {
                try {
                    System.out.println("第" + (clientNum+1) + "次等待连接！");
                    server = serverSocket.accept();
                    hadClient = true;
                    System.out.println("开启数据处理线程");
                    ++clientNum;
                    Thread.sleep(100);
                    new Thread(() -> {
                        int num = clientNum;
                        System.out.println("第 " + (num) + " 个客户端连接成功！");
                        //转化流
                        try (InputStream inputStream = server.getInputStream();
                             OutputStream outputStream = server.getOutputStream();
                             Scanner in = new Scanner(inputStream, StandardCharsets.UTF_8)) {
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
                                //回应客户端
                                if (line.trim().equals("BYE")) {
                                    done = true;
                                    System.out.println("服务端 处理请求完毕......");
                                    printWriter.println("通信与关闭");
                                    printWriter.close();
                                    System.out.println();
                                }
                                printWriter.println(line + "\n我收到了， 我的处理是......");
                            }

                            System.out.println(in.hasNextLine());
                            System.out.println("关闭第 " + num + " 个客户端的通信");
                            System.out.println("客户端数目为：" + clientNum);
                            clientNum--;
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }).start(); //end Thread

                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }

            /*关闭服务器*/
            server.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
