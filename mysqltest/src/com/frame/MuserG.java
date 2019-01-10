package com.frame;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import com.util.bussiness;
import com.util.goods;
import com.util.myutil;
import com.util.user;

public class MuserG extends AbstractTableModel{
	static List<goods> g ;
	private static myutil dbUtil = new myutil();
	String[] columnNames = new String[] {  "商品名", "详情", "图片" ,"价格","商家"};
	 public MuserG() throws Exception{	
		 g = showallgoods();
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
		    else if (columnIndex == 4)
		    	return result.getGoodsBussName();
		  return null;
	 }

	 public static void main(String[] args) throws Exception {
	       new MuserG();
	  }
	 //获取上线的所有商品
	public static List<goods> showallgoods() throws Exception {
			
			String sql = null;
			sql = "select p_id,p_name,detail,main_image,price,b_name from products,bussiness where status='1' and products.b_id=bussiness.b_id";//默认status设为1，为上线
			Connection con = dbUtil.getCon();
			PreparedStatement pstmt = con.prepareStatement(sql);
			 
			ResultSet rs=pstmt.executeQuery();     //将sql语句传至数据库，返回的值为一个字符集用一个变量接收 
		    
			List<goods> result = new ArrayList<goods>();
			
		    while(rs.next()){    //next（）获取里面的内容
		    	 Integer goodsId = rs.getInt("p_id");
		    	 String goodsBussName = rs.getString("b_name");
		    	 String gname =rs.getString("p_name");
			     String gdetail = rs.getString("detail");
			     String gimage = rs.getString("main_image");
			     float gprice = rs.getFloat("price");
		    	result.add(new goods(goodsId,gname, gdetail,gimage,goodsBussName, gprice));
		    	
		   }		   			
			return result;
	}	
}
