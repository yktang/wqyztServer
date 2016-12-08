package com.weiwei.common.constants;

/**
 * 
 * @ClassName: SessionKeyConstants
 * @Description: reids中 会话 hash 结构
 * @author zlp
 * @date 2016年11月2日 下午3:11:57
 *
 */
public class SessionKeyConstants {
	/**
	 * 用户登录 sessionkey 保存
	 */
	public static final String USER_SESSION_KEY = "user:sessionKey:";

	/**
	 * 认证 key
	 */
	public static final String AUTH_KEY = "Auth";

	/**
	 * 用户号码
	 */
	public static final String USER_PHONE_KEY = "USER_PHONE";

	/**
	 * 用户ID
	 */
	public static final String USER_ID_KEY = "USER_ID";

	/**
	 * 用户登录时间
	 */
	public static final String USER_LOGIN_TIME = "LOGIN_TIME";

	/**
	 * 用户最后一次操作时间
	 */
	public static final String USER_LAST_TIME = "LAST_TIME";

	/**
	 * 会话存活时间 24小时 86400秒 改为 10天
	 */
	public static final int SESSION_ACTIVE_SECONDS = 864000;
}
