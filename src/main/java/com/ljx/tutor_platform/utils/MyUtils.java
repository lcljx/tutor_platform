package com.ljx.tutor_platform.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyUtils {

	public static String getCurrentDate() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String createTime = df.format(new Date());// new Date()为获取当前系统时间
		return createTime;
	}
}
