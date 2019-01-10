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
	//获取所有用户
	public static List<user> showalluser() throws Exception {
		
		String sql = null;
		sql = "select u_name,u_password,phone,address from user";
		Connection con = dbUtil.getCon();
		PreparedStatement pstmt = con.prepareStatement(sql);
		
		ResultSet rs=pstmt.executeQuery();     //将sql语句传至数据库，返回的值为一个字符集用一个变量接收 
	    
		List<user> result = new ArrayList<user>();
		
	    while(rs.next()){    //next（）获取里面的内容
	    	user u = new user(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4));
	    	result.add(u);
	    }
		
		
		return result;
	}
}
