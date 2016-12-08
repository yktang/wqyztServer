package com.weiwei.common.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class DateFormatUtils {

	public static String toDateStringTimestamp(Timestamp timestamp) {
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(timestamp);
	}
	
	/**
	 * 获取当前时间戳
	 * 
	 * @return
	 *
	 */
	public static Timestamp getNowTimestamp() {
		return new Timestamp(System.currentTimeMillis());
	}

}
