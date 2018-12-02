package com.danggui.wechat.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.util.Hashtable;

public class QrCodeShowFrame extends JFrame{
    private JLabel jLabel;
    public static void main(String[] args){
        QrCodeShowFrame qrCodeShowFrame = new QrCodeShowFrame();
        qrCodeShowFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                qrCodeShowFrame.dispose();
            }
        });

    }

    public QrCodeShowFrame(){
        String [] words = {"Java", "Python", "Golang"};
        jLabel = new JLabel(new ImageIcon("src/main/resources/images/QR.jpg"));
        this.add(jLabel);
        //设置JFrame属性
        this.setTitle("显示登录二维码");

        this.setSize(600, 600);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }


}
