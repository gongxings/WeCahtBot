package com.danggui.wechat.ui;

import com.danggui.wechat.Wechat;
import com.danggui.wechat.api.MessageTools;
import com.danggui.wechat.api.WechatTools;
import com.danggui.wechat.core.Core;
import com.danggui.wechat.face.MyMessagesHandle;
import com.danggui.wechat.ui.tree.CheckBoxNodeData;
import com.danggui.wechat.ui.tree.CheckBoxNodeEditor;
import com.danggui.wechat.ui.tree.CheckBoxNodeRenderer;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

public class WeBotUI extends JFrame {

    private JPanel mainPanel;
    private JTree contractList;
    private JTextArea textArea;
    private JButton sendBtn;
    private JButton picChooseBtn;
    private JScrollPane treeSPanel;
    private JScrollPane textAreaPanel;
    private JPanel btnPanel;
    private JList picList;

    private static Core core = Core.getInstance();
    private static Logger LOG = LoggerFactory.getLogger(WeBotUI.class);

    private WeBotUI(){
        this.setContentPane(this.getMainPanel());
        setLocationRelativeTo(null);
        setTitle("微信消息群发助手");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(800,600);
    }

    public JPanel getMainPanel() {
        if(mainPanel==null){
            mainPanel = new JPanel();
            mainPanel.setLayout(new GridLayoutManager(4, 4,
                    new Insets(10, 10, 10, 10),
                    10, 10, true,
                    false));
            //树形panel
            mainPanel.add(this.getTreeSPanel(), new GridConstraints(0, 0, 4, 1,
                            GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH,
                            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW,
                            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW,
                            null, new Dimension(140, 480), null, 0, false));

            //文本panel
            mainPanel.add(this.getTextAreaPanel(),
                    new GridConstraints(0, 1, 1, 3,
                            GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH,
                            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW,
                            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW,
                            null, null, null, 0, false));
            //按钮panel
            mainPanel.add(this.getBtnPanel(), new GridConstraints(3, 3, 1, 1,
                    GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    null, null, null, 0, false));

            mainPanel.add(this.getPicList(), new GridConstraints(1, 1, 1, 3,
                    GridConstraints.ANCHOR_CENTER,
                    GridConstraints.FILL_BOTH,
                    GridConstraints.SIZEPOLICY_CAN_GROW,
                    GridConstraints.SIZEPOLICY_WANT_GROW,
                    null,
                    new Dimension(150, 50),
                    null, 0, false));

        }
        return mainPanel;
    }

