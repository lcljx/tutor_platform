package com.ljx.tutor_platform.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ljx.tutor_platform.dao.CourseDao;
import com.ljx.tutor_platform.entity.Course;
import com.ljx.tutor_platform.service.CourseService;

@Service
public class CourseServiceImpl implements CourseService{

	@Autowired
	private CourseDao courseDao;

	@Override
	public List<Course> getTopSixCourse() {
		// TODO Auto-generated method stub
		return courseDao.getTopSixCourse();
	}

	@Override
	public List<Course> getCourseByType(String courseType) {
		// TODO Auto-generated method stub
		return courseDao.getCourseByType(courseType);
	}

	@Override
	public Long getTotal(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return courseDao.getTotal(map);
	}

	@Override
	public List<Course> findCourseList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return courseDao.findCourseList(map);
	}

	@Override
	public boolean editCourse(Course course) {
		// TODO Auto-generated method stub
		return courseDao.editCourse(course);
	}

	@Override
	public boolean delCourse(Integer id) {
		// TODO Auto-generated method stub
		return courseDao.delCourse(id);
	}
}
