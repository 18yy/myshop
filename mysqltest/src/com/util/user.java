package com.util;

public class user {
	private Integer ID;//用户id
	private String userName;//用户名字
	private String userPsw;//用户密码
	private String phone;//用户手机
	private String address;//地址
	
	
	
	public user(String userName, String userPsw, String phone, String address) {
		super();
		this.userName = userName;
		this.userPsw = userPsw;
		this.phone = phone;
		this.address = address;
	}
	public user(){
	  }
	public Integer getID(){
		return ID;
	}
	public void setID(Integer ID){
		 this.ID=ID;
	}
	public String getUserName(){
		 return userName;
	}
	public void setUserName(String userName){
		 this.userName=userName;
	}
	public String getUserPsw(){
		 return userPsw;
	}
	public void setUserPsw(String userPsw){
		 this.userPsw=userPsw;
	}
	public String getUserPhone(){
		 return phone;
	}
	public void setUserPhone(String userPhone){
		 this.phone=userPhone;
	}
	public String getAddress(){
		 return address;
	}
	public void setAddress(String address){
		 this.address=address;
	}
	
}
