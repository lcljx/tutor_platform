package com.ljx.tutor_platform.dao;

import java.util.List;

import com.ljx.tutor_platform.entity.Favorite;

public interface FavoriteDao {

	boolean addFavorite(Favorite favorite);

	List<Favorite> showFavorite(Integer id);

	boolean deleteFavorite(Integer id);

}
