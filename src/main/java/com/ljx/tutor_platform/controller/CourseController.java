package com.ljx.tutor_platform.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ljx.tutor_platform.entity.Course;
import com.ljx.tutor_platform.entity.PageBean;
import com.ljx.tutor_platform.entity.User;
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
		courses = courseService.getCourseByType(courseType);
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
		List<Course> coursetList =courseService.findCourseList(map);
		Long total=courseService.getTotal(map);
		Map<String,Object> m = new HashMap<>();
		m.put("rows", coursetList);
		m.put("total", total);
		return m;
	}
	
	@RequestMapping(value="/createCourse")
	public boolean createCourse(Course course,HttpServletRequest request,MultipartFile file) throws IOException {
		
		//创建文件 
		String path = "C://Users//ASUS//git//tutor_platform//src//main//resources//static//img//";
		File dir=new File(path);
		if(!dir.exists()){
			dir.mkdirs();
		}
		
        //返回原来在客户端的文件系统的文件名
        String fileName=file.getOriginalFilename();
        FileOutputStream imgOut=new FileOutputStream(new File(dir,fileName));//根据 dir 抽象路径名和 img 路径名字符串创建一个新 File 实例。
        
        imgOut.write(file.getBytes());//返回一个字节数组文件的内容
        imgOut.close();
        course.setPic("img/"+fileName);
        boolean flag = courseService.createCourse(course,request);
		return flag;
	}
	
	@RequestMapping(value="/getMyCourse")
	public List<Course> getMyCourse(HttpServletRequest request) {
		List<Course> courses = courseService.getMyCourse(request);
		return courses;
	}
	
	@RequestMapping(value="/deleteMyCourse")
	public boolean deleteMyCourse(Integer id) {
		boolean flag = courseService.deleteMyCourse(id);
		return flag;
	}
}
