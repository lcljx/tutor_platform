package com.ljx.tutor_platform.dao;

import com.ljx.tutor_platform.entity.User;
 
public interface UserDAO {
    User findUserByUsername(String userName);

	boolean addUser(User user);
}
