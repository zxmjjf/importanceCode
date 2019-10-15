package com.book2.B4_InteRnet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * @author jjf
 * <time>2019.10.12  16h</time>
 * <describe>开启本地服务器，端口为8089， 只进行输出操作，输出10次数据</describe>
 */
public class B07_SocketServer {
    /**
     * @mainFuntion 测试
     * @param args = null
     */
    public static void main(String[] args) {
        /*开启本地服务器套接字*/
        try (ServerSocket serverSocket = new ServerSocket(8089)) {
            /*处理客户端连接操作*/
            try {
                int num = 0;//控制while循环的次数；
                System.out.println("服务器正等待客户端连接.....");//断点
                Socket server = serverSocket.accept();//与客户端对接的服务端套接字
                System.out.println("客户端连接成功");//断点

                //搭建服务端输出流
                PrintWriter writer = new PrintWriter(server.getOutputStream(), true, StandardCharsets.UTF_8);

                /*进行100次while循环*/
                while (num < 100) {
                    num++;

                    /*服务端输出数据*/
                    if (num < 10) {
                        writer.println(num);
                        System.out.print(num + "\t");//断点
                        Thread.sleep(100);
                    }//end if

                }//end while

                Thread.sleep(15000);//15秒后线程线程自动结束

            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            } //end Socket_try

        } catch (IOException e) {
            e.printStackTrace();
        } //end ServerSocket_try
    }
}
