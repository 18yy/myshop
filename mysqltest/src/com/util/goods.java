package com.util;

public class goods {
	private Integer ID;//商品id
	private String goodsName;//商品名字
	private String goodsImg;//商品图片
	private String goodsDetail;//商品详情
	private String goodsBussName;//商家名
	private float price;//商品价格
	private Integer status;//商品状态
	
	
	public goods(Integer ID,String goodsName, String goodsImg, String goodsDetail, float price,Integer status) {
		super();
		this.goodsName = goodsName;
		this.goodsImg = goodsImg;
		this.goodsDetail = goodsDetail;
		this.price = price;
		this.status=status;
		this.ID=ID;
		
	}
	
	
	
	public goods(Integer ID,String goodsName, String goodsImg, String goodsDetail, String goodsBussName, float price) {
		super();
		this.goodsName = goodsName;
		this.goodsImg = goodsImg;
		this.goodsDetail = goodsDetail;
		this.goodsBussName = goodsBussName;
		this.price = price;
		this.ID=ID;
	}



	public goods() {
		super();
	}

	

	

	public Integer getID(){
		return ID;
	}
	public void setID(Integer ID){
		 this.ID=ID;
	}
	public String getGoodsName(){
		 return goodsName;
	}
	public void setGoodsName(String goodsName){
		 this.goodsName=goodsName;
	}
	public String getGoodsDetail(){
		 return goodsDetail;
	}
	public void setGoodsDetail(String goodsDetail){
		 this.goodsDetail=goodsDetail;
	}
	public float getPrice(){
		  return price;
	}
	public void setPrice(float price){
		  this.price=price;
	}
	public String getGoodsImg(){
		  return goodsImg;
	}
	public void setGoodsImg(String goodsImg){
		  this.goodsImg=goodsImg;
	}
	public Integer getStatus(){
		return status;
	}
	public void setStatus(Integer status){
		 this.status=status;
	}

	public String getGoodsBussName() {
		return goodsBussName;
	}

	public void setGoodsBussName(String goodsBussName) {
		this.goodsBussName = goodsBussName;
	}

	
	
}
