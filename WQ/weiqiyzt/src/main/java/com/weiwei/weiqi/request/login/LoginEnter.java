package com.weiwei.weiqi.request.login;

import java.io.Serializable;

import com.weiwei.weiqi.request.base.BaseEnter;

public class LoginEnter extends BaseEnter{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2360246980123969691L;

	private String password;
	private String loginMobile;
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getLoginMobile() {
		return loginMobile;
	}
	public void setLoginMobile(String loginMobile) {
		this.loginMobile = loginMobile;
	}
	
}
