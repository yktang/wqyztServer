package com.weiwei.weiqi.response.annoucement;

public class CommentListResult {
	public int sessionId;
	public int id;
	public int announceId;
	public String comment;
	public String commentTime;
	public String customerName;
	public int numberOfLike;
	public int numberOfComment;
	public String[] commentList;
	public String[] customernameList;
	public int getSessionId() {
		return sessionId;
	}
	public void setSessionId(int sessionId) {
		this.sessionId = sessionId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAnnounceId() {
		return announceId;
	}
	public void setAnnounceId(int announceId) {
		this.announceId = announceId;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getCommentTime() {
		return commentTime;
	}
	public void setCommentTime(String commentTime) {
		this.commentTime = commentTime;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public int getNumberOfLike() {
		return numberOfLike;
	}
	public void setNumberOfLike(int numberOfLike) {
		this.numberOfLike = numberOfLike;
	}
	public int getNumberOfComment() {
		return numberOfComment;
	}
	public void setNumberOfComment(int numberOfComment) {
		this.numberOfComment = numberOfComment;
	}
	public String[] getCommentList() {
		return commentList;
	}
	public void setCommentList(String[] commentList) {
		this.commentList = commentList;
	}
	public String[] getCustomernameList() {
		return customernameList;
	}
	public void setCustomernameList(String[] customernameList) {
		this.customernameList = customernameList;
	}
	
	
	
}
