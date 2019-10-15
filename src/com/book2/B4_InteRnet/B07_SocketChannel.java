package com.book2.B4_InteRnet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class B07_SocketChannel {
    public static void main(String[] args) throws InterruptedException {
        /*客户端套接字线程: 连接 + 操作*/
        Thread socketThread = new Thread(() ->{
            Scanner reader = null;
            System.out.println("socketThread 线程运行中...."); //断点
            try(SocketChannel socket = SocketChannel.open(new InetSocketAddress("localhost", 8089))){
                reader = new Scanner(socket, StandardCharsets.UTF_8);
                System.out.println("断点套接字连接成功....");//断点

                /**/
                System.out.println("socketThread isInterrupted: " + Thread.currentThread().isInterrupted());//断点
                while (!Thread.currentThread().isInterrupted()) {
                    System.out.print("Reading:  ");
                    if (reader.hasNextLine()) {
                        String line = reader.nextLine();
                        System.out.println(line);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            assert reader != null;
            System.out.println(reader.nextLine());
            System.out.println("此语句会执行嘛？");
        });
        socketThread.start(); //启动线程；由于对应的远程服务器每隔 0.1s 发送1次数据，共发送10次。故接收完数据之后此线程会阻塞

        Thread.sleep(10000);//main线程阻塞10s再运行
        System.out.println("main线程开始对socketThread线程进行中断！");
        socketThread.interrupt();//中断socketThread线程
    }

}
