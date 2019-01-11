package com.ljx.tutor_platform.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ljx.tutor_platform.dao.UserDAO;
import com.ljx.tutor_platform.entity.User;

import java.util.List;
 
@RestController
@RequestMapping(value = "/try")
@EnableAutoConfiguration
public class UserController {
    @Autowired
    private UserDAO userDAO;
 
    @RequestMapping(value = "/user")
    public List<User> getUsers(){
        return userDAO.selectUsers();
    }
}
