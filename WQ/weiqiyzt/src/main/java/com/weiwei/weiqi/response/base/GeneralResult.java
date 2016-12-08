package com.weiwei.weiqi.response.base;

import java.io.Serializable;

import com.weiwei.common.constants.enums.ResultCodeEnum;
import com.weiwei.common.exception.BusinessException;

/**
 * 通用返回
 * 
 * @author vip
 *
 */
public class GeneralResult implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5368778415387248971L;

	protected int resultCode;

	protected String resultMessage;

	protected String data = "";// 加入这个字段避免客户端解析出异常

	public GeneralResult() {

	}

	public GeneralResult(int resultCode, String resultMessage) {
		super();
		this.resultCode = resultCode;
		this.resultMessage = resultMessage;
	}

	public GeneralResult(ResultCodeEnum result){
		super();
		this.resultCode = result.getCode();
		this.resultMessage = result.getDesc();
	}
	
	public GeneralResult(ResultCodeEnum result, String data){
		super();
		this.resultCode = result.getCode();
		this.resultMessage = result.getDesc();
		this.data = data;
	}
	
	public GeneralResult(BusinessException bex){
		super();
		this.resultCode = bex.getErrorCode();
		this.resultMessage = bex.getMessage();
	}
	
	public int getResultCode() {
		return resultCode;
	}

	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}

	public String getResultMessage() {
		return resultMessage;
	}

	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}

	public Object getData() {
		return data;
	}

}
