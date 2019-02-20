package com.ljx.tutor_platform.dao;

import java.util.List;

import com.ljx.tutor_platform.entity.User;
 
public interface UserDAO {
    User findUserByUsername(String userName);

	boolean addUser(User user);

	String getPasswordByUserName(String userName);

	List<String> queryRolesByUserName(String userName);

	String getSaltByUsername(String userName);

	boolean modifyPassword(String username, String password);
}
