package com.weiwei.common.constants.enums;

public enum ResultCodeEnum {
	/**
	 * 成功 
	 */
	RESULT_SUCCESS(1,"成功"),
	
	/**
	 * 服务器异常 操作操时
	 */
	RESULT_SYSERROR(9999,"服务忙碌，请稍后重试"),
	
	/**
	 * 缺少必要参数
	 */
	RESULT_ERROR_NEED_PARM(9998,"缺少必要参数"),
	
	/**
	 * 参数有误
	 */
	RESULT_ERROR_PARM(9997,"参数有误"),
	
	/**
	 * 验证码 发送时间间隔
	 */
	RESULT_ERROR_SMS_INTEVAL(110,"短信验证码已发送"),
	/**
	 * 用户密码错误
	 */
	RESULT_ERROR_PASSWORD(111,"用户密码错误"),
	
	/**
	 * 验证码错误
	 */
	RESULT_ERROR_SMS_CAPTCHA(112,"手机验证码错误"),
	
	/**
	 * 验证码过期
	 */
	RESULT_ERROR_SMS_EXPIRED(113,"验证码已过期，请重新获取"),
	/**
	 * 验证码 错误次数超阀值
	 */
	RESULT_ERROR_SMS_TIMES(114,"验证码错误次数过多，请稍后再试"),
	
	/**
	 * 手机号未注册
	 */
	RESULT_ERROR_REG_NO(115,"手机号未注册"),
	
	/**
	 * 手机号已注册
	 */
	RESULT_ERROR_REG_DUPLICATE(116,"手机号已注册"),
	
	/**
	 * 会话超时
	 */
	RESULT_ERROR_SESSION_TIME_OUT(119,"会话超时-请重新登陆"),
	
	/**
	 * 被踢下线
	 */
	RESULT_ERROR_SESSION_KICK_OUT(120,"您的账号已在其他终端登录"),
	
	/**
	 * 用户名或密码错误
	 */
	RESULT_ERROR_ACCOUNT(121,"用户名或密码错误");
	
	
	private int code;
	private String desc;
	
	private ResultCodeEnum(int code,String desc) {
        this.code = code;
        this.desc = desc;

    }
	
	public int getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}

	public String toString(){
		return code+":"+desc;
	}
}
