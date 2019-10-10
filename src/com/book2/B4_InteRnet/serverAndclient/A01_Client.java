package com.book2.B4_InteRnet.serverAndclient;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

public class A01_Client {
    /*客户端计数器*/
    private static int unmber = 0;
    private JFrame clientFrame; //客户端图形图形界面
    private JTextField jTextField; //文本框
    private JButton linkButton; //连接按钮
    private JTextArea jTextArea;
    private JButton requestButton;
    private Socket socket;
    private AtomicBoolean connected;

    /**
     * <describe>客户端构造器</describe>
     */
    public A01_Client(){
        //设置组件属性
        clientFrame = new JFrame("客户端" + ++unmber);

        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        SwingUtilities.updateComponentTreeUI(clientFrame);

        //总面板
        JPanel jPanelS = new JPanel();
        jPanelS.setLayout(new BorderLayout(5, 5));
        jPanelS.setBackground(Color.PINK);

        //连接操作：
        //1.标签
        JPanel jPanel1 = new JPanel();
        jPanel1.setBackground(Color.PINK);
        JLabel jLabel = new JLabel("服务端IP：");
        jLabel.setFont(new Font("华文琥珀", Font.BOLD, 15));
        //2.文本框
        try {
            jTextField = new JTextField(InetAddress.getLocalHost().getHostAddress() + "    8189");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        //3.连接按钮
        linkButton = new JButton("link / 链接");
        //4.组装
        jPanel1.setLayout(new BorderLayout());
        jPanel1.add(jLabel, BorderLayout.WEST);
        jPanel1.add(jTextField);
        jPanel1.add(linkButton, BorderLayout.EAST);

        //客户端请求文本框
        //1.边框
        Font borderFont = new Font("华文琥珀", Font.BOLD, 15);
        Border border = BorderFactory.createEtchedBorder(0);
        TitledBorder border1 = BorderFactory.createTitledBorder(border, "客户端");
        border1.setTitleColor(Color.BLUE);
        border1.setTitleFont(borderFont);
        //2.文本区
        jTextArea = new JTextArea();
        jTextArea.setLineWrap(true);
        JScrollPane jScrollPane = new JScrollPane(jTextArea);
        jScrollPane.setBorder(border1);

        //3.组装
        jPanelS.add(jPanel1, BorderLayout.NORTH);
        jPanelS.add(jScrollPane);
        //3.按钮
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.PINK);

        requestButton = new JButton("Request / 请求");
        requestButton.setForeground(Color.RED);
        buttonPanel.add(requestButton);
        jPanelS.add(buttonPanel, BorderLayout.SOUTH);

        clientFrame.setVisible(true);
        clientFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        clientFrame.setBounds((unmber) * 500, 100, 400, 300);
        clientFrame.add(jPanelS);

        //配置连接和请求
        link();
        request();
    }

    /**
     * <describe>建立连接</describe>
     */
    private void link() {
        //将文本区设置为提示信息
        String tip = "客户端为连接，请先建立连接：在上方文本框输入：\n" +
                "[主机名称或其Ip地址]，再输入[端口号]，用任意空格隔开!\n" +
                "如上默认输入是连接本地服务器（若已经开启本地服务器）\n";
        jTextArea.setText(tip);

        //处理客户端链接过程
        connected = new AtomicBoolean(false); //只开启一个读取线程
        linkButton.addActionListener(event ->{
            try {

                if (socket != null && socket.isConnected()) {
                    //如果线程已连接，则，connected.get() == true
                    if (connected.get())
                        JOptionPane.showMessageDialog(clientFrame, "线程已连接");
                }else{
                    //开启客户端套接字
                    socket = new Socket();

                    //读取输入并处理的数据
                    String text = jTextField.getText();
                    String[] address = text.split("\\s+");
                    //检测数据
                    if (address.length < 1) {
                        throw new IOException();
                    }
                    //链接远程服务器
                    socket.connect(new InetSocketAddress(address[0], Integer.parseInt(address[1])), 3000); //最多3秒延迟
                    if (socket.isConnected()) {
                        //客户端读取服务器响应线程任务设置
                        jTextArea.append("连接成功：等待客户端回复线程已经开启...");
                        respondThread(connected);
                        //延迟。让上面函数的功能正常完成
                        Thread.sleep(100);
                        connected.set(true);
                        //提示连接成功
                        jTextField.setText("连接成功....");
                        linkButton.setText("已连接");
                        //jTextArea.append(""); //清空提示

                    } else {
                        linkButton.setText("连接已中断");
                        connected.set(false);
                    }
                }

            } catch (IOException | InterruptedException e) {
                if (e.getClass() == SocketTimeoutException.class) {
                    JOptionPane.showMessageDialog(clientFrame, "链接超时, 请重试");
                    jTextArea.append(e.getMessage() + "\n");
                } else {
                    JOptionPane.showMessageDialog(clientFrame, "链接失败，请检查主机及端口是否正确");
                    jTextArea.append(e.getMessage() + "\n");
                }
            }
        });

    }

