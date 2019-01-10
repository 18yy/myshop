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
	String[] columnNames = new String[] {  "��Ʒ��", "����", "ͼƬ" ,"�۸�","�̼�"};
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
	 //��ȡ���ߵ�������Ʒ
	public static List<goods> showallgoods() throws Exception {
			
			String sql = null;
			sql = "select p_id,p_name,detail,main_image,price,b_name from products,bussiness where status='1' and products.b_id=bussiness.b_id";//Ĭ��status��Ϊ1��Ϊ����
			Connection con = dbUtil.getCon();
			PreparedStatement pstmt = con.prepareStatement(sql);
			 
			ResultSet rs=pstmt.executeQuery();     //��sql��䴫�����ݿ⣬���ص�ֵΪһ���ַ�����һ���������� 
		    
			List<goods> result = new ArrayList<goods>();
			
		    while(rs.next()){    //next������ȡ���������
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
