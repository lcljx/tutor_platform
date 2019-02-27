package com.ljx.tutor_platform.dao;

import java.util.List;
import java.util.Map;

import com.ljx.tutor_platform.entity.Roles;
import com.ljx.tutor_platform.entity.User;
 
public interface UserDAO {
    User findUserByUsername(String userName);

    User findUserByid(String id);
    
	boolean addUser(User user);

	String getPasswordByUserName(String userName);

	List<String> queryRolesByUserName(String userName);

	String getSaltByUsername(String userName);

	boolean modifyPassword(String username, String password);

	List<User> showTeachers();

	List<User> showfourTeachers();

	List<String> getRoleByUsername(String userName);

	List<User> findManageInfos(Map<String, Object> map);

	Long getTotal(Map<String, Object> map);

	void lastLoginTime(String userName, String currentDate);

	boolean newManage(String username, String password, String rolename);

	List<Roles> showRoles();

	boolean delManager(String id);

	boolean addManager(User user);

	List<User> findUsersInfos(Map<String, Object> map);

	Long getUsersTotal(Map<String, Object> map);

	boolean updateRoles(String id, String role);

	void updatePass(User user);

}
