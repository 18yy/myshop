package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;


public class myutil {
	
		private static String dbUrl = "jdbc:mysql://127.0.0.1:3306/myshop?useUnicode = true&characterEncoding = utf-8&useSSL = false&serverTimezone = GMT";

		private static String dbUserName = "root";

		private static String dbPassvord = "9527";

		private static String jdbcName = "com.mysql.cj.jdbc.Driver";

		public Connection getCon() throws Exception {
			Class.forName(jdbcName);
			Connection con = DriverManager.getConnection(dbUrl, dbUserName, dbPassvord);
			System.out.println("Á¬½Ó");
			return con;
		}

		public void close(PreparedStatement pstmt, Connection con) throws Exception {
			if (pstmt != null) {
				pstmt.close();
				if (con != null) {
					con.close();
				}
			}

		}
	


}
