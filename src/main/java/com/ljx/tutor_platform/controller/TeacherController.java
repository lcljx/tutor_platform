package com.ljx.tutor_platform.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ljx.tutor_platform.entity.User;
import com.ljx.tutor_platform.service.UserService;

@RestController
@RequestMapping(value="/teacher")
@EnableAutoConfiguration
public class TeacherController {

	@Autowired
	private UserService tservice;
	
	/**	
	 * 主要展示在前端的名师页面上，排序是按照积分排序
	 * */
	@RequestMapping("/showTeachers")
	public List<User> showTeachers(){
		return tservice.showTeachers();
	}
	
	/**	
	 * 主要展示在前端的关于我们的页面上，按照积分排序选出最高的四个
	 * */
	@RequestMapping("/showfourTeachers")
	public List<User> showfourTeachers(){
		return tservice.showfourTeachers();
	}
}
