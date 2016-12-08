package com.weiwei.weiqi.response.annoucement;

import java.io.Serializable;

public class ContentResult implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3421261770114973770L;

	private Integer aid;
	private String fillBody;
	public Integer getAid() {
		return aid;
	}
	public void setAid(Integer aid) {
		this.aid = aid;
	}
	public String getFillBody() {
		return fillBody;
	}
	public void setFillBody(String result) {
		if("".equalsIgnoreCase(result)){
			return;
		}
		String startKeyword = "{dede:field name='body'}";
		String endKeyword = "{/dede:field}";
		result = result.substring(result.indexOf(startKeyword));
		this.fillBody = result.substring(startKeyword.length(), result.indexOf(endKeyword));
	}
	
	
	
	
}
