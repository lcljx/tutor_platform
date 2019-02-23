package com.ljx.tutor_platform.dao;

import java.util.List;
import java.util.Map;

import com.ljx.tutor_platform.entity.TutorType;

public interface TypeDao {

	List<TutorType> showCourseType(String flag);

	boolean addTypes(TutorType tutorType);

	List<TutorType> findTypeList(Map<String, Object> map);

	Long getTotal(Map<String, Object> map);

	boolean delType(String id);

	boolean editType(TutorType tutorType);

}
