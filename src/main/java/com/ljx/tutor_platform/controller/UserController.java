package com.ljx.tutor_platform.controller;
import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ljx.tutor_platform.entity.User;
import com.ljx.tutor_platform.service.MessageValidate;
@RestController
@RequestMapping(value="/user")
@EnableAutoConfiguration
public class UserController{
	@Autowired
	private MessageValidate messageValidate;
	
    @RequestMapping(value="/register")
    public String getUsers(User user,HttpServletRequest request){
    	String result = "";
        JSONObject json = (JSONObject)request.getSession().getAttribute("code");
        try {
        	String code =  json.getString("code");
        	System.out.println("code");
			if(!json.getString("code").equals(user.getVcode())){
			    return "验证码错误";
			}else if((System.currentTimeMillis() - json.getLong("createTime")) > 1000 * 60 * 5){
		            return "验证码过期";
		    }else {
		    	result = "恭喜您注册成功，可以登录啦";
		    }
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			return "验证码错误";
		}
        
		return result;
    }
    
    @RequestMapping(value="/sendCode")
    public String sendCode(@RequestBody String phoneNum,HttpServletRequest request){
    	String[] phoneNumbers = {phoneNum};
    	String rs = messageValidate.sendMsg(phoneNumbers,request);
    	return rs;
    }
}
