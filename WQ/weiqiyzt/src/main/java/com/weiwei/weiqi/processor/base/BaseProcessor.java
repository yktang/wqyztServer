package com.weiwei.weiqi.processor.base;

import java.util.Map;

public abstract class BaseProcessor {
	public String doProcess(Map<String, Object> scopes) {
		preProcess(scopes);
		String event = executeProcess(scopes);
		event = postProcess(scopes, event);
		return event;
	}
	protected void preProcess(Map<String, Object> scopes){}
	protected abstract String executeProcess(Map<String, Object> scopes);
	protected String postProcess(Map<String, Object> scopes, String event){
		return event;
	}
}
