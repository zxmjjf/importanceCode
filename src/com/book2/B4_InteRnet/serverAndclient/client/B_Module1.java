package com.book2.B4_InteRnet.serverAndclient.client;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class B_Module1 {
    private JPanel jPanel; //客户端请求信息面板
    private JButton jButton;
    private JTextField jTextIP;
    private JTextField jTextPort;

    B_Module1(){
        /*客户端详细信息面板. 基本设置*/
        jPanel = new JPanel();
        jPanel.setBackground(Color.PINK);
        jPanel.setLayout(new BorderLayout(6, 6));
        //添加边框
        Border border = BorderFactory.createBevelBorder(1, Color.DARK_GRAY, Color.WHITE);
        Border border1 = BorderFactory.createTitledBorder(border, "连接远程服务器", 0, 0, new Font("华文彩云", Font.BOLD, 12), Color.RED);
        jPanel.setBorder(border1);

        /*配置组件*/
        JPanel jPanel1 = new JPanel();
        jPanel1.setLayout(new BorderLayout(6,6));
        jPanel1.setBackground(Color.PINK);
        JPanel jPaneL = new JPanel(); //主机/Ip面板
        jPaneL.setLayout(new BorderLayout(6,6));
        jPaneL.setBackground(Color.PINK);
        JPanel jPaneR = new JPanel(); //端口面板
        jPaneR.setBackground(Color.PINK);

        jPanel1.add(jPaneL);
        jPanel1.add(jPaneR, BorderLayout.EAST);
        jPanel.add(jPanel1); //


        //主机/Ip面板
        //1.标签
        JLabel jLabel = new JLabel("服务端IP：");
        jLabel.setFont(new Font("隶书", Font.BOLD, 14));
        jPaneL.add(jLabel, BorderLayout.WEST);
        //2.输入框
        jTextIP = new JTextField();
        jPaneL.add(jTextIP, BorderLayout.CENTER);

        //端口面板
        //1.标签
        JLabel jLabel2 = new JLabel("端口号：");
        jLabel2.setFont(new Font("隶书", Font.BOLD, 11));
        jPaneR.add(jLabel2);
        //2.输入框
        jTextPort = new JTextField(4);
        jPaneR.add(jTextPort);
        //3.距离控制

        //3.连接按钮
        jButton = new JButton("link / 链接");
        jButton.setFont(new Font("隶书", Font.BOLD, 10));

        //4.组装
        jPanel.add(jButton, BorderLayout.EAST);

    }

    protected JPanel getJPanel() {
        return this.jPanel;
    }

    protected JButton getJButton() {
        return jButton;
    }

    protected JTextField getJTextIp() {
        return jTextIP;
    }

    protected JTextField getjTextPort() {
        return jTextPort;
    }
}
