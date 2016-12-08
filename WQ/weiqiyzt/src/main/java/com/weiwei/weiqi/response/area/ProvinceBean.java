package com.weiwei.weiqi.response.area;

import java.io.Serializable;

public class ProvinceBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8457810826382248354L;
	private int provinceId;
	private String provinceName;
	public int getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(int provinceId) {
		this.provinceId = provinceId;
	}
	public String getProvinceName() {
		return provinceName;
	}
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	
	
}
