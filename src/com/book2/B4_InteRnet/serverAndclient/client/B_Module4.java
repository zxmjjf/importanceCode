package com.book2.B4_InteRnet.serverAndclient.client;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class B_Module4 {
    private JPanel jPanel; //客户端请求信息面板
    private JTextArea jTextArea;
    private JButton jButton;

    B_Module4(){
        /*客户端详细信息面板. 基本设置*/
        {
            jPanel = new JPanel();
            jPanel.setBackground(Color.PINK);
            jPanel.setLayout(new BorderLayout(6, 6));
            //添加边框
            Border border = BorderFactory.createBevelBorder(1, Color.DARK_GRAY, Color.WHITE);
            Border border1 = BorderFactory.createTitledBorder(border, "编辑请求", 0, 0, new Font("华文彩云", Font.BOLD, 12), Color.RED);
            jPanel.setBorder(border1);
        } //end
        JPanel jPaneL = new JPanel();
        jPaneL.setBackground(Color.PINK);
        jPaneL.setLayout(new BorderLayout(6, 6));

        /*1.添加标签*/
        {
            JLabel jLabel = new JLabel("输入请求：");
            jLabel.setFont(new Font("隶书", Font.BOLD, 14));
            jPaneL.add(jLabel, BorderLayout.WEST);
            //2.输入框
            jTextArea = new JTextArea(3,15);
            JScrollPane textPanel = new JScrollPane(jTextArea);
            jTextArea.setLineWrap(true);
            jPaneL.add(textPanel);
        } //end

        /*3.添加按钮*/
        JPanel button = new JPanel();
        button.setLayout(new BorderLayout());
        {
            /*两个无关的面板*/ //可以考虑别的方式
            JPanel jPanel1 = new JPanel();
            JPanel jPanel2 = new JPanel();

            jPanel1.setBackground(Color.PINK);
            jPanel2.setBackground(Color.PINK);
            button.add(jPanel1, BorderLayout.NORTH);
            button.add(jPanel2, BorderLayout.SOUTH);

            button.setBackground(Color.PINK);
            jButton = new JButton("确认");
            jButton.setFont(new Font("隶书", Font.BOLD, 12));
            //jButton.setSize(10, 5);
            button.add(jButton);
        } //end

        /*组合组件*/
        jPanel.add(jPaneL);
        jPanel.add(button, BorderLayout.EAST); //end

    }

    protected JPanel getJPanel() {
        return this.jPanel;
    }

    protected JButton getJButton() {
        return jButton;
    }

    protected JTextArea getJTextArea() {
        return jTextArea;
    }
}
