package com.ljx.tutor_platform.service.impl;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ljx.tutor_platform.dao.CourseDao;
import com.ljx.tutor_platform.entity.Course;
import com.ljx.tutor_platform.entity.User;
import com.ljx.tutor_platform.service.CourseService;
import com.ljx.tutor_platform.utils.MyUtils;

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

	@Override
	public boolean createCourse(Course course, HttpServletRequest request) {
		// TODO Auto-generated method stub
		User currentUser = (User) request.getSession().getAttribute("currentUser");
		
		if(currentUser!=null)
			course.setUserId(currentUser.getId());
		else
			return false;
		
		String time = MyUtils.getCurrentDate();// new Date()为获取当前系统时间
		course.setCreateTime(time);
		return courseDao.createCourse(course);
	}

	@Override
	public List<Course> getMyCourse(HttpServletRequest request) {
		// TODO Auto-generated method stub
		User currentUser = (User) request.getSession().getAttribute("currentUser");
		int currentId = currentUser.getId();
		List<Course> myCourses = courseDao.getMyCourse(currentId);
		return myCourses;
	}

	@Override
	public boolean deleteMyCourse(Integer id) {
		// TODO Auto-generated method stub
		return courseDao.delCourse(id);
	}
}
