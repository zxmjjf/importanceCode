package com.book2.B4_InteRnet.serverAndclient.server;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class B_Module3 {
    private JPanel jPanel; //客户端请求信息面板

    B_Module3(){
        /*客户端详细信息面板. 基本设置*/
        jPanel = new JPanel();
        jPanel.setBackground(Color.PINK);
        //添加边框
        Border border = BorderFactory.createBevelBorder(1, Color.DARK_GRAY, Color.WHITE);
        Border border1 = BorderFactory.createTitledBorder(border, "客户端详细信息", 2, 2, new Font("华文彩云", Font.BOLD, 15), Color.RED);
        jPanel.setBorder(border1);

    }

    protected JPanel getJPanel() {
        return this.jPanel;
    }
}
