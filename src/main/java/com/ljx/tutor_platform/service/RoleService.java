package com.ljx.tutor_platform.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.ljx.tutor_platform.entity.Roles;

public interface RoleService {

	boolean updateRoles(String id,String description,String name);

	Map<String, Object> getRoles(String page, String rows, Roles role);

	boolean delRoles(String id);

	boolean newRoles(Roles role, HttpServletRequest request);

}
