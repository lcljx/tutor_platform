package com.ljx.tutor_platform.entity;


/**
 * @author 林佳鑫
 * Description:收藏的实体类
 */
public class Favorite {

	private Integer favoriteId;//收藏的id
	private Integer userId;
	private String courseName;//课程名称
	private String contact;//课程联系人
	
	private String mobilePhone;//手机号码
	private Double coursePrice;//课程价格
	private String picture;//图片
	
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getMobilePhone() {
		return mobilePhone;
	}
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	public Double getCoursePrice() {
		return coursePrice;
	}
	public void setCoursePrice(Double coursePrice) {
		this.coursePrice = coursePrice;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer integer) {
		this.userId = integer;
	}
	public Integer getFavoriteId() {
		return favoriteId;
	}
	public void setFavoriteId(Integer favoriteId) {
		this.favoriteId = favoriteId;
	}
	
	
	
}
