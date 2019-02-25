package com.ljx.tutor_platform.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
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
	public void addUser(User user) {
		// TODO Auto-generated method stub
		userDao.addUser(user);
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
	
}
