//package com.dao;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.util.ArrayList;
//import java.util.List;
//
//import com.util.CommonUtils;
//import com.util.bussiness;
//import com.util.goods;
//import com.util.myutil;
//
//
//public class allgoods {
//	private static myutil dbUtil = new myutil();
//	//��ȡ������Ʒ
//	public static List<goods> showallgoods() throws Exception {
//		
//		String sql = null;
//		sql = "select p_name,main_image,detail,price from products where status='1'";//Ĭ��status��Ϊ1��Ϊ����
//		Connection con = dbUtil.getCon();
//		PreparedStatement pstmt = con.prepareStatement(sql);
//		
//		ResultSet rs=pstmt.executeQuery();     //��sql��䴫�����ݿ⣬���ص�ֵΪһ���ַ�����һ���������� 
//	    
//		List<goods> result = new ArrayList<goods>();
//		
//	    while(rs.next()){    //next������ȡ���������
//	    	goods g = new goods(rs.getString(1),rs.getString(2),rs.getString(3),rs.getFloat(4));
//	    	result.add(g);
//	    }
//		
//		
//		return result;
//	}
//	//������Ʒ
//	public static boolean addgoods(goods g) throws Exception {
//		CommonUtils.bussiness = new bussiness();
//		String sql = null;
//		sql = "insert into products(p_name,main_image,detail,price,b_id) value("+g.getGoodsName()+ "," +g.getGoodsImg()+","+g.getGoodsDetail()
//		+","+g.getPrice()+CommonUtils.bussiness.getID()+")";
//		Connection con = dbUtil.getCon();
//		PreparedStatement pstmt = con.prepareStatement(sql);
//		
//		int i = pstmt.executeUpdate();
//		if (i == 1) {
//			dbUtil.close(pstmt, con);
//			return true;
//		} else {
//			dbUtil.close(pstmt, con);
//			return false;
//		}
//	}
//	//������Ʒ
//	public static boolean buggoods(goods g) throws Exception {
//		
//		String sql = null;
//		sql = "update products set status='0' where p_id="+g.getID()+"";//status��Ϊ0Ϊ�ѹ���
//		Connection con = dbUtil.getCon();
//		PreparedStatement pstmt = con.prepareStatement(sql);
//		
//		int i = pstmt.executeUpdate();
//		if (i == 1) {
//			dbUtil.close(pstmt, con);
//			return true;
//		} else {
//			dbUtil.close(pstmt, con);
//			return false;
//		}
//
//	}
//	//ɾ����Ʒ
//	public static boolean deletegoods(goods g) throws Exception {
//			
//			String sql = null;
//			sql = "update products set status='2' where p_id="+g.getID()+"";//status��Ϊ2Ϊ��ɾ��
//			Connection con = dbUtil.getCon();
//			PreparedStatement pstmt = con.prepareStatement(sql);
//			
//			int i = pstmt.executeUpdate();
//			if (i == 1) {
//				dbUtil.close(pstmt, con);
//				return true;
//			} else {
//				dbUtil.close(pstmt, con);
//				return false;
//			}
//	
//		}
//}
