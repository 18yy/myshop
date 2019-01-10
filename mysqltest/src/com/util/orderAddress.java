package com.util;

public class orderAddress {
	private Integer ID;//地址id
	private String bussName;//商家名字
	private String userName;//用户名字
	private String phone;//用户手机
	private String address;//地址
	public orderAddress(){
		
	  }
	public Integer getID(){
		return ID;
	}
	public void setID(Integer ID){
		 this.ID=ID;
	}
	public String getBussName(){
		 return bussName;
	}
	public void setBussName(String bussName){
		 this.bussName=bussName;
	}
	public String getUserName(){
		 return userName;
	}
	public void setUserName(String userName){
		 this.userName=userName;
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
