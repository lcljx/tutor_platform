package com.ljx.tutor_platform.entity;

public class Course {
	private User user;//发布者的信息在里面
	private Integer id;
	private TutorType type;
	
	private String courseName;
	private Double price;
	private String courseDescribe;
	
	private Double period;//课时
	private Integer status;//0,1代表未被购买
	private Double commission;//抽成
	
	private Integer rank;//推荐力度
	private String createTime;//创建时间
	
	private String pic;
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public TutorType getType() {
		return type;
	}
	public void setType(TutorType type) {
		this.type = type;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getCourseDescribe() {
		return courseDescribe;
	}
	public void setCourseDescribe(String courseDescribe) {
		this.courseDescribe = courseDescribe;
	}
	public Double getPeriod() {
		return period;
	}
	public void setPeriod(Double period) {
		this.period = period;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Double getCommission() {
		return commission;
	}
	public void setCommission(Double commission) {
		this.commission = commission;
	}
	public Integer getRank() {
		return rank;
	}
	public void setRank(Integer rank) {
		this.rank = rank;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
}
