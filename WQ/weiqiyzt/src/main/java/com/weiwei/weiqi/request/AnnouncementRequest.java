package com.weiwei.weiqi.request;

public class AnnouncementRequest {
	public int startIndex;
	public int endIndex;
	public String url;
	public int getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}
	public int getEndIndex() {
		return endIndex;
	}
	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Override
	public String toString() {
		return "AnnouncementRequest [startIndex=" + startIndex + ", endIndex=" + endIndex + ", url=" + url + "]";
	}
	
	
}
