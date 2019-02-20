package com.ljx.tutor_platform.service;

import com.ljx.tutor_platform.entity.User;

public interface UserService {
	
	public void addUser(User user);

	public User findUserByUsername(String userName);

	public String findSaltByUsername(String username);

	public boolean modifyPassword(String username, String password);

	public String findPassByUsername(String username);
}
