package com.ljx.tutor_platform.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections4.map.HashedMap;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ljx.tutor_platform.dao.UserDAO;
import com.ljx.tutor_platform.entity.User;
import com.ljx.tutor_platform.service.UserService;
import com.ljx.tutor_platform.utils.MyUtils;
@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDAO userDao;
	@Override
	public String editUser(User user,HttpServletRequest request) {
		// TODO Auto-generated method stub
		String time = MyUtils.getCurrentDate();// new Date()为获取当前系统时间
		String password = null;
		String result = "";
		String random = null;
		boolean flag = false;
		if(user.getEmail()==null) {
			flag = true;
		}
        JSONObject json = (JSONObject)request.getSession().getAttribute("code");
        try {
        	
			if(!json.getString("code").equals(user.getVcode())){
			    return "验证码错误";
			}else if((System.currentTimeMillis() - json.getLong("createTime")) > 1000 * 60 * 5){
		            return "验证码过期";
		    }else {
		    	 //生成盐（部分，需要存入数据库中）
		    	user.setNickName(user.getUserName());
		    	
		    	user.setStatus(1);//1表示可用账号，0表示注销账号
		    	user.setRole("3 ");
		    	if(flag) {
		    		//将原始密码加盐（上面生成的盐），并且用md5算法加密三次，将最后结果存入数据库中
		    		random=new SecureRandomNumberGenerator().nextBytes().toHex();
		    		password = new Md5Hash(user.getPass(),random,3).toString();
		    		user.setSalt(random);
			    	user.setPass(password);
		    		
			    	user.setCreateTime(time);
		    		userDao.addUser(user);
		    		result = "恭喜您注册成功，请前往登录";
		    	}else{
		    		String salt = userDao.getSaltByUsername(user.getUserName());
		    		password = new Md5Hash(user.getPass(),salt,3).toString();
		    		user.setPass(password);
			    	user.setUpdateTime(time);
		    		userDao.updatePass(user);
		    		request.getSession().removeAttribute("currentUser");
		    		result = "密码修改成功，请前往登录";
		    	}
		    }
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			 e.printStackTrace();
		}
		return result;
	}

	@Override
	public String findSaltByUsername(String username) {
		// TODO Auto-generated method stub
		return userDao.getSaltByUsername(username);
	}

	@Override
	public boolean modifyPassword(String username, String password) {
		// TODO Auto-generated method stub
		return userDao.modifyPassword(username,password);
	}

	@Override
	public String findPassByUsername(String username) {
		// TODO Auto-generated method stub
		return userDao.getPasswordByUserName(username);
	}

	/**	
	 * 主要展示在前端的名师页面上，排序是按照积分排序
	 * */
	@Override
	public List<User> showTeachers() {
		// TODO Auto-generated method stub
		return userDao.showTeachers();
	}

	@Override
	public List<User> showfourTeachers() {
		// TODO Auto-generated method stub
		return userDao.showfourTeachers();
	}

	@Override
	public Map<String, Object> login(String userName, String pass) {
		// TODO Auto-generated method stub
		Map<String, Object> m = new HashedMap<String, Object>();
		User user = new User();
    	String flag = "";
    	Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(userName,pass);
		List<String> roles = new ArrayList<String>();
		try {
			subject.login(token);
			if(subject.isAuthenticated()){
				user = userDao.findUserByUsername(userName);
				Session session = subject.getSession();
				session.setAttribute("currentUser", user);
			}
			if(!subject.hasRole("admin")&&!subject.hasRole("nadmin")) {
				flag="非法角色";
				subject.logout();
			}else {
				flag = "登录成功";
				String currentDate = MyUtils.getCurrentDate();
				userDao.lastLoginTime(userName,currentDate);
			}
			m.put("flag",flag);
			m.put("subject", subject);
		} catch (AuthenticationException e) {
			// TODO Auto-generated catch block
			String errorMsg = "账户或密码错误";
			e.printStackTrace();
			m.put("flag",errorMsg);
		}
    	return m;	
	}

	
	@Override
	public Map<String, Object> frontLogin(String userName, String pass) {
		// TODO Auto-generated method stub
		Map<String, Object> m = new HashedMap<String, Object>();
		User user = new User();
    	String flag = "";
    	Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(userName,pass);
		try {
			subject.login(token);
			if(subject.isAuthenticated()){
				user = userDao.findUserByUsername(userName);
				Session session = subject.getSession();
				session.setAttribute("currentUser", user);
			}
			if(!subject.hasRole("user")) {
				flag="非法角色";
				subject.logout();
			}else {
				flag = "登录成功";
				String currentDate = MyUtils.getCurrentDate();
				userDao.lastLoginTime(userName,currentDate);
			}
			m.put("flag",flag);
			m.put("subject", subject);
		} catch (AuthenticationException e) {
			// TODO Auto-generated catch block
			String errorMsg = "账户或密码错误";
			e.printStackTrace();
			m.put("flag",errorMsg);
		}
    	return m;	
	}

	@Override
	public List<String> getRoleByUsername(String userName) {
		// TODO Auto-generated method stub
		return userDao.getRoleByUsername(userName);
	}

	@Override
	public boolean updateUser(User user) {
		// TODO Auto-generated method stub
		return userDao.updateUser(user);
	}
	
}
