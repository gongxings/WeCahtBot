package com.danggui.wechat.demo;

import com.danggui.wechat.Wechat;
import com.danggui.wechat.api.MessageTools;
import com.danggui.wechat.api.WechatTools;
import com.danggui.wechat.core.Core;
import com.danggui.wechat.face.MyMessagesHandle;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class MainFrame extends JFrame{
    static Logger LOG = Logger.getLogger(MainFrame.class);
    JScrollPane scrollPane;
    private JList<String> groupList;
    private JPanel listPanel;
   // private JPanel mainPanel;
    private JPanel newPanel;
    private static JTextArea textArea;
    private static JButton submitBtn;

    private static Core core = Core.getInstance();

    public  MainFrame(){
        this.setSize(800,600);
        this.setLocationRelativeTo(null);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                WechatTools.logout();
            }
        });
        setDefaultCloseOperation(EXIT_ON_CLOSE);
       // mainPanel = new JPanel();
        List list = core.getGroupNickNameList();
        String[] groups = new String[list.size()];
        list.toArray(groups);
        groupList = new JList<String>();
        groupList.setListData(groups);
        groupList.setVisibleRowCount(20);
        scrollPane = new JScrollPane();
        scrollPane.add(new Label("微信群"));
        scrollPane.setViewportView(groupList);
        listPanel = new JPanel();
        listPanel.add(scrollPane);

        submitBtn =  new JButton("发送");
        textArea = new JTextArea();
        textArea.setSize(600,400);
        textArea.setColumns(40);
        Font font = new Font("宋体",Font.PLAIN,20);
        textArea.setFont(font);
        newPanel = new JPanel();
        newPanel.add(textArea,BorderLayout.NORTH);
        newPanel.add(submitBtn,BorderLayout.SOUTH);
        this.add(listPanel, BorderLayout.WEST);
        this.add(newPanel, BorderLayout.CENTER);
       /* mainPanel.add(newPanel,BorderLayout.CENTER);
        this.add(mainPanel);*/
        this.setTitle("微信消息发送助手");
    }


    public static void main(String[] args) {

        MyMessagesHandle msgHandler = new MyMessagesHandle(); // 实现IMsgHandlerFace接口的类

        Wechat wechat = new Wechat(msgHandler, "src/main/resources/images"); // 【注入】
        wechat.start();
        MainFrame mainFrame = new MainFrame();

        submitBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                try {
                    String msg = textArea.getText();
                    msgHandler.setMessages(msg);
                    MessageTools.sendMsg(msg, "filehelper");
                    MessageTools.sendPicMsgByUserId("filehelper","G:\\商城资料\\公众号资料信息\\经营店公众号二维码.jpg");
                    //List<String> groupIdList =  core.getGroupIdList();
                    //for(String )
                }catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
        mainFrame.setVisible(true);

    }

}
