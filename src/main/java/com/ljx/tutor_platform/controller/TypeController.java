package com.ljx.tutor_platform.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ljx.tutor_platform.entity.PageBean;
import com.ljx.tutor_platform.entity.TutorType;
import com.ljx.tutor_platform.entity.User;
import com.ljx.tutor_platform.service.TypeService;

@RestController
@RequestMapping(value="/type")//类型
@EnableAutoConfiguration
public class TypeController {

	@Autowired
	private TypeService typeService;
	
	@RequestMapping(value="/showCourseType")
	public List<TutorType> showCourseType(String flag){
		List<TutorType> types = new ArrayList<TutorType>();
		types = typeService.showCourseType(flag);
		return types;
	}
	
	/**
	 * 新增类别
	 * */
	@RequestMapping(value="/newType")
	public boolean newCourseType(String flag,String typeName,String describes,String url,HttpServletRequest request) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
    	String createTime = df.format(new Date());// new Date()为获取当前系统时间
    	User currentUser = (User) request.getSession().getAttribute("currentUser");
    	TutorType tutorType = new TutorType();
    	tutorType.setTypeName(typeName);
    	tutorType.setDescribe(describes);
    	tutorType.setCreateTime(createTime);
    	tutorType.setTrueName(currentUser.getTrueName());
    	tutorType.setPhone(currentUser.getPhoneNum());
    	tutorType.setTutorType(flag);
    	tutorType.setUrl(url);
    	boolean flags = typeService.addType(tutorType);
		return flags;
	}
	
	/**
	 * 后台显示类别
	 * */
	@RequestMapping(value="/typeList")
	public Map<String, Object> publishList(@RequestParam(value="page",required=false)String page,
			@RequestParam(value="rows",required=false)String rows,
			TutorType type,String flag) throws Exception{
		
		PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("tutorType",flag);
		map.put("url",type.getUrl());
		map.put("typeName", type.getTypeName());
		map.put("start", pageBean.getStart());
		map.put("size", pageBean.getPageSize());
		List<TutorType> typetList =typeService.findTypeList(map);
		Long total=typeService.getTotal(map);
		Map<String,Object> m = new HashMap<>();
		m.put("rows", typetList);
		m.put("total", total);
		return m;
	}
	
	/**
	 * 删除类型
	 * */
	@RequestMapping(value="/delType")
	public boolean delType(String id) {
		boolean flag = typeService.delType(id);
		return flag;
	}
	
	/**
	 * 更新公告
	 * */
	@RequestMapping(value="/editType")
	public boolean editType(String id,String typeName,String describes,String url) {
		Integer id2 = Integer.parseInt(id);
		TutorType tutorType = new TutorType();
		tutorType.setId(id2);//公告id
		tutorType.setTypeName(typeName);//类型名称
		tutorType.setDescribe(describes);;//类型描述
		tutorType.setUrl(url);;//类型描述
    	boolean flag = typeService.editType(tutorType);
		return flag;
	}
	
	//发布课程页面传递类别列表
	@RequestMapping(value="/getCourseTypes")
	public List<TutorType> getCourseTypes(String flag){
		List<TutorType> courseTypes = typeService.getCourseTypes(flag);
		return courseTypes;
	}
}
