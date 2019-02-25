package com.ljx.tutor_platform.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ljx.tutor_platform.entity.Roles;
import com.ljx.tutor_platform.service.RoleService;

@RestController
@RequestMapping(value="/roles")
@EnableAutoConfiguration
public class RoleController {

	@Autowired
	private RoleService rService;
	
	/**
	 *显示到后台权限管理
	 * */
	@RequestMapping(value="/getRoles")
	public Map<String, Object> getRoles(String page,String rows,Roles role) throws Exception{
		Map<String,Object> map=new HashMap<String,Object>();
		map = rService.getRoles(page,rows,role);
		return map;
	}
	
	/**
	 *添加新的角色
	 * */
	@RequestMapping(value="/newRoles")
	public Boolean newRoles(Roles role,HttpServletRequest request){
		boolean flag = rService.newRoles(role,request);
		return flag;
	}
	
	/**
	 *修改角色
	 * */
	
	@RequestMapping(value="/updateRoles")
	public Boolean updateRoles(String id,String description,String name){
		boolean flag = rService.updateRoles(id,description,name);
		return flag;
	}
	
	/**
	 *修改角色
	 * */
	
	@RequestMapping(value="/delRoles")
	public Boolean delRoles(String id){
		boolean flag = rService.delRoles(id);
		return flag;
	}
}
