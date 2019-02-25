package com.ljx.tutor_platform.entity;
/**
 * 任务实体
 * */
public class Task {
	private User user;//发布者的信息在里面
	private Integer id;
	private TutorType type;
	
	private String taskName;
	private Double price;
	private String taskDescribe;
	
	private Double period;//课时
	private Integer status;//0,1代表未被购买
	private Integer ranks;//推荐力度
	
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
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getTaskDescribe() {
		return taskDescribe;
	}
	public void setTaskDescribe(String taskDescribe) {
		this.taskDescribe = taskDescribe;
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
	public Integer getRanks() {
		return ranks;
	}
	public void setRanks(Integer ranks) {
		this.ranks = ranks;
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
