package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.util.order;
import com.util.user;
import com.util.CommonUtils;
import com.util.goods;
import com.util.myutil;

public class allorder {
	private static myutil dbUtil = new myutil();
	//��ȡ���ж���
	public static List<order> showallorder() throws Exception {
		//CommonUtils.user = new user();
		String sql = null;
		sql = "select * from order";
		Connection con = dbUtil.getCon();
		PreparedStatement pstmt = con.prepareStatement(sql);
		
		ResultSet rs=pstmt.executeQuery();     //��sql��䴫�����ݿ⣬���ص�ֵΪһ���ַ�����һ���������� 
	    
		List<order> result = new ArrayList<order>();
		
	    while(rs.next()){    //next������ȡ���������
	    	order o = new order(rs.getInt("u_id"),rs.getString("p_name"),rs.getFloat("total_price"));
	    	result.add(o);
	    }
		
		
		return result;
	}
	//���Ӷ���
		public static boolean addorder(order o,goods g) throws Exception {
			CommonUtils.user = new user();
			String sql = null;
			sql = "insert into order(u_id,p_id,p_name,total_price) value("+CommonUtils.user.getID()+g.getID()+g.getGoodsName()+g.getPrice()+")";
			Connection con = dbUtil.getCon();
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			int i = pstmt.executeUpdate();
			if (i == 1) {
				dbUtil.close(pstmt, con);
				return true;
			} else {
				dbUtil.close(pstmt, con);
				return false;
			}
		}
}
