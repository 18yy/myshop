package com.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class MbussinessO extends AbstractTableModel{
	private static Integer bid = 0;
	static List<order> o ;
	private static myutil dbUtil = new myutil();
	String[] columnNames = new String[] {  "��Ʒ��", "�۸�","���","�����ϵ����","��ҵ�ַ","����ʱ��"};
	
	public MbussinessO(bussiness myBussiness) throws Exception{	
		  bid = myBussiness.getID();//��ȡ�̼�ID	
		  o = showallorder(bid);
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
		  order result = o.get(rowIndex);
		  if (columnIndex == 0)
			  return result.getGoodsName();
		    else if (columnIndex == 1)
		    	return result.getPrice();
		    else if (columnIndex == 2)
		    	return result.getUsername();
		    else if (columnIndex == 3)
		    	 return result.getPhone();
		    else if (columnIndex == 4)
		    	 return result.getAddress();
		    else if (columnIndex == 5)
		    	 return result.getCreatetime();
	     return null;
	    }

	  public static void main(String[] args, bussiness myBussiness) throws Exception {
	       new MbussinessO(myBussiness);   
	    }

	  //��ȡ�̼ҵ����ж���
	public static List<order> showallorder(int bid) throws Exception {
		//CommonUtils.user = new user();
		String sql = null;
		sql = "select * from orders,myaddress,user where myaddress.u_id=orders.u_id and user.u_id=orders.u_id and p_id in(select p_id from products where b_id=?)";
		Connection con = dbUtil.getCon();
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, bid);
		ResultSet rs=pstmt.executeQuery();     //��sql��䴫�����ݿ⣬���ص�ֵΪһ���ַ�����һ���������� 
	    
		List<order> result = new ArrayList<order>();
		
	    while(rs.next()){    //next������ȡ���������
	    	
	    	 String gname =rs.getString("p_name");
		     String username = rs.getString("u_name");
		     float gprice = rs.getFloat("total_price");
		     String createtime = rs.getString("createTime");
		     String phone = rs.getString("phone");
		     String address = rs.getString("address");
		     System.out.println(gname); // ������񡱺�ִ����������
    			
	    	result.add(new order( gname, gprice, createtime,username,phone,address));
	    }
		
		
		return result;
	}
	//��ȡ���ж���
//			public static List<order> showallorder(int bid) throws Exception {
//				//CommonUtils.user = new user();
//				String sql = null;
//				sql = "select p_name,total_price,createtime from orders";
//				Connection con = dbUtil.getCon();
//				PreparedStatement pstmt = con.prepareStatement(sql);
//				// pstmt.setInt(1, bid);
//				ResultSet rs=pstmt.executeQuery();     //��sql��䴫�����ݿ⣬���ص�ֵΪһ���ַ�����һ���������� 
//			    
//				List<order> result = new ArrayList<order>();
//				
//			    while(rs.next()){    //next������ȡ���������
//			    	
//			    	 String gname =rs.getString("p_name");
//				     //String username = rs.getString("u_name");
//				     float gprice = rs.getFloat("total_price");
//				     String createtime = rs.getString("createTime");
////				     String phone = rs.getString("phone");
////				     String address = rs.getString("address");
//				     System.out.println(gname); // ������񡱺�ִ����������
//	        			
////			    	result.add(new order( gname, gprice, createtime,username,phone,address));
//			    }
//				
//				
//				return result;
//			}	  
}
