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
import com.util.order;
import com.util.user;

public class Morder extends AbstractTableModel{
	private static Integer uid = 0;
	static List<order> o ;
	private static myutil dbUtil = new myutil();
	String[] columnNames = new String[] {  "商品名", "价格","商家","购买时间"};
	 public Morder(user myuser) throws Exception{	
		uid = myuser.getID();
		o = showallorder(uid);
	 }
	 @Override
	 public int getColumnCount() {
			// TODO Auto-generated method stub
		 return columnNames.length;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return o.size();
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
		  order result = o.get(rowIndex);
		  if (columnIndex == 0)
			  return result.getGoodsName();
		    else if (columnIndex == 1)
		    	return result.getPrice();
		    else if (columnIndex == 2)
		    	return result.getBussinessname();
		    else if (columnIndex == 3)
		    	 return result.getCreatetime();
		   
	     return null;
	    }
	  public static void main(String[] args, user myuser) throws Exception {
	         new Morder(myuser);
	    }
	//获取所有订单
		public static List<order> showallorder(int uid) throws Exception {
			//CommonUtils.user = new user();
			String sql = null;
			sql = "select * from orders where u_id=?";
			Connection con = dbUtil.getCon();
			PreparedStatement pstmt = con.prepareStatement(sql);
			 pstmt.setInt(1, uid);
			ResultSet rs=pstmt.executeQuery();     //将sql语句传至数据库，返回的值为一个字符集用一个变量接收 
		    
			List<order> result = new ArrayList<order>();
			
		    while(rs.next()){    //next（）获取里面的内容
		    	
		    	 String gname =rs.getString("p_name");
			     String bussinessname = rs.getString("bussname");
			     float gprice = rs.getFloat("total_price");
			     String createtime = rs.getString("createTime");
		    	result.add(new order(uid, gname, gprice, createtime, bussinessname));
		    }
			
			
			return result;
		}
}
