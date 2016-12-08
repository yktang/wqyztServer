package com.weiwei.weiqi.request.base;

import java.io.Serializable;

import com.weiwei.common.util.StringUtils;

public class BaseEnter implements Serializable{
	
	private static final long serialVersionUID = 7669644580101979994L;
	
	/**
	 * 软件版本号 
	 */
	private String appVersion;
	
	/**
	 * 客户端类型
	 * H5:4
	 * PC：3
	 * app android:2
	 * app ios:1
	 */
	private int clientType;
	
	/**
	 * 手机sim卡号
	 */
	private String imsi ;
	
	/**
	 * 手机终端序列号
	 */
	private String imei ;
	
	private String mac;
	/**
	 * 时间戳 毫秒数
	 */
	private long timestamp;
	

	/**
	 * Session KEY
	 */
	private String sessionKey;
	
	/**
	 * 客户端IP
	 */
	private String enterIp;
	
	/**
	 * 用户手机号
	 */
	private String mobileNum;

	/**
	 * 用户ID
	 */
	private int userId;
	
	private boolean userAuth;
	
	private int cert;
	
	

	/**
	 *  PC：3
	 * app android:2
	 * app ios:1
	 * 
	 * @Description: 
	 *
	 * @ClassName: ClIENTTYPE 
	 *
	 * @author zlp
	 *
	 * @date 2016年6月13日下午6:20:43
	 *
	 * @version 1.0
	 *
	 */
	public interface ClIENTTYPE{
		public static final int IOS = 1;
		public static final int ANDROID = 2;
		public static final int PC = 3;
		public static final int WECHAT = 4;
	}
	
	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getMobileNum() {
		return mobileNum;
	}

	public void setMobileNum(String mobileNum) {
		this.mobileNum = mobileNum;
	}

	public String getEnterIp() {
		return enterIp;
	}

	public void setEnterIp(String enterIp) {
		this.enterIp = enterIp;
	}

	

	public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

	public int getClientType() {
		return clientType;
	}

	public void setClientType(int clientType) {
		this.clientType = clientType;
	}

	public String getImsi() {
		return imsi;
	}

	public void setImsi(String imsi) {
		this.imsi = imsi;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	private String userKey = "";
	private final static String KEY = "sessionKey:"; 
	public String getSessionKey() {
		if(StringUtils.isNotBlank(sessionKey)){
			if(sessionKey.startsWith(KEY)){
				return sessionKey;
			}
			return KEY+sessionKey;
		}else{
			return KEY+userKey+appVersion+"_"+clientType+"_"+imsi+"_"+imei+"_"+mac;
		}
	}
	
	public String getOriginalSessionKey(){
		return sessionKey;
	}

	public void setSessionKey(String sessionKey) {
		this.sessionKey = sessionKey;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
		if(userId != 0){
			userKey = userId+"_";
		}
	}

	public boolean isUserAuth() {
		return userAuth;
	}

	public void setUserAuth(boolean userAuth) {
		this.userAuth = userAuth;
	}

	public int getCert() {
		return cert;
	}

	public void setCert(int cert) {
		this.cert = cert;
	}
}
