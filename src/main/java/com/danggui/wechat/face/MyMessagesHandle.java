package com.danggui.wechat.face;

import com.danggui.wechat.api.WechatTools;
import com.danggui.wechat.beans.BaseMsg;
import com.danggui.wechat.core.Core;
import org.apache.log4j.Logger;

public class MyMessagesHandle implements IMsgHandlerFace {
    Logger LOG = Logger.getLogger(MyMessagesHandle.class);
    String messages ;
    @Override
    public String textMsgHandle(BaseMsg msg) {
        if (!msg.isGroupMsg()) { // 群消息不处理
            // String userId = msg.getString("FromUserName");
            // MessageTools.sendFileMsgByUserId(userId, docFilePath); // 发送文件
            // MessageTools.sendPicMsgByUserId(userId, docFilePath);
            String text = msg.getText(); // 发送文本消息，也可调用MessageTools.sendFileMsgByUserId(userId,text);
            LOG.info(text);
            if (text.equals("111")) {
                WechatTools.logout();
            }
            if (text.equals("222")) {
                WechatTools.remarkNameByNickName("yaphone", "Hello");
            }
            if (text.equals("333")) { // 测试群列表
                System.out.print(WechatTools.getGroupNickNameList());
                System.out.print(WechatTools.getGroupIdList());
                System.out.print(Core.getInstance().getGroupMemeberMap());
            }
            return text;
        }
        return null;
    }

    @Override
    public String picMsgHandle(BaseMsg msg) {
        return null;
    }

    @Override
    public String voiceMsgHandle(BaseMsg msg) {
        return null;
    }

    @Override
    public String viedoMsgHandle(BaseMsg msg) {
        return null;
    }

    @Override
    public String nameCardMsgHandle(BaseMsg msg) {
        return null;
    }

    @Override
    public void sysMsgHandle(BaseMsg msg) {

    }

    @Override
    public String verifyAddFriendMsgHandle(BaseMsg msg) {
        return null;
    }

    @Override
    public String mediaMsgHandle(BaseMsg msg) {
        return null;
    }

    public String getMessages() {
        return messages;
    }

    public void setMessages(String messages) {
        this.messages = messages;
    }
}
