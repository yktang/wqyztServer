package com.weiwei.weiqi.request.announcement;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

public class AddCommentEnter implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4971449565900485962L;
	
	public Integer sessionId;
	public Integer announceId;
	@NotNull
	private String commentContent;
	
	public String getCommentContent() {
		return commentContent;
	}
	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}
	public Integer getSessionId() {
		return sessionId;
	}
	public void setSessionId(Integer sessionId) {
		this.sessionId = sessionId;
	}
	public Integer getAnnounceId() {
		return announceId;
	}
	public void setAnnounceId(Integer announceId) {
		this.announceId = announceId;
	}
	

	
	
}
