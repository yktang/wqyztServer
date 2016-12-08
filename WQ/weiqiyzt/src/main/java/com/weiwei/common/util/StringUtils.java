package com.weiwei.common.util;

public class StringUtils {

	public static boolean isBlank(Object string) {
		if (string == null || String.valueOf(string).trim().equals("")) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isNotBlank(Object string) {
		return !isBlank(string);
	}

	public static String[] insert(String[] arr, String str) {
		int size = arr.length;

		String[] tmp = new String[size + 1];

		System.arraycopy(arr, 0, tmp, 0, size);

		tmp[size] = str;

		return tmp;
	}
}
