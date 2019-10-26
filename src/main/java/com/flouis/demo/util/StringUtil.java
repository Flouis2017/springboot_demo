package com.flouis.demo.util;

import org.apache.commons.lang3.StringUtils;

public class StringUtil {

	public static String getString(String arg){
		return StringUtils.isBlank(arg) ? null : arg;
	}

}
