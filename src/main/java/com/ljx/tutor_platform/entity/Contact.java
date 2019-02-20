package com.ljx.tutor_platform.entity;
/**
 * 反馈
 * */
public class Contact {

	private String name;//联系人姓名
	private String email;//邮箱
	private String title;//反馈标题
	private String message;//反馈内容
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
	
	
}
