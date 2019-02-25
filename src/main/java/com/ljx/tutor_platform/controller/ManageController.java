package com.ljx.tutor_platform.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ljx.tutor_platform.entity.Roles;
import com.ljx.tutor_platform.entity.User;
import com.ljx.tutor_platform.service.ManageService;

@RestController
@RequestMapping(value="/manageinfo")
@EnableAutoConfiguration
public class ManageController {

	@Autowired
	private ManageService mService;
	
	/**
	 *显示到后台管理页
	 * */
	@RequestMapping(value="/manageInfos")
	public Map<String, Object> manageInfos(String page,String rows,User user,String flag) throws Exception{
		Map<String,Object> map=new HashMap<String,Object>();
		map = mService.getManageInfos(page,rows,user,flag);
		return map;
	}
	
	/**
	 *添加新的管理员
	 * */
	@RequestMapping(value="/newManage")
	public Boolean newManage(User user){
		boolean flag = mService.newManage(user);
		return flag;
	}
	
	/**
	 *显示出角色名
	 * */
	@RequestMapping(value="/showRoles")
	public List<Roles> showRoles(){
		List<Roles> roles = mService.showRoles();
		return roles;
	}
	
	/**
	 *显示出角色名
	 * */
	@RequestMapping(value="/delManager")
	public Boolean delManager(String id){
		boolean flag = mService.delManager(id);
		return flag;
	}
	
	/**
	 *修改角色
	 * */
	
	@RequestMapping(value="/updateRoles")
	public Boolean updateRoles(String id,String role){
		boolean flag = mService.updateRoles(id,role);
		return flag;
	}
}
