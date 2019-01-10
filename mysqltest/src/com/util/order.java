package com.util;

public class order {
	private Integer ID;//orderid
	private Integer UID;//userid
	private Integer GID;//goodsid
	private String goodsName;//商品名字
	private float price;//商品价格
	private String createtime;//创建时间
	private String bussinessname;
	private String username;
	private String phone;
	private String address;
	
	public order(String goodsName, float price, String createtime, String username, String phone, String address) {
		super();
		this.goodsName = goodsName;
		this.price = price;
		this.createtime = createtime;
		this.username = username;
		this.phone = phone;
		this.address = address;
	}
	public order(Integer uID, String goodsName, float price, String createtime, String bussinessname) {
		super();
		UID = uID;
		this.goodsName = goodsName;
		this.price = price;
		this.createtime = createtime;
		this.bussinessname = bussinessname;
	}
	public order(Integer uID, String goodsName, float price) {
		super();
		UID = uID;
		this.goodsName = goodsName;
		this.price = price;
	}
	public order() {
		super();
	}
	public Integer getID(){
		return ID;
	}
	public void setID(Integer ID){
		 this.ID=ID;
	}
	public Integer getUID(){
		return UID;
	}
	public void setUID(Integer UID){
		 this.UID=UID;
	}
	public Integer getGID(){
		return GID;
	}
	public void setGID(Integer GID){
		 this.GID=GID;
	}
	public String getGoodsName(){
		 return goodsName;
	}
	public void setGoodsName(String goodsName){
		 this.goodsName=goodsName;
	}
	public float getPrice(){
		  return price;
	}
	public void setPrice(float price){
		  this.price=price;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getBussinessname() {
		return bussinessname;
	}
	public void setBussinessname(String bussinessname) {
		this.bussinessname = bussinessname;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
}
