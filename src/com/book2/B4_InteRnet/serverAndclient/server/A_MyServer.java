package com.book2.B4_InteRnet.serverAndclient.server;

import javax.swing.*;
import java.awt.*;

public class A_MyServer {
    /*私有保护数据*/
    private JFrame serverFrame;
    private JPanel jPanel;
    /*模块*/
    private B_Module1 b_module1;
    private B_Module2 b_module2;
    private B_Module3 b_module3;
    private B_Module4 b_module4;

    /*客户端计数器，保护数据*/
    protected int clientNum = 0;

    /**
     * 构造器
     */
    public A_MyServer(){
        /*窗口*/
        serverFrame = new JFrame("服务器");

        /*设置窗口底层容器*/
        jPanel = new JPanel();
        serverFrame.getContentPane().add(jPanel);
        jPanel.setLayout(new GridLayout(2, 2));

        /*配置客户端主机面板*/
        //1.加载模块1
        b_module1 = new B_Module1();
        jPanel.add(b_module1.getJPanel());

        //1.加载模块2
        b_module2 = new B_Module2();
        jPanel.add(b_module2.getJPanel());

        //1.加载模块3
        b_module3 = new B_Module3();
        jPanel.add(b_module3.getJPanel());

        //1.加载模块4
        b_module4 = new B_Module4();
        jPanel.add(b_module4.getJPanel());

        /*窗口基本设置*/
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        SwingUtilities.updateComponentTreeUI(serverFrame);
        serverFrame.setBounds(400, 50, 800, 600);
        //serverFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        serverFrame.setVisible(true);
    }

    /*模块接口*/

    protected JFrame getServerFrame() {
        return serverFrame;
    }

    protected B_Module1 getB_module1() {
        return b_module1;
    }
    protected B_Module2 getB_module2() {
        return b_module2;
    }
    protected B_Module3 getB_module3() {
        return b_module3;
    }
    protected B_Module4 getB_module4() {
        return b_module4;
    }

    public static void main(String[] args) {
        A_MyServer a10My_server = new A_MyServer();
        a10My_server.serverFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