    /**
     * <descibe>发送客户端请求并响应服务端</descibe>
     */
    private void request(){
        //安装请求功能
        //获取连接数据
        /*String text = jTextField.getText();
        String[] address = text.split("\\s+");
        try {
            socket.connect(new InetSocketAddress(address[0], Integer.parseInt(address[1])), 3000); //最多3秒延迟
        } catch (IOException e) {
            jTextArea.setText(e.getMessage());
            jTextArea.append("\t连接已断开....");
        }*/
        final PrintWriter[] writer = {null};
        Thread requestThread = new Thread(() ->{
            //首先让线程等待0.5s再执行任务
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            /*保证只有一个客户端输出流*/
            AtomicBoolean connect = new AtomicBoolean(false);
            /*注册事件监听程序*/
            requestButton.addActionListener(event ->{
                if (socket != null && socket.isConnected()) {
                    if (!connect.get()) {
                        /*建立客户端输出流*/
                        try {
                            writer[0] = new PrintWriter(socket.getOutputStream(), true, StandardCharsets.UTF_8);
                            connect.set(true); //一旦输出流被建立就不再重新建立输出流
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    //按钮响应，发出请求
                    if (!socket.isConnected()) {
                        //再次检查是否已连接
                        connected.set(false);
                        linkButton.setText("未连接");
                        JOptionPane.showMessageDialog(clientFrame, "服务器未连接，请先连接"); //提示未连接
                    } else {
                        //捕获输入信息
                        System.out.println("发送请求"); //测试语句
                        String request = jTextArea.getText();
                        System.out.println(request);
                        jTextArea.setText("");
                        //写出请求
                        writer[0].println(request + "\n");
                    }
                }else {
                    /*socket = new Socket();
                    try {
                        socket.connect(new InetSocketAddress("horstmann.com", 80), 3000); //最多3秒延迟
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (socket.isConnected()) {
                        JOptionPane.showMessageDialog(clientFrame, "服务器连接主机为： " + socket.getInetAddress().getHostName()); //提示未连接
                    }*/
                    connected.set(false);
                    linkButton.setText("未连接");
                    JOptionPane.showMessageDialog(clientFrame, "发送失败，未连接到服务器!");
                }
            });

        });

        //启动线程
        requestThread.start();

    }

    /**
     * <describe>在服务器连接完成时，建立客户端输入流</describe>
     * @param connected
     */
    private void respondThread(AtomicBoolean connected) {
        final Scanner[] scanner = {null};
        Thread getResult = new Thread(() ->{
            //监听服务端响应
            if (!connected.get()) {
                try {
                    System.out.println("开启Scanner");
                    scanner[0] = new Scanner(socket.getInputStream(), StandardCharsets.UTF_8);
                    while (scanner[0].hasNextLine()) {
                        //一直读取服务端响应
                        jTextArea.append(scanner[0].nextLine() + "\n");
                    }
                } catch (IOException e) {
                    jTextArea.append(e.getMessage());
                }
            }

        });
        //启动读取服务端响应线程
        getResult.start();
    }

    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException, UnknownHostException {
        A01_Client a01_client = new A01_Client();
        //a01_client.link();
        //A01_Client a01_client1 = new A01_Client();
    }
}