    public void setMainPanel(JPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    public JTree getContractList() {
        if(contractList==null){
            contractList = new JTree();
            contractList.setFont(new Font("宋体",Font.PLAIN,20));
             DefaultMutableTreeNode root = new DefaultMutableTreeNode("通讯录");
             DefaultMutableTreeNode accessibility = new DefaultMutableTreeNode("联系人");
            java.util.List<String> contactNickNameList = WechatTools.getContactNickNameList();

            for(String name:contactNickNameList){
                add(accessibility, name, false);
            }
            root.add(accessibility);
            DefaultMutableTreeNode browsing =new DefaultMutableTreeNode("微信群");
            java.util.List<String> groupNickNameList = WechatTools.getGroupNickNameList();
            //String reg = "[^\u4e00-\u9fa5]"; //替换英文
            for(String name:groupNickNameList){
                add(browsing, name, false);
            }
            root.add(browsing);
            final DefaultTreeModel treeModel = new DefaultTreeModel(root);
            contractList.setModel(treeModel);

            final CheckBoxNodeRenderer renderer = new CheckBoxNodeRenderer();

            contractList.setCellRenderer(renderer);

            final CheckBoxNodeEditor editor = new CheckBoxNodeEditor(contractList);
            contractList.setCellEditor(editor);
            contractList.setEditable(true);

            // listen for changes in the selection
            contractList.addTreeSelectionListener(new TreeSelectionListener() {

                @Override
                public void valueChanged(final TreeSelectionEvent e) {
                    System.out.println(System.currentTimeMillis() + ": selection changed");
                }
            });

            // listen for changes in the model (including check box toggles)
            treeModel.addTreeModelListener(new TreeModelListener() {

                @Override
                public void treeNodesChanged(final TreeModelEvent e) {
                    //System.out.println(System.currentTimeMillis() + ": nodes changed");
                }

                @Override
                public void treeNodesInserted(final TreeModelEvent e) {
                    //System.out.println(System.currentTimeMillis() + ": nodes inserted");
                }

                @Override
                public void treeNodesRemoved(final TreeModelEvent e) {
                    //System.out.println(System.currentTimeMillis() + ": nodes removed");
                }

                @Override
                public void treeStructureChanged(final TreeModelEvent e) {
                    //System.out.println(System.currentTimeMillis() + ": structure changed");
                }
            });


        }
        return contractList;
    }

    public void setContractList(JTree contractList) {
        this.contractList = contractList;
    }

    public JTextArea getTextArea() {
        if(textArea==null){
            textArea = new JTextArea();
            textArea.setText("输入文字");
            textArea.setFont(new Font("宋体",Font.PLAIN,20));
        }
        return textArea;
    }

    public void setTextArea(JTextArea textArea) {
        this.textArea = textArea;
    }

    public JButton getSendBtn() {
        if(sendBtn==null){
            sendBtn = new JButton("发送");
        }
        sendBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               String textMsg = getTextArea().getText();
                ArrayList<String> picList = new ArrayList<>();
                int listSize = getPicList().getModel().getSize();
                for(int index=0;index<listSize;index++){
                    picList.add(getPicList().getModel().getElementAt(index).toString());
                }
                DefaultTreeModel model = (DefaultTreeModel)getContractList().getModel();
                //picList
               DefaultMutableTreeNode rootNode = (DefaultMutableTreeNode) model.getRoot();
               int rootChilds = rootNode.getChildCount();
               for(int i=0;i<rootChilds;i++){
                   DefaultMutableTreeNode childerNode = (DefaultMutableTreeNode) rootNode.getChildAt(i);
                   int childCon = childerNode.getChildCount();
                   for(int j=0;j<childCon;j++){
                       DefaultMutableTreeNode conNode = (DefaultMutableTreeNode) childerNode.getChildAt(j);
                       CheckBoxNodeData data = (CheckBoxNodeData) conNode.getUserObject();
                        //如果被选中，发送消息
                       if(data.isChecked()){
                           String nickName = data.getText();
                           String id = core.getNickNameIdMap().get(nickName);
                           //发送文本消息

                           System.out.println(id);
                           MessageTools.sendMsgById(textMsg,id);

                           for (Object picPath:picList){
                               MessageTools.sendPicMsgByUserId(id,picPath.toString());
                           }
                           LOG.info("发送给"+nickName);
                           LOG.info("NickName:"+nickName+"/id:"+id);
                       }

                   }
               }
            }
        });
        return sendBtn;
    }

    public void setSendBtn(JButton sendBtn) {
        this.sendBtn = sendBtn;
    }

    public JScrollPane getTreeSPanel() {
        if(treeSPanel==null){
            treeSPanel = new JScrollPane();
            treeSPanel.setViewportView(this.getContractList());
        }
        return treeSPanel;
    }

    public void setTreeSPanel(JScrollPane treeSPanel) {
        this.treeSPanel = treeSPanel;
    }

    public JScrollPane getTextAreaPanel() {
        if (textAreaPanel ==null){
            textAreaPanel = new JScrollPane();
            textAreaPanel.setViewportView(this.getTextArea());
        }
        return textAreaPanel;
    }

    public void setTextAreaPanel(JScrollPane textAreaPanel) {
        this.textAreaPanel = textAreaPanel;
    }

    public JPanel getBtnPanel() {
        if(btnPanel==null){
            btnPanel = new JPanel();

            btnPanel.add(this.getPicChooseBtn(),
                    new GridConstraints(0, 0, 1, 1,
                            GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
                            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                            GridConstraints.SIZEPOLICY_FIXED, null, null, null,
                            0, false));
            btnPanel.add(this.getSendBtn(),
                    new GridConstraints(0, 0, 1, 2,
                            GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
                            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                            GridConstraints.SIZEPOLICY_FIXED, null, null, null,
                            0, false));
        }
        return btnPanel;
    }

    public void setBtnPanel(JPanel btnPanel) {
        this.btnPanel = btnPanel;
    }

    public JList getPicList() {
        if(picList==null){
            picList = new JList();
            DefaultListModel defaultListModel = new DefaultListModel();
            picList.setEnabled(true);
            picList.setFont(new Font("宋体",Font.PLAIN,20));

            picList.setModel(defaultListModel);
        }
        return picList;
    }

    public void setPicList(JList picList) {
        this.picList = picList;
    }

    public JButton getPicChooseBtn() {
        if(picChooseBtn==null){
            picChooseBtn = new JButton("添加图片");
        }
        picChooseBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser jfc = new JFileChooser();
                jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
                jfc.showDialog(new JLabel(), "选择图片");
                File file = jfc.getSelectedFile();
                System.out.println(file.getName());
                System.out.println(file.getAbsolutePath());
                DefaultListModel defaultListModel = (DefaultListModel)picList.getModel();
                if(file.getName().endsWith("jpg")
                        || file.getName().endsWith("png")
                        ||file.getName().endsWith("gif")){
                    defaultListModel.addElement(file.getAbsolutePath().trim());
                }
                /*if (file.isDirectory()) {
                    System.out.println("文件夹:" + file.getAbsolutePath());
                } else if (file.isFile()) {
                    System.out.println("文件:" + file.getAbsolutePath());
                }
                System.out.println(jfc.getSelectedFile().getName());*/
            }
        });
        return picChooseBtn;
    }

    public void setPicChooseBtn(JButton picChooseBtn) {
        this.picChooseBtn = picChooseBtn;
    }

    private static DefaultMutableTreeNode add(
            final DefaultMutableTreeNode parent, final String text,
            final boolean checked){
         CheckBoxNodeData data = new CheckBoxNodeData(text, checked);
         DefaultMutableTreeNode node = new DefaultMutableTreeNode(data);
        parent.add(node);
        return node;
    }

    public static void main(String[] args) {

        MyMessagesHandle msgHandler = new MyMessagesHandle(); // 实现IMsgHandlerFace接口的类
        //启动微信
        Wechat wechat = new Wechat(msgHandler, System.getProperty("user.dir")+"/images"); // 【注入】
        wechat.start();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                WeBotUI weBotUI = new WeBotUI();
                weBotUI.setVisible(true);
            }
        });

    }
}
