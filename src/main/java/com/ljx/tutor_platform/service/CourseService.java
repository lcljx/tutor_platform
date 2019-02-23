package com.ljx.tutor_platform.service;

import java.util.List;
import java.util.Map;

import com.ljx.tutor_platform.entity.Course;

public interface CourseService {

	List<Course> getTopSixCourse();

	List<Course> getCourseByType(String courseType);

	Long getTotal(Map<String, Object> map);

	List<Course> findCourseList(Map<String, Object> map);

	boolean editCourse(Course course);

	boolean delCourse(Integer id);

}
