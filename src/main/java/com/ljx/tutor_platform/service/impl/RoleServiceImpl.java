package com.ljx.tutor_platform.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ljx.tutor_platform.dao.RoleDao;
import com.ljx.tutor_platform.entity.PageBean;
import com.ljx.tutor_platform.entity.Roles;
import com.ljx.tutor_platform.entity.User;
import com.ljx.tutor_platform.service.RoleService;
import com.ljx.tutor_platform.utils.MyUtils;

@Service
public class RoleServiceImpl implements RoleService{
	
	@Autowired
	private RoleDao rDao;
	@Override
	public boolean newRoles(Roles role,HttpServletRequest request) {
		// TODO Auto-generated method stub
		String createTime = MyUtils.getCurrentDate();
		User currentUser = (User) request.getSession().getAttribute("currentUser");
		role.setCreateTime(createTime);
		role.setCreateUser(currentUser.getTrueName());
		return rDao.newRoles(role);
	}

	@Override
	public Map<String, Object> getRoles(String page, String rows, Roles role) {
		// TODO Auto-generated method stub
		PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("description", role.getDescription());
		map.put("start", pageBean.getStart());
		map.put("size", pageBean.getPageSize());
		List<Roles> roles = null;
		Long total = null;
		roles =rDao.getRoles(map);
		total=rDao.getTotal(map);
		Map<String,Object> m = new HashMap<>();
		m.put("rows", roles);
		m.put("total", total);
		return m;
	}

	@Override
	public boolean updateRoles(String id,String description,String name) {
		// TODO Auto-generated method stub
		Roles role2 = new Roles();
		Integer id2 = Integer.parseInt(id);
		role2.setId(id2);
		role2.setDescription(description);
		role2.setName(name);
		String updateTime = MyUtils.getCurrentDate();
		role2.setUpdateTime(updateTime);
		return rDao.updateRoles(role2);
	}

	@Override
	public boolean delRoles(String id) {
		// TODO Auto-generated method stub
		return rDao.delRoles(id);
	}
}
