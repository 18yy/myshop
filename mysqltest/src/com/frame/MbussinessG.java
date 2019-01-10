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
	String[] columnNames = new String[] {  "��Ʒ��", "����", "ͼƬ" ,"�۸�","״̬"};

	
	 public MbussinessG(bussiness myBussiness) throws Exception{	
		  bid = myBussiness.getID();//��ȡ�̼�ID	
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
		    		return "���ϼ�";
		    	}else {
		    		return "�ѱ�����";
		    	}
		    }
		    	
	     return null;
	    }


	  public static void main(String[] args, bussiness myBussiness) throws Exception {
	       new MbussinessG(myBussiness);   
	    }
	
	  //��ȡ������������Ʒ
	
		public static List<goods> showallgoods(Integer bid) throws Exception {
			
			String sql = null;
			sql = "select * from products where b_id=?";//Ĭ��status��Ϊ1��Ϊ����
			Connection con = dbUtil.getCon();
			PreparedStatement pstmt = con.prepareStatement(sql);
			 pstmt.setInt(1, bid);
			ResultSet rs=pstmt.executeQuery();     //��sql��䴫�����ݿ⣬���ص�ֵΪһ���ַ�����һ���������� 
		    
			List<goods> result = new ArrayList<goods>();
			
		    while(rs.next()){    //next������ȡ���������
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
