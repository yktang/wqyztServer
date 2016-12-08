package com.weiwei.weiqi.service.customer.impl;

public abstract class AbstractCustomerService {

	protected static final String SMSCODE = "sms_code";

	/**
	 * 验证码 发送间隔时间
	 */
	protected static final int REGSMSC_INTEVAL = 60;

	private int index = 10;

	/**
	 * 获取短信序号
	 * 
	 * @return
	 */
	protected int getIndex() {
		if (index > 1000) {
			index = 10;
		}
		return index++;
	}

	/**
	 * @Description: 获取rediskey 描述符
	 */
	protected abstract String getRedisKey();

	/**
	 * 
	 * @Description: 验证码发送时间间隔
	 * @param mobile
	 * @param userId
	 * @return
	 */
	protected String getSMSCIRedisKey(String mobile) {
		return "user:" + getRedisKey() + ":SMSCINTEVAL:" + mobile;
	}

	/**
	 * 
	 * @Description: 验证码发送次数rediskey
	 * @param mobile
	 * @param userId
	 * @return
	 */
	protected String getSMSCTRedisKey(String mobile) {
		return "user:" + getRedisKey() + ":SMSCT:" + mobile;
	}

	/**
	 * 
	 * @Description: 短信验证码内容rediskey
	 * @param mobile
	 * @param userId
	 * @return
	 */
	protected String getSMSCRedisKey(String mobile, String sessionKey) {
		return "user:" + getRedisKey() + ":SMSC:" + mobile + "_" + sessionKey;
	}

}
