package com.ljx.tutor_platform.utils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.ljx.tutor_platform.dao.UserDAO;

public class MyRealm extends AuthorizingRealm{

	@Autowired
	private UserDAO userDao;
	
    // 设置realm的名称
    @Override
    public void setName(String name) {
        super.setName("myRealm");
    }
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// TODO Auto-generated method stub
		//1.从主体传过来的信息中获取用户名
		String userName = (String) principals.getPrimaryPrincipal();
		
		//从数据库或缓存中获取角色数据
		//设置权限
		Set<String> roles = getRolesByUserName(userName);
		Set<String> permissions = getPermissionsByUserName();
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		authorizationInfo.setRoles(roles);
		authorizationInfo.setStringPermissions(permissions);
		return authorizationInfo;
	}

	private Set<String> getPermissionsByUserName() {
		// TODO Auto-generated method stub
		Set<String> sets = new HashSet<String>();
		sets.add("user:select");
		sets.add("user:delete");
		return sets;
	}

	private Set<String> getRolesByUserName(String userName) {
		// TODO Auto-generated method stub
		List<String> list = userDao.queryRolesByUserName(userName);
		Set<String> sets = new HashSet<String>(list);
		return sets;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// TODO Auto-generated method stub
		
		//1.从主体传过来的信息中获取用户名
		String userName = (String) token.getPrincipal();
		
		//2.通过用户名到数据库中获取凭证
		String password = userDao.getPasswordByUserName(userName);
		if(password == null){
			return null;
		}
		
		
    	String salt = userDao.getSaltByUsername(userName);
		SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(userName,password,"myRealm");
		//加密加盐
		simpleAuthenticationInfo.setCredentialsSalt(ByteSource.Util.bytes(salt));
		return simpleAuthenticationInfo;
	}


}
