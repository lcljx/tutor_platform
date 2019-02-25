package com.ljx.tutor_platform.service;

import java.util.List;
import java.util.Map;

import com.ljx.tutor_platform.entity.Roles;
import com.ljx.tutor_platform.entity.User;

public interface ManageService {
	
	Map<String, Object> getManageInfos(String page, String rows, User user,String flag);

	boolean newManage(User user);

	List<Roles> showRoles();

	boolean delManager(String id);

	boolean updateRoles(String id, String role);

}
