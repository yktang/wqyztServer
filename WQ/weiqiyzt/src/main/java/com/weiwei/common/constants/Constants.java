package com.weiwei.common.constants;

public class Constants {
	final public static String SERVICE_REQUEST = "service_request";
	final public static String SERVICE_RESPONSE = "service_response";
	final public static String DAOOBJECT = "data_object";
	final public static String EVENT_SUCCESS = "success";
	final public static String EVENT_FAIL = "fail";
	final public static String EVENT_EXISTED = "existed";

	final public static String HEADER_REG_USERNAME = "X-Register-Username";
	final public static String HEADER_REG_COMPANYNAME = "X-Register-CompanyName";

	private static final String SECURE_API_PATH = "/sweiwei";
	public static final String REGISTER_CODE_URL = SECURE_API_PATH + "/registerCode";

	final public static String EVENT_ANNOUNCE_LIST = "announce_list";
	final public static String EVENT_ANNOUNCE_BODY = "announce_body";
	final public static String GROUP_SEPARATOR = ";-;"; // 有时间的话，把这个改为0x1D吧，是char的，不是String,
														// CommenDAOImpl.java里面的语句也要相应改掉

	
	// sms
	final public static String SMS_SERVICE_URL = "http://xtx.telhk.cn:8080/sms.aspx?";
	final public static String SMS_SERVICE_USERID = "5272";
	final public static String SMS_SERVICE_ACCOUNT = "a10103";
	final public static String SMS_SERVICE_PASSWORD = "1235451";
	final public static String SMS_SERVICE_FPWD_CONTENT = "安吉开发区服务平台忘记密码服务，请再2分钟内完成验证，验证码：";
	final public static String SMS_SERVICE_CONTENT = "欢迎注册微企一站通中小企业服务平台，验证码：";
	final public static String SMS_SERVICE_SIGNATURE = "【微未信息】";

	/**
	 * 用户注册 redis key
	 */
	public static final String REG_REDIS_KEY = "UserReg";

	/**
	 * 会话 key
	 */
	public static final String SESSION_KEY = "sessionKey";
	
	/**
	 * 页码号
	 */
	public static final String PAGE_NUM = "page";
	
	/**
	 * 页大小
	 */
	public static final String PAGE_SIZE = "page_size";

}
