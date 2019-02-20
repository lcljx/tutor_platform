package com.ljx.tutor_platform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ljx.tutor_platform.dao.UserDAO;
import com.ljx.tutor_platform.entity.User;
import com.ljx.tutor_platform.service.UserService;
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
	public User findUserByUsername(String userName) {
		// TODO Auto-generated method stub
		
		return userDao.findUserByUsername(userName);
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

}
