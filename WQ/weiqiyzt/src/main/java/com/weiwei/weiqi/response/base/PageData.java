package com.weiwei.weiqi.response.base;

import java.io.Serializable;
import java.util.List;

public class  PageData<T> implements Serializable  {
	private static final long serialVersionUID = 7244516020793855784L;
	// 总页数
	private Integer totalPage=0;
	// 总条数
	private Long totalNum=0l;
	//当前页数
	private int currentPage;
	//结果集
	private List<T> list;
	
	public PageData(Integer totalPage,Long totalNum,int currentPage,List<T> list){
		this.totalPage = totalPage;
		this.totalNum = totalNum;
		this.list = list;
		this.currentPage = currentPage;
	}
	public PageData(Integer totalPage,Long totalNum,List<T> list){
		this.totalPage = totalPage;
		this.totalNum = totalNum;
		this.list = list;
	}
	public Integer getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	public Long getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(Long totalNum) {
		this.totalNum = totalNum;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
}
