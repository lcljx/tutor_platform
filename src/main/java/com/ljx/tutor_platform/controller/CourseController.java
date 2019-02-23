package com.ljx.tutor_platform.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ljx.tutor_platform.entity.Course;
import com.ljx.tutor_platform.entity.PageBean;
import com.ljx.tutor_platform.service.CourseService;


@RestController
@RequestMapping(value="/course")//课程相关处理
@EnableAutoConfiguration
public class CourseController {
	
	@Autowired
	private CourseService courseService;
	
	/**
	 *首页显示
	 * */
	@RequestMapping(value="/showCourse")
	public List<Course> showCourse(){
		List<Course> courses = new ArrayList<Course>();
		courses = courseService.getTopSixCourse();//获取最受欢迎的6门课程
		return courses;
	}
	
	/**
	 *具体课程页 如小学课程页显示
	 * */
	@RequestMapping(value="showCourseByType")
	public List<Course> showCourseByType(String courseType){
		List<Course> courses = new ArrayList<Course>();
		courses = courseService.getCourseByType(courseType);//获取最受欢迎的6门课程
		return courses;
	}
	
	/**
	 *修改课程相关信息
	 * */
	@RequestMapping(value="editCourse")
	public boolean editCourse(Course course,String id){
		return courseService.editCourse(course);
	}
	
	/**
	 *删除课程
	 * */
	@RequestMapping(value="delCourse")
	public boolean delCourse(Integer id){
		return courseService.delCourse(id);
	}
	
	/**
	 *显示到后台管理页
	 * */
	@RequestMapping(value="courseList")
	public Map<String, Object> courseList(@RequestParam(value="page",required=false)String page,
			@RequestParam(value="rows",required=false)String rows,
			Course course) throws Exception{
		PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("courseName", course.getCourseName());
		map.put("rank", course.getRank());
		map.put("start", pageBean.getStart());
		map.put("size", pageBean.getPageSize());
		List<Course> typetList =courseService.findCourseList(map);
		Long total=courseService.getTotal(map);
		Map<String,Object> m = new HashMap<>();
		m.put("rows", typetList);
		m.put("total", total);
		return m;
	}
}
