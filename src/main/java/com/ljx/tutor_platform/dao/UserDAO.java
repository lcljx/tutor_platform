package com.ljx.tutor_platform.dao;


import java.util.List;

import com.ljx.tutor_platform.entity.User;
 
public interface UserDAO {
    List<User> selectUsers();
}
