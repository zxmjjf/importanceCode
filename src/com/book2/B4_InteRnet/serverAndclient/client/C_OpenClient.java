package com.book2.B4_InteRnet.serverAndclient.client;

import javax.swing.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class C_OpenClient {
    private int numOfClients;
    private int clientID;
    private AtomicBoolean connected = new AtomicBoolean(false);
    private AtomicBoolean haveListener = new AtomicBoolean(false);
    /*客户端界面参数*/
    private AtomicReference<A_MyClient> myClientUI; //获得服务端图形界面

    /*底层服务参数*/
    private Socket socket;
    private Scanner reader;
    private PrintWriter writer;

    public C_OpenClient() {
        /*启动客户端UI线程*/
        myClientUI = new AtomicReference<>();
        new Thread(() -> {
            myClientUI.set(new A_MyClient());
        }).start();

        /*休眠1.5s*/
        try {
            Thread.sleep(1500);
            link();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /*是否正在连接检测线程*/
    private void isConneting() {
        new Thread(() -> {
            while (socket.isConnected()) {
                try {
                    /*每隔秒1检测一次*/
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            /*如果连接已经断开*/
            connected.set(false);
            System.out.println("连接断开...");
            JOptionPane.showMessageDialog(myClientUI.get().getClientFrame(), "客户端连接已断开", "连接断开", JOptionPane.ERROR_MESSAGE);
        }).start();
    }

    /*注册UI组件事件*/
    private void link() {
        /*获取图形界面参数*/
        A_MyClient myClient = myClientUI.get();

        try {
            /*设置模块1文本框的默认值*/
            InetAddress inetAddress = InetAddress.getLocalHost(); //获得远程地址
            myClient.getB_module1().getJTextIp().setText(inetAddress.getHostAddress()); //把地址写入文本框
            myClient.getB_module1().getjTextPort().setText(8189 + ""); //把端口写入文本框
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        /*注册图形界面的所有事件监听的按钮监听事件*/
        myClient.getB_module1().getJButton().addActionListener(event -> {
            /*搭建连接*/
            try {
                if (socket != null && connected.get()) {
                    /*避免重复连接*/
                    //如果线程已连接，则，connected.get() == true
                    if (connected.get()) {
                        JOptionPane.showMessageDialog(myClientUI.get().getClientFrame(), "线程已连接");
                    }
                }else{
                    /*正式连接*/
                    socket = new Socket(); //建立连接套接字
                    /*获得连接参数*/
                    String address = myClient.getB_module1().getJTextIp().getText(); //读取地址
                    int port = Integer.parseInt(myClient.getB_module1().getjTextPort().getText()); //读取端口
                    //开始连接
                    socket.connect(new InetSocketAddress(address, port), 4000); //超时6s
                    try {
                        socket.setSoTimeout(4000);
                    } catch (SocketException e) {
                        JOptionPane.showMessageDialog(myClient.getClientFrame(), "数据操作超时", "超时", JOptionPane.ERROR_MESSAGE);
                    }

                    /*设置连接成功标记*/
                    connected.set(true);
                    /*如果连接成功*/
                    if (connected.get()) {
                        System.out.println("连接成功"); //测试
                        numOfClients++; //客户端数目加1
                        clientID = numOfClients; //绑定客户端ID
                        connected.set(true);
                        /*检测连接是否断开*/
                        isConneting();

                        /*搭建 客户端——服务器 流*/
                        reader = new Scanner(socket.getInputStream(), StandardCharsets.UTF_8); //接收服务器响应流
                        writer = new PrintWriter(socket.getOutputStream(), true, StandardCharsets.UTF_8); //发送请求信息流
                        /*while (reader.hasNextLine()) {
                            String ok = reader.nextLine();
                            if (ok.trim().equals("ok")) {
                                break;
                            }
                            System.out.println(ok);
                        }*/

                        /*启动线程 客户端请求线程*/
                        /*new Thread(() -> {

                            boolean done = false;
                            String request;  //请求信息
                            while (!done) {
                                try {
                                    Thread.sleep(500);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                System.out.print("客户端请求： ");
                                request = myClient.getB_module2().getList().getSelectedValue();
                                writer.println(request);

                                if (request.trim().equals("BYE")) {
                                    done = true;
                                    System.out.println("ok，请求完毕");
                                    System.out.println();
                                }
                            }
                        }).start();*/

                    }
                }

            } catch (IOException e) {
                if (e instanceof ConnectException) {
                    myClient.getB_module4().getJTextArea().append("Connection refused: 连接受拒\t");
                } else if (e instanceof SocketTimeoutException) {
                    myClient.getB_module4().getJTextArea().append("SocketTimeoutException：连接超时\t");
                } else {
                    e.printStackTrace();
                }
            }

            /*如果搭建成功则继续注册所有监听事件, 分开注册 */
            //1. 客户端请求界面事件监听程序
            if (connected.get() && !haveListener.get()) {
                /*断开连接事件监听程序*/
                myClient.getB_module2().getJButton1().addActionListener(actionEvent -> {
                    /*try {
                        socket.close(); //服务端也会被关闭
                    } catch (IOException e) {
                        e.printStackTrace();
                    }*/

                    /*关闭之间的数据流*/
                    /*reader.close();
                    writer.close();*/
                    socket = new Socket();
                    connected.set(false);

                    /*删除所有监听事件*/

                });
                /*发送请求事件事件监听程序*/
                myClient.getB_module2().getJButton2().addActionListener(actionEvent  ->{
                    String request;  //请求信息
                    request = myClient.getB_module2().getList().getSelectedValue();
                    /*确认数据不为空*/
                    if (request == null) {
                        JOptionPane.showMessageDialog(myClient.getClientFrame(), "请先选择要发送的数据！", "未选择数据", JOptionPane.WARNING_MESSAGE);
                    } else {
                        writer.println(request);
                        if (request.trim().equals("BYE")) {
                            System.out.println("ok，请求完毕");
                            System.out.println();
                        }
                    }

                });
                /*删除所选事件监听程序*/
                myClient.getB_module2().getJButton3().addActionListener(actionEvent -> {
                    if (!myClient.getB_module2().getList().isSelectionEmpty()) {
                        myClient.getB_module2().getListModel().remove(myClient.getB_module2().getList().getSelectedIndex());
                    }
                });
            }

            //2. 服务器响应界面事件监听程序
            if (connected.get() && !haveListener.get()) {
                /*启动线程 客户端接线程*/
                new Thread(() ->{
                    System.out.println("开启服务端监听对接");
                    //接收服务端响应
                    while (reader.hasNextLine()) {
                        String line = reader.nextLine();
                        myClient.getB_module3().getListModel().addElement(line);
                    }
                }).start();
            }

            //3. 编辑请求界面事件监听程序
            if (connected.get() && !haveListener.get()) {
                /*清空编辑文本框*/
                myClient.getB_module4().getJTextArea().setText("");
                myClient.getB_module4().getJButton().addActionListener(actionEvent -> {
                    String request = myClient.getB_module4().getJTextArea().getText();
                    myClient.getB_module2().getListModel().addElement(request);
                    /*清空编辑窗口*/
                    myClient.getB_module4().getJTextArea().setText("");
                });
            }

            if (connected.get()) {
                haveListener.set(true); /*关闭事件注册能力*/
            }
        });


    }

    public static void main(String[] args) {
        /*开启客户端套接字线程*/
        new Thread(() -> {
            C_OpenClient openServer = new C_OpenClient();
        }).start();

    }
}
