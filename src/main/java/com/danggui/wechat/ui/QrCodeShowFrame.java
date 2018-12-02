package com.danggui.wechat.ui;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.util.Hashtable;

public class QrCodeShowFrame extends JFrame{
    private JLabel jLabel;
    private static final Logger LOG = LoggerFactory.getLogger(QrCodeShowFrame.class);
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
        jLabel = new JLabel(new ImageIcon(System.getProperty("user.dir")+"\\images\\QR.jpg"));
        LOG.info("图片路径"+System.getProperty("user.dir")+"\\images");
        this.add(jLabel);
        //设置JFrame属性
        this.setTitle("显示登录二维码");

        this.setSize(600, 600);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }


}
