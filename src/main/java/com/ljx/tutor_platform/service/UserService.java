package com.ljx.tutor_platform.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.ljx.tutor_platform.entity.User;

public interface UserService {
	
	public String editUser(User user,HttpServletRequest request);

	public String findSaltByUsername(String username);

	public boolean modifyPassword(String username, String password);

	public String findPassByUsername(String username);

	public List<User> showTeachers();

	public List<User> showfourTeachers();

	public Map<String, Object> login(String userName, String pass);
	
	public Map<String, Object> frontLogin(String userName, String pass);

	public List<String> getRoleByUsername(String userName);

	public boolean updateUser(User user);
}
