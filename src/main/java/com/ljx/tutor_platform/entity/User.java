package com.ljx.tutor_platform.entity;

public class User {
    private String userName;
    private String pass;
    private String rePass;
    private String phoneNum;
    private String  vcode;
    private Integer role;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getRePass() {
		return rePass;
	}
	public void setRePass(String rePass) {
		this.rePass = rePass;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getVcode() {
		return vcode;
	}
	public void setVcode(String vcode) {
		this.vcode = vcode;
	}
	public Integer getRole() {
		return role;
	}
	public void setRole(Integer role) {
		this.role = role;
	}
    
    
}
