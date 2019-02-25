package com.ljx.tutor_platform.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ljx.tutor_platform.dao.UserDAO;
import com.ljx.tutor_platform.entity.PageBean;
import com.ljx.tutor_platform.entity.Roles;
import com.ljx.tutor_platform.entity.User;
import com.ljx.tutor_platform.service.ManageService;
import com.ljx.tutor_platform.utils.MyUtils;

@Service
public class ManageServiceImpl implements ManageService{

	@Autowired
	private UserDAO userDao;
	
	@Override
	public Map<String, Object> getManageInfos(String page, String rows, User user,String flag) {
		// TODO Auto-generated method stub
		PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("userName", user.getUserName());
		map.put("trueName", user.getTrueName());
		map.put("start", pageBean.getStart());
		map.put("size", pageBean.getPageSize());
		List<User> users = null;
		Long total = null;
		if(flag==null) {
			users =userDao.findManageInfos(map);
			total=userDao.getTotal(map);
		}else {
			users =userDao.findUsersInfos(map);
			total=userDao.getUsersTotal(map);
		}
		Map<String,Object> m = new HashMap<>();
		m.put("rows", users);
		m.put("total", total);
		return m;
	}

	/**
	 *添加新的管理员
	 * */
	@Override
	public boolean newManage(User user) {
		// TODO Auto-generated method stub
		 //生成盐（部分，需要存入数据库中）
    	String random=new SecureRandomNumberGenerator().nextBytes().toHex();
    	//将原始密码加盐（上面生成的盐），并且用md5算法加密三次，将最后结果存入数据库中
    	String password = new Md5Hash(user.getPass(),random,3).toString();
    	user.setPass(password);
    	String createTime = MyUtils.getCurrentDate();
    	user.setCreateTime(createTime);
    	user.setNickName(user.getUserName());
    	user.setSalt(random);
    	user.setStatus(1);//1表示可用账号，0表示注销账号
    	boolean flag = userDao.addManager(user);
		return flag;
	}

	@Override
	public List<Roles> showRoles() {
		// TODO Auto-generated method stub
		return userDao.showRoles();
	}

	@Override
	public boolean delManager(String id) {
		// TODO Auto-generated method stub
		return userDao.delManager(id);
	}

	@Override
	public boolean updateRoles(String id, String role) {
		// TODO Auto-generated method stub
		return userDao.updateRoles(id,role);
	}



}
