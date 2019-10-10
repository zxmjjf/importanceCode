package com.book2.B4_InteRnet.serverAndclient.server;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReference;

public class C_OpenServer {
    /*私有数据*/
    private ServerSocket serverSocket;
    private Socket server;
    private Scanner reader;
    private PrintWriter writer;

    /*模块1引用*/
    DefaultListModel<String> listModel;
    private AtomicReference<A_MyServer> myServerUI; //获得服务端图形界面

    private C_OpenServer() {
        /*打开服务端界面*/
        myServerUI = new AtomicReference<>();
        new Thread(() -> {
            myServerUI.set(new A_MyServer());
            WindowAdapter adapter = new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    try {
                        if (server != null && server.isConnected()) {
                            System.out.println("关闭服务器");
                            serverSocket.close();
                            server.close();
                        }
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    System.out.println("退出程序！");
                    System.exit(0);

                }
            };
            System.out.println("注册窗口关闭事件");
            myServerUI.get().getServerFrame().addWindowListener(adapter);
        }).start();

        /*开启服务线程*/
        try {
            serverSocket = new ServerSocket(8089);
            /*搭建服务端 套接字流*/
            try {
                System.out.println("服务端已开启...");
                server = serverSocket.accept();/*等待客户端连接*/
                if (server.isBound()) {
                    listModel = myServerUI.get().getB_module1().getListModel();
                    listModel.addElement("[主机" + myServerUI.get().clientNum + "]: " + server.getInetAddress().getHostAddress() + "     " + server.getLocalPort());
                    System.out.println(server.getLocalPort());
                }


                InputStream inputStream = server.getInputStream();/*服务器输入流*/
                OutputStream outputStream = server.getOutputStream();

                //转化流
                reader = new Scanner(inputStream, StandardCharsets.UTF_8);
                writer = new PrintWriter(outputStream, true, StandardCharsets.UTF_8);

                /*开启响应线程*/
                response();

            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 服务端响应
     */
    private void response() {
        //通告客户端
        writer.println("欢迎连接....");
        writer.println("ok");
        //响应客户端
        boolean done = false;
        while (!done && reader.hasNextLine()) {
            /*读取客户端请求*/
            String line = reader.nextLine();
            System.out.println("响应客户端： " + line + "\t\t处理成功.....");

            /*回应客户端*/
            writer.println(line + "\t我收到了， 我的处理是......");
            if (line.trim().equals("BYE")) {
                done = true;
                System.out.println("服务端 处理请求完毕......");
                System.out.println();

                //关闭资源
                try {
                    server.close();
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        /*开启服务端套接字线程*/
        new Thread(() -> {
            C_OpenServer openServer = new C_OpenServer();
        }).start();

    }
}
