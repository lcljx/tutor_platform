package com.ljx.tutor_platform.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ljx.tutor_platform.dao.TypeDao;
import com.ljx.tutor_platform.entity.TutorType;
import com.ljx.tutor_platform.service.TypeService;

@Service
public class TypeServiceImpl implements TypeService{

	@Autowired
	private TypeDao typeDao;
	
	@Override
	public List<TutorType> showCourseType(String flag) {
		// TODO Auto-generated method stub
		return typeDao.showCourseType(flag);
	}

	@Override
	public boolean addType(TutorType tutorType) {
		// TODO Auto-generated method stub
		return typeDao.addTypes(tutorType);
	}

	@Override
	public List<TutorType> findTypeList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return typeDao.findTypeList(map);
	}

	@Override
	public Long getTotal(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return typeDao.getTotal(map);
	}

	@Override
	public boolean delType(String id) {
		// TODO Auto-generated method stub
		return typeDao.delType(id);
	}

	@Override
	public boolean editType(TutorType tutorType) {
		// TODO Auto-generated method stub
		return typeDao.editType(tutorType);
	}

	@Override
	public List<TutorType> getCourseTypes(String flag) {
		// TODO Auto-generated method stub
		return typeDao.showCourseType(flag);
	}

}
