package com.frame;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.util.bussiness;
import com.util.goods;
import com.util.myutil;

public class MbussinessG extends AbstractTableModel{
	private static Integer bid = 0;
	static List<goods> g ;
	private static myutil dbUtil = new myutil();
	String[] columnNames = new String[] {  "商品名", "详情", "图片" ,"价格","状态"};

	
	 public MbussinessG(bussiness myBussiness) throws Exception{	
		  bid = myBussiness.getID();//获取商家ID	
		  g = showallgoods(bid);
	 }
	 
	
	 @Override
	 public int getColumnCount() {
			// TODO Auto-generated method stub
		 return columnNames.length;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return g.size();
	}

	@Override
	public String getColumnName(int columnIndex) {
        // TODO Auto-generated method stub
		return columnNames[columnIndex];
    }
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
	}
	  public Object getValueAt(int rowIndex, int columnIndex) {
	        // TODO Auto-generated method stub
		  goods result = g.get(rowIndex);
		  if (columnIndex == 0)
			  return result.getGoodsName();
		    else if (columnIndex == 1)
		    	return result.getGoodsDetail();
		    else if (columnIndex == 2)
		    	return result.getGoodsImg();
		    else if (columnIndex == 3)
		    	 return result.getPrice();
		    else if(columnIndex == 4) {
		    	if(result.getStatus()==1) {
		    		return "新上架";
		    	}else {
		    		return "已被购买";
		    	}
		    }
		    	
	     return null;
	    }


	  public static void main(String[] args, bussiness myBussiness) throws Exception {
	       new MbussinessG(myBussiness);   
	    }
	
	  //获取发布的所有商品
	
		public static List<goods> showallgoods(Integer bid) throws Exception {
			
			String sql = null;
			sql = "select * from products where b_id=?";//默认status设为1，为上线
			Connection con = dbUtil.getCon();
			PreparedStatement pstmt = con.prepareStatement(sql);
			 pstmt.setInt(1, bid);
			ResultSet rs=pstmt.executeQuery();     //将sql语句传至数据库，返回的值为一个字符集用一个变量接收 
		    
			List<goods> result = new ArrayList<goods>();
			
		    while(rs.next()){    //next（）获取里面的内容
		    	 Integer gid = rs.getInt("p_id");
		    	 String gname =rs.getString("p_name");
			     String gdetail = rs.getString("detail");
			     String gimage = rs.getString("main_image");
			     float gprice = rs.getFloat("price");
			     Integer status = rs.getInt("status");
		    	result.add(new goods(gid,gname, gdetail,gimage, gprice,status));
		    	
		    }		   			
			return result;
		}	

}
