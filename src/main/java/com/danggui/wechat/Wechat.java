package com.danggui.wechat;

import com.danggui.wechat.controller.LoginController;
import com.danggui.wechat.core.MsgCenter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.danggui.wechat.face.IMsgHandlerFace;
import sun.rmi.runtime.Log;

import java.io.File;

public class Wechat {
	private static final Logger LOG = LoggerFactory.getLogger(Wechat.class);
	private IMsgHandlerFace msgHandler;

	public Wechat(IMsgHandlerFace msgHandler, String qrPath) {
		System.setProperty("jsse.enableSNIExtension", "false"); // 防止SSL错误
		this.msgHandler = msgHandler;

		// 登陆
		LoginController login = new LoginController();
		File qrFile = new File(qrPath);
		if(!qrFile.exists()){
			qrFile.mkdir();
		}
		LOG.info("二维码路径:"+qrFile.getAbsolutePath());
		login.login(qrPath);
	}

	public void start() {
		LOG.info("+++++++++++++++++++开始消息处理+++++++++++++++++++++");
		new Thread(new Runnable() {
			@Override
			public void run() {
				MsgCenter.handleMsg(msgHandler);
			}
		}).start();
	}

}
