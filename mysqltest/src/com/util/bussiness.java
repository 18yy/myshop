package com.util;

public class bussiness {
	private Integer ID;//商家id
	private String bussName;//商家名字
	private String bussPsw;//商家密码
	
	
	public bussiness() {
		super();
	}
	public bussiness(String bussName, String bussPsw) {
		super();
		this.bussName = bussName;
		this.bussPsw = bussPsw;
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
	public String getBussPsw(){
		 return bussPsw;
	}
	public void setBussPsw(String bussPsw){
		 this.bussPsw=bussPsw;
	}
}
