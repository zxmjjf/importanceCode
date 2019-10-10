package com.book2.B4_InteRnet;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 *
 */
public class B04_ServerAndClient {

    /**
     * <decribe>程序入口，图形界面主窗口</decribe>
     * @param args
     * @throws ClassNotFoundException
     * @throws UnsupportedLookAndFeelException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        JFrame jFrame = new JFrame("Server AND Client");
        //设置组件风格
        UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        SwingUtilities.updateComponentTreeUI(jFrame);

        //将容器转为面板
        JPanel jPanel = new JPanel();
        jFrame.getContentPane().add(jPanel);
        jPanel.setLayout(new GridLayout(1, 2, 10, 10));
        jPanel.setBackground(Color.WHITE);

        //add panel
        jPanel.add(setServer());
        jPanel.add(setClient());

        //基本设置
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setBounds(300, 100, 600, 350);
        jFrame.setVisible(true);
    }

    /**
     * <describe>设置服务端界面</describe>
     * @return <type>JPanel</type>
     */
    private static JPanel setServer() {
        //server panel
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
        JTextField jTextField = new JTextField(20);
        //3.连接按钮
        JButton linkButton = new JButton("link / 链接");
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
        JTextArea jTextArea = new JTextArea();
        JScrollPane jScrollPane = new JScrollPane(jTextArea);
        jScrollPane.setBorder(border1);

        //3.组装
        jPanelS.add(jPanel1, BorderLayout.NORTH);
        jPanelS.add(jScrollPane);
        //3.按钮
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.PINK);

        JButton jButton = new JButton("Request / 请求");
        jButton.setForeground(Color.RED);
        buttonPanel.add(jButton);
        jPanelS.add(buttonPanel, BorderLayout.SOUTH);

        return jPanelS;
    }


    /**
     * <describe>设置客户端界面</describe>
     * @return <type>JPanel</type>
     */
    private  static JPanel setClient(){
        //Client panel
        JPanel jPanelC = new JPanel();
        jPanelC.setLayout(new BorderLayout(5, 5));
        jPanelC.setBackground(Color.CYAN);

        //服务端请求文本框
        //1.边框
        Font borderFont = new Font("华文琥珀", Font.BOLD, 15);
        Border border = BorderFactory.createEtchedBorder(0);
        TitledBorder border1 = BorderFactory.createTitledBorder(border, "服务端");
        border1.setTitleColor(Color.BLUE);
        border1.setTitleFont(borderFont);
        //2.收到的请求文本
        JTextArea jTextArea1 = new JTextArea();
        JScrollPane jScrollPane1 = new JScrollPane(jTextArea1);
        jScrollPane1.setBorder(border1);
        //3.jScrollPane.add(jTextArea);
        jPanelC.add(jScrollPane1, BorderLayout.CENTER);
        //4.按钮
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.CYAN);

        JButton jButton = new JButton("Respond / 回应");
        jButton.setForeground(Color.RED);
        buttonPanel.add(jButton);
        jPanelC.add(buttonPanel, BorderLayout.SOUTH);

        return jPanelC;
    }
}
