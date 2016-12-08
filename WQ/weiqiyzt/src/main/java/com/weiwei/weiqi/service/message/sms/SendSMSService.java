package com.weiwei.weiqi.service.message.sms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

@Component
public class SendSMSService {
	
//	@Autowired
//	private HttpClient httpClient;
	
	@Autowired
	private ThreadPoolTaskExecutor sendSMSThreadPool;

	public void sendRegSMS(String mobileNum, String value) {
		sendSMS(mobileNum, value);
	}
	
	
	private void sendSMS(String newMobileNum,String msg){
		sendSMSThreadPool.execute(new SendSMSThread(newMobileNum,msg), 60000);
	}
}
