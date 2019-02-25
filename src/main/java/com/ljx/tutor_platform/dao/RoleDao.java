package com.ljx.tutor_platform.dao;

import java.util.List;
import java.util.Map;

import com.ljx.tutor_platform.entity.Roles;

public interface RoleDao {

	List<Roles> getRoles(Map<String, Object> map);

	Long getTotal(Map<String, Object> map);

	boolean updateRoles(Roles role);

	boolean newRoles(Roles role);

	boolean delRoles(String id);

}
