package com.weiwei.weiqi.request.base;

import java.io.Serializable;


public class MyPageRequest implements Serializable {
	
	private static final long serialVersionUID = 2742662908567131753L;
	private int page;
	private int size;

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

//	private PageRequest PageRequest;
//
//	public PageRequest getPageRequest() {
//		return PageRequest;
//	}
//
//	public void setPageRequest(PageRequest pageRequest) {
//		PageRequest = pageRequest;
//	}
	
	
	
	
}
