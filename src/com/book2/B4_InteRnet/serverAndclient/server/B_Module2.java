package com.book2.B4_InteRnet.serverAndclient.server;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class B_Module2 {
    private JPanel jPanel; //客户端请求信息面板
    private DefaultListModel<String> listModel;
    private int requestSum = 0;
    private int handleSum = 0;

    B_Module2(){
        /*客户端请求面板. 基本设置*/
        jPanel = new JPanel();
        jPanel.setBackground(Color.PINK);
        jPanel.setLayout(new BorderLayout());
        //添加边框
        Border border = BorderFactory.createBevelBorder(1, Color.DARK_GRAY, Color.WHITE);
        TitledBorder border1 = BorderFactory.createTitledBorder(border, "客户端请求", 2, 2, new Font("华文彩云", Font.BOLD, 15), Color.RED);
        jPanel.setBorder(border1);



        /*处理客户端列表*/
        //1.列表基本设置, 添加滚动列表，设置元素字体，颜色和选中颜色，添加边框等
        listModel = new DefaultListModel<>();
        JList<String> list = new JList<>(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); //在设置选择模式时，一定要保证list有可选项，不让会出现异常
        JScrollPane jScrollPane1 = new JScrollPane(list);

        list.setForeground(Color.BLACK);
        list.setFont(new Font("隶书", Font.PLAIN, 15));
        list.setSelectionBackground(Color.WHITE);
        list.setSelectionForeground(Color.RED);

        border = BorderFactory.createBevelBorder(2, Color.PINK, Color.PINK);
        border1 = BorderFactory.createTitledBorder(border, "请求数/处理数： " + requestSum + "/" + handleSum + "  ", 5, 5, new Font("华文新魏", Font.BOLD + Font.ITALIC, 13), Color.BLUE);
        border1 = BorderFactory.createTitledBorder(border1, requestSum + "/" + handleSum + "  ", 5, 5, new Font("华文新魏", Font.BOLD + Font.ITALIC, 15), Color.RED);
        jScrollPane1.setBorder(border1);
        jPanel.add(jScrollPane1);
    }

    protected JPanel getJPanel() {
        return this.jPanel;
    }
}
