package com.weiwei.weiqi.response;

import java.util.ArrayList;
import java.util.List;

public class GeneralServiceResponse<T> {
	protected List<T> responseObjectList;
	
	public GeneralServiceResponse(){
		super();
		responseObjectList = new ArrayList<T>();
	}
	
	public void setResponseObjectList(ArrayList<T> list){
		responseObjectList = list;
	}
	public List<T> getResponseObjectList(){
		return responseObjectList;
	}

	@Override
	public String toString() {
		return "GeneralServiceResponse [responseObjectList=" + responseObjectList + "]";
	}
	
}
