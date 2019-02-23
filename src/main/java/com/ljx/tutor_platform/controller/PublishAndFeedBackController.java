package com.ljx.tutor_platform.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.ljx.tutor_platform.entity.Contact;
import com.ljx.tutor_platform.entity.PageBean;
import com.ljx.tutor_platform.entity.Publish;
import com.ljx.tutor_platform.entity.User;
import com.ljx.tutor_platform.service.PublishAndFeedBackService;
import com.ljx.tutor_platform.utils.ExcelUtils;
import com.ljx.tutor_platform.utils.GsonUtils;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;

@RestController
@RequestMapping(value="/feedpub")//feedpub是feedback和publish的简写
@EnableAutoConfiguration
public class PublishAndFeedBackController {

	@Autowired
	private PublishAndFeedBackService pfService;
	
	/**
	 * 发布公告
	 * */
	@RequestMapping(value="/savePublish")
	public boolean savePublish(String publishname,String publishcontent,HttpServletRequest request) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
    	String createTime = df.format(new Date());// new Date()为获取当前系统时间
    	User currentUser = (User) request.getSession().getAttribute("currentUser");
    	Publish publish = new Publish();
    	publish.setCreateTime(createTime);
    	publish.setPublishName(publishname);//公告名称
    	publish.setContent(publishcontent);//公告内容
    	publish.setContact(currentUser.getPhoneNum());//联系号码
    	publish.setUserName(currentUser.getUserName());//创建人
    	boolean flag = pfService.addPublish(publish);
		return flag;
	}
	
	/**
	 * 查询显示公告
	 * */
	@RequestMapping(value="/publishList")
	public Map<String, Object> publishList(@RequestParam(value="page",required=false)String page,@RequestParam(value="rows",required=false)String rows,Publish publish) throws Exception{		
		PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("publishName", publish.getPublishName());
		map.put("start", pageBean.getStart());
		map.put("size", pageBean.getPageSize());
		List<Publish> productList =pfService.findPublishList(map);
		Long total=pfService.getTotal(map);
		Map<String,Object> m = new HashMap<>();
		m.put("rows", productList);
		m.put("total", total);
		return m;
	}
	
	/**
	 * 删除公告
	 * */
	@RequestMapping(value="/deletePublish")
	public boolean deletePublish(String id) {
		boolean flag = pfService.delPublishByid(id);
		return flag;
	}
	
	/**
	 * 更新公告
	 * */
	@RequestMapping(value="/updatePublish")
	public boolean updatePublish(String id,String publishname,String publishcontent) {
		Integer id2 = Integer.parseInt(id);
		Publish publish = new Publish();
		publish.setId(id2);//公告id
		publish.setPublishName(publishname);//公告名称
    	publish.setContent(publishcontent);//公告内容
    	boolean flag = pfService.updatePublishByid(publish);
		return flag;
	}
	
	/**
	 * 提交反馈
	 * */
	@RequestMapping(value="/feedback")
	public boolean feedback(Contact contact) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
    	String createTime = df.format(new Date());// new Date()为获取当前系统时间
    	contact.setCreateTime(createTime);
    	boolean flag = pfService.addFeedback(contact);
    	return flag;
	}
	
	/**
	 * 查询显示 反馈
	 * */
	@RequestMapping(value="/feedbacks")
	public Map<String, Object> feedbackList(String page,String rows,Contact contact) throws Exception{		
		PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("title", contact.getTitle());
		map.put("start", pageBean.getStart());
		map.put("size", pageBean.getPageSize());
		List<Contact> feedbacks =pfService.findFeedbacks(map);
		Long total=pfService.getContactTotal(map);
		Map<String,Object> m = new HashMap<>();
		m.put("rows", feedbacks);
		m.put("total", total);
		return m;
	}
	
	/**
	 * excel导出
	 * @throws IOException 
	 * */
	@RequestMapping(value="/doExport")
	public void doExport(HttpServletResponse response,String data) throws IOException {
		List<Contact> contacts = new ArrayList<Contact>();
		List<Contact> list = new ArrayList<Contact>();
		contacts = GsonUtils.StringTolist(data,Contact[].class);
		list.addAll(contacts);
	    String fileName = "反馈信息.xls";
	    ExcelUtils.exportExcel(list, "反馈信息表", "反馈信息", Contact.class, fileName, response);
	};
	
	/**
	 * 删除反馈
	 * @throws IOException 
	 * */
	@RequestMapping(value="/delFeedback")
	public boolean delFeedback(String id) {
		boolean flag = pfService.delFeedbackByid(id);
		return flag;
	}
	
	@RequestMapping(value="/showContacts")
	public List<Contact> showContacts() {
		List<Contact> contacts = pfService.showContacts();
		return contacts;
	}
}
