package com.ljx.tutor_platform.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ljx.tutor_platform.entity.Favorite;
import com.ljx.tutor_platform.service.FavoriteService;

@RestController
@RequestMapping(value="/favourite")//课程相关处理
@EnableAutoConfiguration
public class FavoriteController {

	@Autowired
	private FavoriteService favoriteService;
	
	//添加收藏
	@RequestMapping(value="/addFavorite")
	public String addFavorite(Favorite favorite,HttpServletRequest request) {
		boolean flag = favoriteService.addFavorite(favorite,request);
		if(flag)
			return "成功加入收藏";
		else
			return "加入收藏失败";
	}
	
	//显示收藏
	@RequestMapping(value="/showFavourite")
	public List<Favorite> showFavorite(HttpServletRequest request) {
		List<Favorite> favoriteList = null;
		favoriteList = favoriteService.showFavorite(request);
		return favoriteList;
	}
	
	//删除收藏
	@RequestMapping(value="/deleteFavourite")
	public boolean deleteFavourite(Integer favoriteId) {
		boolean flag = favoriteService.deleteFavourite(favoriteId);
		return flag;
	}
}
