package com.ljx.tutor_platform.controller;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ljx.tutor_platform.entity.User;
import com.ljx.tutor_platform.service.MessageValidate;
import com.ljx.tutor_platform.service.UserService;
@RestController
@RequestMapping(value="/user")
@EnableAutoConfiguration
public class UserController{
	@Autowired
	private MessageValidate messageValidate;
	@Autowired
	private UserService userService;
    @RequestMapping(value="/register")
    
    public String getUsers(User user,HttpServletRequest request){
    	String result = "";
        JSONObject json = (JSONObject)request.getSession().getAttribute("code");
        try {
        	
			if(!json.getString("code").equals(user.getVcode())){
			    return "验证码错误";
			}else if((System.currentTimeMillis() - json.getLong("createTime")) > 1000 * 60 * 5){
		            return "验证码过期";
		    }else {
		    	 //生成盐（部分，需要存入数据库中）
		    	String random=new SecureRandomNumberGenerator().nextBytes().toHex();
		    	 
		    	 //将原始密码加盐（上面生成的盐），并且用md5算法加密三次，将最后结果存入数据库中
		    	String password = new Md5Hash(user.getPass(),random,3).toString();
		    	user.setPass(password);
		    	System.out.println(user.getPass());
		    	userService.addUser(user);
		    	result = "恭喜您注册成功，可以登录啦";
		    }
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			 e.printStackTrace();
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
