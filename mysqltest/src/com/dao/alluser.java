package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.util.user;

import com.util.myutil;

public class alluser {
	private static myutil dbUtil = new myutil();
	//��ȡ�����û�
	public static List<user> showalluser() throws Exception {
		
		String sql = null;
		sql = "select u_name,u_password,phone,address from user";
		Connection con = dbUtil.getCon();
		PreparedStatement pstmt = con.prepareStatement(sql);
		
		ResultSet rs=pstmt.executeQuery();     //��sql��䴫�����ݿ⣬���ص�ֵΪһ���ַ�����һ���������� 
	    
		List<user> result = new ArrayList<user>();
		
	    while(rs.next()){    //next������ȡ���������
	    	user u = new user(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4));
	    	result.add(u);
	    }
		
		
		return result;
	}
}
