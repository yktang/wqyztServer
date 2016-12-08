package com.weiwei.weiqi.request.announcement;

import javax.validation.constraints.NotNull;

import com.weiwei.weiqi.request.base.BaseEnter;

public class CommentListEnter extends BaseEnter{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2629459058951696011L;
	@NotNull
	private Integer announceId;

	public Integer getAnnounceId() {
		return announceId;
	}

	public void setAnnounceId(Integer announceId) {
		this.announceId = announceId;
	}
	
	
}
