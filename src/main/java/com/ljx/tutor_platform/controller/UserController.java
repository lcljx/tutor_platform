package com.ljx.tutor_platform.controller;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.collections4.map.HashedMap;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md5Hash;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	/**	
	 * 注册
	 * */
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
		    	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		    	String createTime = df.format(new Date());// new Date()为获取当前系统时间
		    	user.setCreateTime(createTime);
		    	user.setNickName(user.getUserName());
		    	user.setSalt(random);
		    	user.setStatus(1);//1表示可用账号，0表示注销账号
		    	user.setRole("1");
		    	userService.addUser(user);
		    	result = "恭喜您注册成功，可以登录啦";
		    }
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			 e.printStackTrace();
		}
		return result;
    }
    
    /**
	 * 发送验证码
	 * */
    @RequestMapping(value="/sendCode")
    public String sendCode(String phoneNum,HttpServletRequest request){
    	String[] phoneNumbers = {phoneNum};
    	String rs = messageValidate.sendMsg(phoneNumbers,request);
    	return rs;
    }
    
    /**
	 * 后台登录
	 * */
    @RequestMapping(value="/login",method=RequestMethod.POST)
    public String login(String account,String password) {
    	Map<String, Object> m = new HashedMap<String, Object>();
    	m = userService.login(account,password);
    	System.out.println(m.get("flag"));
    	return (String) m.get("flag");
    }
    
    /**
   	 *前台 登录
   	 * */
	@RequestMapping(value="/frontLogin",method=RequestMethod.POST)
	public String frontLogin(String account,String password) {
		Map<String, Object> m = new HashedMap<String, Object>();
		m = userService.frontLogin(account,password);
		return (String) m.get("flag");
	}
    
    @RequestMapping(value="/currentUser")
    public User currentUser(HttpServletRequest request) {
    	User currentUser = (User) request.getSession().getAttribute("currentUser");
    	List<String> roles = userService.getRoleByUsername(currentUser.getUserName());
    	String role = roles.get(0);
    	currentUser.setRole(role);
		return currentUser;
    }
    
    /**
	 * 后台管理系统修改密码
	 * */
    @RequestMapping(value="/modifyPassword")
    public Integer modifyPassword(String username,String oldPassword,String newPassword) {
    	int msg = 0;
    	System.out.println("进去修改密码");
    	String compareOld = userService.findPassByUsername(username);
    	String salt = userService.findSaltByUsername(username);
    	String oldPassword2 = new Md5Hash(oldPassword,salt,3).toString();
    	if(compareOld.equals(oldPassword2)) {
	    	String password = new Md5Hash(newPassword,salt,3).toString();
	    	boolean flag = userService.modifyPassword(username,password);
	    	if(flag) {
	    		msg = 1;
	    	}
    	}else {
    		msg=2;
    	}
		return msg;
    }
    /**
	 * 退出当前账户
     * @throws IOException 
	 * */
    @RequestMapping(value="/logout")
    public void logout(HttpServletRequest request,HttpServletResponse response,@RequestParam String flag) throws IOException {
    	System.out.println(flag);
    	request.getSession().removeAttribute("currentUser");
    	if(flag.equals("front")) {
    		response.sendRedirect(request.getContextPath()+"/login.html");
    	}else {
    		response.sendRedirect(request.getContextPath()+"/manage/login.html");
    	}
    }
 
}
