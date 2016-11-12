package com.weiwei.common;

public class Constants {
	final public static String SERVICE_REQUEST = "service_request";
	final public static String SERVICE_RESPONSE = "service_response";
	final public static String DAOOBJECT = "data_object";
	final public static String EVENT_SUCCESS = "success"; 
	final public static String EVENT_FAIL = "fail";
	final public static String EVENT_EXISTED = "existed";
	
	final public static String HEADER_REG_USERNAME = "X-Register-Username";
	final public static String HEADER_REG_COMPANYNAME = "X-Register-CompanyName";
	
	private static final String SECURE_API_PATH = "/sweiwei";
	public static final String REGISTER_CODE_URL = SECURE_API_PATH + "/registerCode";
	
	final public static String EVENT_ANNOUNCE_LIST = "announce_list";
	final public static String EVENT_ANNOUNCE_BODY = "announce_body";
	final public static String GROUP_SEPARATOR = ";-;"; // 有时间的话，把这个改为0x1D吧，是char的，不是String, CommenDAOImpl.java里面的语句也要相应改掉
}
