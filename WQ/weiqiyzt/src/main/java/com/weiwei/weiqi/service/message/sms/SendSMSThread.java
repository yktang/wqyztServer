package com.weiwei.weiqi.service.message.sms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ruanwei.interfacej.SmsClientSend;
import com.weiwei.common.constants.Constants;

public class SendSMSThread implements Runnable {
	private final Logger logger = LoggerFactory.getLogger(SendSMSThread.class);
	private String smsMsg;
	private String phoneNumber;

	public SendSMSThread(String phoneNumber, String smsMsg) {
		this.phoneNumber = phoneNumber;
		this.smsMsg = smsMsg;
	}

	@Override
	public void run() {
		try {
//			SmsClientSend.sendSms(Constants.SMS_SERVICE_URL, Constants.SMS_SERVICE_USERID, Constants.SMS_SERVICE_ACCOUNT, Constants.SMS_SERVICE_PASSWORD, phoneNumber, Constants.SMS_SERVICE_CONTENT
//					+ smsMsg + Constants.SMS_SERVICE_SIGNATURE);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}