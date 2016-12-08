package com.weiwei.weiqi.request.register;

import com.weiwei.weiqi.request.base.BaseEnter;

public class PreRegEnter extends BaseEnter {
	private static final long serialVersionUID = -4010018507966441178L;
	private String captchaCode;
	public String getCaptchaCode() {
		return captchaCode;
	}

	public void setCaptchaCode(String captchaCode) {
		this.captchaCode = captchaCode;
	}
}
