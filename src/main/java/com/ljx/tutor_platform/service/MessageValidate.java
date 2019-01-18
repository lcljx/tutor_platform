package com.ljx.tutor_platform.service;

import javax.servlet.http.HttpServletRequest;

public interface MessageValidate {
	public String sendMsg(String[] phoneNumbers,HttpServletRequest request);
}
