package com.weiwei.weiqi.request.register;

import java.io.Serializable;

public class CompleteRegSubmitEnter implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1378235524742344528L;
	private String password;
	private String userName;
	private String mobilePhonenumber;
	private String phoneNumber;
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getMobilePhonenumber() {
		return mobilePhonenumber;
	}
	public void setMobilePhonenumber(String mobilePhonenumber) {
		this.mobilePhonenumber = mobilePhonenumber;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	
	
}
