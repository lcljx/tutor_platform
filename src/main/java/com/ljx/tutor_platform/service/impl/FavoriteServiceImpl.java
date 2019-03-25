package com.ljx.tutor_platform.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Service;

import com.ljx.tutor_platform.dao.FavoriteDao;
import com.ljx.tutor_platform.entity.Favorite;
import com.ljx.tutor_platform.entity.User;
import com.ljx.tutor_platform.service.FavoriteService;


@Service
public class FavoriteServiceImpl implements FavoriteService{

	@Autowired
	private FavoriteDao favoriteDao;
	
	/**
	 * @author ljx
	 *{@link Description} 添加到收藏
	 */
	@Override
	public boolean addFavorite(Favorite favorite, HttpServletRequest request) {
		// TODO Auto-generated method stub
		User currentUser = (User) request.getSession().getAttribute("currentUser");
		if(currentUser==null)
			return false;
		
		favorite.setUserId(currentUser.getId());
		boolean flag = favoriteDao.addFavorite(favorite);
		return flag;
	}
	/**
	 * @author ljx
	 *{@link Description} 显示收藏
	 */
	@Override
	public List<Favorite> showFavorite(HttpServletRequest request) {
		// TODO Auto-generated method stub
		User currentUser = (User) request.getSession().getAttribute("currentUser");
		List<Favorite> favoriteList = favoriteDao.showFavorite(currentUser.getId());
		return favoriteList;
	}
	@Override
	public boolean deleteFavourite(Integer id) {
		// TODO Auto-generated method stub
		
		return favoriteDao.deleteFavorite(id);
	}

}
