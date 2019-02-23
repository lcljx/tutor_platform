package com.ljx.tutor_platform.entity;

import java.util.Date;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelIgnore;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;

/**
 * 反馈
 * */
@ExcelTarget("contact")
public class Contact {

	@ExcelIgnore
	private String id;//联系人姓名
	@ExcelIgnore
	private String pic;//联系人姓名
	@Excel(name = "姓名", width = 25)
	private String name;//联系人姓名
	@Excel(name = "邮箱", width = 25)
	private String email;//邮箱
	@Excel(name = "反馈标题", width = 25)
	private String title;//反馈标题
	@Excel(name = "反馈内容", width = 25)
	private String message;//反馈内容
	@Excel(name = "反馈时间", width = 25)
	private String createTime;//反馈时间
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	
	
}
