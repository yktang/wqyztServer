package com.weiwei.weiqi.response.annoucement;

import java.io.Serializable;

public class HtmlListResult implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8466499854433165856L;
	
	private Integer aid;
	private String title;
	private String publishTime;
	public Integer getAid() {
		return aid;
	}
	public void setAid(Integer aid) {
		this.aid = aid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(String url) {
		try {
			int index = url.indexOf("-");
			this.publishTime = url.substring(index-4,index+6);
		} catch (Exception e) {
			System.out.println(url);
		}
		
	}
	
}
