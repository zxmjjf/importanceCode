package com.book2.B4_InteRnet.serverAndclient.client;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.atomic.AtomicInteger;

public class A_MyClient {

    private JFrame clientFrame;
    private static AtomicInteger clientSum = new AtomicInteger(0);
    private JPanel jPanel;
    /*模块*/
    private B_Module1 b_module1;
    private B_Module2 b_module2;
    private B_Module3 b_module3;
    private B_Module4 b_module4;

    A_MyClient(){
        clientFrame = new JFrame("客户端" + "【" + clientSum.getAndIncrement() + "】");
        jPanel = new JPanel();
        jPanel.setLayout(new GridLayout(1, 2, 6, 6));

        Container containerPanel = clientFrame.getContentPane();
        containerPanel.setLayout(new BorderLayout(4, 4));
        containerPanel.setBackground(Color.WHITE);
        jPanel.setBackground(Color.PINK);

        containerPanel.add(jPanel);



        /*配置模块*/
        //模块1
        b_module1 = new B_Module1();
        containerPanel.add(b_module1.getJPanel(), BorderLayout.NORTH);

        //模块2
        b_module2 = new B_Module2();
        jPanel.add(b_module2.getJPanel());

        //模块3
        b_module3 = new B_Module3();
        jPanel.add(b_module3.getJPanel());

        //模块4
        b_module4 = new B_Module4();
        containerPanel.add(b_module4.getJPanel(), BorderLayout.SOUTH);


        /*窗口基本设置*/
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        SwingUtilities.updateComponentTreeUI(clientFrame);
        clientFrame.setBounds((clientSum.get() - 1)  * 100, (clientSum.get() - 1) * 100, 700, 700);
        clientFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        clientFrame.setVisible(true);
        clientFrame.setResizable(false);
    }

    /*获取模块*/
    protected JFrame getClientFrame() {
        return clientFrame;
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
        A_MyClient myClient1 = new A_MyClient();
        //A_MyClient myClient2 = new A_MyClient();

    }
}
