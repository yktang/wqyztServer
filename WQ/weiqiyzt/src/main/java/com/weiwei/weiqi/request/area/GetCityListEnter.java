package com.weiwei.weiqi.request.area;

import com.weiwei.weiqi.request.base.BaseEnter;

public class GetCityListEnter extends BaseEnter {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4507651206112486855L;
	private int provinceId;

	public int getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(int provinceId) {
		this.provinceId = provinceId;
	}

}
