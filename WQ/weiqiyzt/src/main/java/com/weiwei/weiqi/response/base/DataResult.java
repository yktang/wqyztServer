package com.weiwei.weiqi.response.base;

import java.io.Serializable;

import com.weiwei.common.constants.enums.ResultCodeEnum;

public class DataResult<T> extends GeneralResult implements Serializable {

	private static final long serialVersionUID = 6380821301891204450L;
	protected T data;

	/**
	 * @param resultCode
	 * @param resultMessage
	 * @param data
	 */
	public DataResult(int resultCode, String resultMessage, T data) {
		super(resultCode, resultMessage);
		this.data = data;
	}

	/**
	 * @param resultCode
	 * @param resultMessage
	 * @param data
	 */
	public DataResult(ResultCodeEnum result, T data) {
		super(result);
		this.data = data;
	}
	
	public DataResult(){
		
	}
	
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}

	
}
