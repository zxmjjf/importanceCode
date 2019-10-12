package com.book2.B4_InteRnet.serverAndclient.client;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class B_Module2 {
    //模块2底层面板
    private JPanel jPanel;
    private JButton jButton1;
    private JButton jButton2;
    private JButton jButton3;
    private JList<String> list;
    private DefaultListModel<String> listModel;


    public B_Module2() {
        /*客户端主机信息面板. 基本设置*/
        jPanel = new JPanel();
        jPanel.setBackground(Color.PINK);
        jPanel.setLayout(new BorderLayout());

        /*为底层面板添加边框*/
        Border border = BorderFactory.createBevelBorder(1, Color.DARK_GRAY, Color.WHITE);
        TitledBorder border1 = BorderFactory.createTitledBorder(border, "客户端请求界面", 2, 2, new Font("华文彩云", Font.BOLD, 15), Color.RED);
        jPanel.setBorder(border1);

        /*处理客户端列表*/
        //1.列表基本设置, 添加滚动列表，设置元素字体，颜色和选中颜色，添加边框等
        listModel = new DefaultListModel<>();
        list = new JList<>(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); //在设置选择模式时，一定要保证list有可选项，不让会出现异常
        JScrollPane jScrollPane1 = new JScrollPane(list);

        list.setForeground(Color.BLACK);
        list.setFont(new Font("隶书", Font.PLAIN, 15));
        list.setSelectionBackground(Color.WHITE);
        list.setSelectionForeground(Color.RED);
        jPanel.add(jScrollPane1);

        /*border = BorderFactory.createBevelBorder(2, Color.PINK, Color.PINK);
        border1 = BorderFactory.createTitledBorder(border, "客户端连接数： " + listModel.size() + "  ", 5, 5, new Font("华文新魏", Font.BOLD + Font.ITALIC, 13), Color.BLUE);
        border1 = BorderFactory.createTitledBorder(border1, listModel.size() + "  ", 5, 5, new Font("华文新魏", Font.BOLD + Font.ITALIC, 15), Color.RED);
        jScrollPane1.setBorder(border1);
        *//*添加元素例子*//*
        listModel.addElement("姜剑锋"); //例子
        listModel.addElement("小狼");
        listModel.addElement("小美");
        border1.setTitle(listModel.size() + "  "); //只要数目增加，则改数目*/

        /*操作客户端的按钮*/
        JPanel buttonPanel1 = new JPanel();
        jPanel.add(buttonPanel1, BorderLayout.SOUTH);
        buttonPanel1.setBackground(Color.pink);

        jButton1 = new JButton("连接");
        jButton2 = new JButton("请求");
        jButton3 = new JButton("删除");
        //设置字体
        Font buttonFont = new Font("隶书", Font.BOLD, 12);
        jButton1.setFont(buttonFont);
        jButton2.setFont(buttonFont);
        jButton3.setFont(buttonFont);


        buttonPanel1.add(jButton1);
        buttonPanel1.add(jButton2);
        buttonPanel1.add(jButton3);


    }

    public JPanel getJPanel() {
        return jPanel;
    }

    protected JList<String> getList() {
        return list;
    }

    protected JButton getJButton1() {
        return jButton1;
    }

    protected JButton getJButton2() {
        return jButton2;
    }

    protected JButton getJButton3() {
        return jButton3;
    }

    protected DefaultListModel<String> getListModel() {
        return listModel;
    }
}
