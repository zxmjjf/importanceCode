package com.junkCode;

import javax.swing.*;
import java.awt.*;

public class TestJFrame {
    private static JFrame frame;
    private static JLabel jLabel;
    public static void main(String[] args) {
        frame = new JFrame("test");

        Font font = new Font("华文行楷", Font.BOLD, 20);
        jLabel = new JLabel("This is my Windows: 姜剑锋");
        jLabel.setFont(font);
        jLabel.setForeground(Color.RED);

        JPanel jPanel = new JPanel();
        jPanel.setBackground(Color.BLUE);
        frame.getContentPane().add(jPanel);

        jPanel.add(jLabel);


        frame.setVisible(true);
        frame.setBounds(200, 100, 400, 300);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
