package com.weiwei.weiqi.service.customer.api;

import com.weiwei.common.exception.BusinessException;
import com.weiwei.weiqi.request.base.BaseEnter;
import com.weiwei.weiqi.request.register.CompleteRegSubmitEnter;
import com.weiwei.weiqi.request.register.PreRegEnter;
import com.weiwei.weiqi.response.base.GeneralResult;

public interface RegisterService {

	/**
	 * 超时时间10分钟
	 */
	public static final int EXPIRED_SECONDS = 600;

	/**
	 * 计数单位 1
	 */
	public static final byte INCREMENT_VALUE = 1;

	/**
	 * 注册验证码错误次数 超过后不能在发送验证码
	 */
	public static final int REGSMSC_TIMES = 100;

	/**
	 * 注册验证码超阀值锁定时间 超阀值锁定6小时
	 */
	public static final int REGSMSC_LOCK_HOURS = 6;

	/**
	 * 
	 * @Title: 获取短信验证码
	 * @Description: 一个用户每日获取特定的次数
	 * @param @return
	 * @return GeneralResult
	 * @throws
	 */
	GeneralResult getRegSMSCaptcha(BaseEnter enter) throws BusinessException;

	GeneralResult validationRegSmsCaptcha(PreRegEnter preRegEnter) throws BusinessException;

	GeneralResult completeSubmit(CompleteRegSubmitEnter completeRegSubmitEnter,
			BaseEnter baseEnter) throws BusinessException;

}
