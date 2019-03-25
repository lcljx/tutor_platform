package com.ljx.tutor_platform.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ljx.tutor_platform.entity.Favorite;

public interface FavoriteService {

	boolean addFavorite(Favorite favorite, HttpServletRequest request);

	List<Favorite> showFavorite(HttpServletRequest request);

	boolean deleteFavourite(Integer id);

}
